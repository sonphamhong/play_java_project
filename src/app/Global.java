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
    
}
