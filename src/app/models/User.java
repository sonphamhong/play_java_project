package models;

import java.util.*;

import play.db.ebean.*;

import javax.validation.*;

import play.data.validation.Constraints.*;

import javax.persistence.*;
import org.mindrot.jbcrypt.BCrypt;
@Entity
@Table(name = "m_users")
public class User extends Model {

  @Id
  public Integer user_id;
  @ManyToOne
  //@JoinTable(name = "sessions")
  public Company company;
  @ManyToOne
  public Session session;
//  @Column(nullable = false)
//  public Integer user_auth_id;
//  @Column(nullable = false)
  public String login_id;
//  @Column(nullable = false)
  public String password;
//  public String password_before;
//  public java.util.Date password_update_date;
  @Column(nullable = false, length = 50)
  public String user_name;
//  @Column(nullable = false, length = 100)
//  public String user_name_kana;
//  @Column(nullable = false)
//  public String mail_address;
//  @Column(nullable = false)
//  public boolean mail_rreceive_flg;
//  @Column(nullable = false)
//  public boolean sex_flag;
//  @Column(nullable = false)
//  public java.util.Date birthday;
//  public java.util.Date hire_date;
//  @Column(nullable = false)
//  public java.util.Date term_agree_date;
//  public java.util.Date deleted_at;
//  @Column(nullable = false)
//  public java.util.Date created_at;
//  @Column(nullable = false)
//  public Integer created_by;
//  @Column(nullable = false)
//  public java.util.Date updated_at;
//  @Column(nullable = false)
//  public Integer updated_by;
  public static Finder<Integer, User> find = new Finder(Integer.class,
      User.class);

  public static List<User> all() {
    return find.all();
  }

  public static void create(User user) {
    user.save();
  }
  
  public static void create_user(Map<String, String[]> params) {
    User newU = new User();
    String session_id = params.get("session_id")[0];
    String user_name = params.get("login_id")[0];
    Long sesid = Long.parseLong(session_id);
    String password = params.get("password")[0];
    Session session = Session.find.ref(sesid);
    newU.session = session;
    newU.company = session.company;
    if (user_name != ""){
      newU.login_id = user_name ;
      newU.user_name = user_name;
      newU.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    newU.save();
  }

  public static boolean authenticate(String username, String password){
	  User user = User.find.where().eq("user_name", username).findUnique();
	  if(user != null && BCrypt.checkpw(password, user.password)){
		  return true;
	  } else {
		  return false;  
	  }
  }
  
  public static void delete(Integer id) {
    find.ref(id).delete();
  }

}