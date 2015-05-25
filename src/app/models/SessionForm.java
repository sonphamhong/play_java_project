package models;

import javax.validation.*;

import play.data.validation.Constraints.*;


public class SessionForm extends Session {

  @Required(message = "required")
  public String name;
  @Required(message = "required")
  public String company_id;
}