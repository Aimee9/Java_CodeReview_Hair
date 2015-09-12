import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Client firstClient = new Client("Betty Sue", 1);
    Client secondClient = new Client("Betty Sue", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Client myClient = new Client("Betty Sue", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
public void save_savesStylistIdIntoDB_true() {
  Stylist myStylist = new Stylist("Cyril Turnstone", "Cuts");
  myStylist.save();
  Client myClient = new Client("GI Jane", myStylist.getId());
  myClient.save();
  Client savedClient = Client.find(myClient.getId());
  assertEquals(savedClient.getStylistId(), myStylist.getId());
}

  @Test
  public void find_findClientInDatabase_true() {
    Client myClient = new Client("Betty Sue", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

   @Test
   public void update_updateClientNameToNewName() {
     Client myClient = new Client("Patrick Stewart", 1);
     myClient.save();
     Client updateClient = new Client("Tyrese Gibson", 1);
    myClient.update(updateClient.getId(), updateClient.getName());
    assertTrue(updateClient.getName() == "Tyrese Gibson");
  }

 @Test
 public void delete_deleteClientFromDatabase() {
   Client myClient = new Client("Patrick Stewart", 1);
   myClient.save();
   myClient.delete();
   assertEquals(0, Client.all().size());
 }
}
