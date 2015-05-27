package models;

import javax.validation.*;

import play.data.validation.Constraints.*;


public class UserForm extends User {

//  @Required(message = "To pole jest wymagane")
//  public String session_id;
  @Required
  public String login_id;
  @Required
  public String password;
}