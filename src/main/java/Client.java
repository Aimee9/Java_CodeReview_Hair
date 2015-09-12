import java.util.List;
import org.sql2o.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Client {
  private int id;
  private String name;



  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Client(String name) {
    this.name = name;
  }

  @Override
public boolean equals(Object otherClient){
  if (!(otherClient instanceof Client)) {
    return false;
  } else {
    Client newClient = (Client) otherClient;
    return this.getName().equals(newClient.getName());
  }
}

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients ORDER BY name";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id = :id";
      Client client = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public static void update(int id, String name) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "UPDATE clients SET name = :name WHERE id = :id";
     con.createQuery(sql)
     .addParameter("name", name)
     .addParameter("id", id)
     .executeUpdate();
   }
 }

 public void delete() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "DELETE FROM clients WHERE id = :id ";
     con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
   }
 }
}
