package controllers;

import play.*;
import play.libs.F.Promise;
import play.mvc.*;
import models.*;
import play.data.*;
import play.db.*;
import views.html.*;
import play.i18n.Lang;
import play.i18n.Messages;
import play.mvc.Http.Cookie;
import play.mvc.Http.Request;
import play.mvc.SimpleResult;

import java.util.*;
import java.lang.Object;
import java.lang.reflect.Method;


public class Login extends Controller{
	static Form<UserForm> userForm = Form.form(UserForm.class);
	public static Result session_new() {
		return ok(views.html.login.render(userForm));
	  }
	
	public static Result create_session(){
		Form<UserForm> filledUserForm = userForm.bindFromRequest();
	    if (filledUserForm.hasErrors()) {
	    	play.Logger.info("sdfsdfsdfsdfsdf"+ filledUserForm.errors());
	      return badRequest(views.html.login.render( filledUserForm));
	    } else {
	      Map<String, String[]> params = request().body().asFormUrlEncoded();
	      play.Logger.info("params "+ params);
	      String user_name = params.get("login_id")[0];
	      String password = params.get("password")[0];
	      if(User.authenticate(user_name, password)){
	    	  return redirect(routes.Application.lists());
	      } else {
	    	  return redirect(routes.Login.session_new());
	      }
	      
	    }
	}
}
