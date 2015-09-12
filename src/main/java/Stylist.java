import java.util.List;
import org.sql2o.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Stylist {
  private int id;
  private String stagename;
  private String specialization;


  public int getId() {
    return id;
  }

  public String getStageName() {
    return stagename;
  }

  public String getSpecialization() {
    return specialization;
  }

  public Stylist(String stagename, String specialization) {
    this.stagename = stagename;
    this.specialization = specialization;
  }
  @Override
public boolean equals(Object otherStylist){
  if (!(otherStylist instanceof Stylist)) {
    return false;
  } else {
    Stylist newStylist = (Stylist) otherStylist;
    return this.getStageName().equals(newStylist.getStageName());
  }
}

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (stagename, specialization) VALUES (:stagename, :specialization);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("stagename", this.stagename)
      .addParameter("specialization", this.specialization)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists ORDER BY stagename";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id = :id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public static void updateStylistName(int id, String stagename) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "UPDATE stylists SET stagename = :stagename WHERE id = :id";
     con.createQuery(sql)
     .addParameter("stagename", stagename)
     .addParameter("id", id)
     .executeUpdate();
   }
 }

 public static void updateStylistSpec(int id, String specialization) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "UPDATE stylists SET specialization = :specialization WHERE id = :id";
     con.createQuery(sql)
     .addParameter("specialization", specialization)
     .addParameter("id", id)
     .executeUpdate();
   }
 }

 public void delete() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "DELETE FROM stylists WHERE id = :id ";
     con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
   }
 }
}
