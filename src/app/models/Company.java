package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company extends Model {

  @Id
  public Long id;
  @Column(nullable = false)
  @Required
  public String name;
  @Required
  @Column(nullable = false, length = 255)
  public String company_id;
  @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "company")
  // @JoinColumn(name="company_id")
  // @JoinColumn(name="company_id", referencedColumnName="company_id",
  // insertable=false, updatable=false)
  public List<Session> sessions;
  @OneToMany(mappedBy = "company")
  //@JoinTable(name = "sessions")
  public List<User> users;

  public static Finder<Long, Company> find = new Finder(Long.class,
      Company.class);

  public static List<Company> all() {
    return find.all();
  }

  public static void create(Company company) {
    company.save();
  }

  public static void delete(Long id) {
    find.ref(id).delete();
  }

}