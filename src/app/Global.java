import play.*;
import play.libs.*;
import models.*;

import java.util.*;

import play.mvc.*;
import play.data.*;
import play.db.*;

import com.avaje.ebean.Ebean;

import controllers.routes;
import play.mvc.Action;
import play.mvc.Http.Request;

import java.lang.reflect.Method;

import play.i18n.Lang;
import play.i18n.Messages;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.SimpleResult;



public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        InitialData.insert(app);
    }
    
    static class InitialData {
        public static void insert(Application app) {
          if (Company.find.findRowCount() == 0){
            Map<String, List<Object>> all = (Map<String, List<Object>>)Yaml.load("seed.yml");
            Ebean.save(all.get("companies"));
          }
        }
    }
    
    private class ActionWrapper extends Action.Simple {
        public ActionWrapper(Action<?> action) {
            this.delegate = action;
        }

        @Override
        public Promise<SimpleResult> call(Http.Context ctx) throws java.lang.Throwable {
        	String locale = ctx.request().getQueryString("locale");
        	ctx.changeLang(locale);
        	Promise<SimpleResult> result = this.delegate.call(ctx);
            Http.Response response = ctx.response();
            //response.setHeader("Access-Control-Allow-Origin", "*");
            return result;
        }
    }

    @Override
    public Action<?> onRequest(Http.Request request, java.lang.reflect.Method actionMethod) {
        return new ActionWrapper(super.onRequest(request, actionMethod));
    }
    
}
