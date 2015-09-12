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
    Client firstClient = new Client("Betty Sue");
    Client secondClient = new Client("Betty Sue");
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Client myClient = new Client("Betty Sue");
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void find_findClientInDatabase_true() {
    Client myClient = new Client("Betty Sue");
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

   @Test
   public void update_updateClientNameToNewName() {
     Client myClient = new Client("Patrick Stewart");
     myClient.save();
     Client updateClient = new Client("Tyrese Gibson");
    myClient.update(updateClient.getId(), updateClient.getName());
    assertTrue(updateClient.getName() == "Tyrese Gibson");
  }

 @Test
 public void delete_deleteClientFromDatabase() {
   Client myClient = new Client("Patrick Stewart");
   myClient.save();
   myClient.delete();
   assertEquals(0, Client.all().size());
 }
}
