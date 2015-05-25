package models;

import javax.validation.*;

import play.data.validation.Constraints.*;


public class CompanyForm extends Company {

  @Required(message = "required")
  public String name;
}