package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "sessions")
public class Session extends Model {

  @Id
  public Long id;
  @Column(nullable = false)
  public String name;
  @ManyToOne
  // @JoinColumn(name="company_id", insertable=false, updatable=false)
  public Company company;
  @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "session")
  public List<User> users;

  public static Finder<Long, Session> find = new Finder(Long.class,
      Session.class);

  public static List<Session> all() {
    return find.all();
  }

  public static void create(Session session) {
    session.save();
  }
  
  public static void create_session(Map<String, String[]> params) {
    Session newS = new Session();
    String companyId = params.get("company_id")[0];
    String name = params.get("name")[0];
    Long comId = Long.parseLong(companyId);
    Company com = Company.find.ref(comId);
    newS.company = com;
    newS.name = name ;
    newS.save();
  }

  public static void delete(Long id) {
    find.ref(id).delete();
  }

}