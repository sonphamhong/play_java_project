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

public class Application extends Controller {

  static Form<UserForm> userForm = Form.form(UserForm.class);
  static Form<SessionForm> sessionForm = Form.form(SessionForm.class);
  static Form<Company> companyForm = Form.form(Company.class);

  public static Result index() {
    return redirect(routes.Application.lists());
  }

  public static Result lists() {
    String locale = Controller.request().getQueryString("language");
    //ctx().changeLang(locale);
    //play.i18n.Lang.change(locale);
    return ok(views.html.index.render(Company.all(), userForm, companyForm, sessionForm));
  }

  public static Result newUser() {
    Form<UserForm> filledUserForm = userForm.bindFromRequest();
    if (filledUserForm.hasErrors()) {
      return badRequest(views.html.index.render(Company.all(), filledUserForm, companyForm, sessionForm));
    } else {
      Map<String, String[]> params = request().body().asFormUrlEncoded();
      User.create_user(params);
      return redirect(routes.Application.lists());
    }
  }
  
  public static Result newCompany() {
    Form<Company> filledCompanyForm = companyForm.bindFromRequest();
    if (filledCompanyForm.hasErrors()) {
      return badRequest(views.html.index.render(Company.all(), userForm, filledCompanyForm, sessionForm));
    } else {
      Map<String, String[]> params = request().body().asFormUrlEncoded();
      Company.create(filledCompanyForm.get());
      return redirect(routes.Application.lists());
    }
  }
  
  public static Result newSession() {
    Form<SessionForm> filledSessionForm = sessionForm.bindFromRequest();
    if (filledSessionForm.hasErrors()) {
      return badRequest(views.html.index.render(Company.all(), userForm, companyForm, filledSessionForm));
    } else {
      Map<String, String[]> params = request().body().asFormUrlEncoded();
      Session.create_session(params);
      return redirect(routes.Application.lists());
    }
  }

  public static Result deleteList(Long id) {
    Company.delete(id);
    return redirect(routes.Application.lists());
  }
  
  public  static Result setLanguage() {
    String refererUrl = request().getHeader("referer");
    String locale = Controller.request().getQueryString("locale");
    play.mvc.Controller.ctx().changeLang(locale);
    //Lang.apply(locale);
    return redirect(routes.Application.lists());
  }
}
