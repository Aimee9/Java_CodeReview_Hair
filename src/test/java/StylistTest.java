import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfStageNamesAretheSame() {
    Stylist firstStylist = new Stylist("Hairy Potter", "Coloring");
    Stylist secondStylist = new Stylist("Hairy Potter", "Coloring");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist myStylist = new Stylist("Hairy Potter", "Coloring");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void find_findStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Hairy Potter", "Coloring");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

   @Test
   public void update_updateStylistNameToNewName() {
     Stylist myStylist = new Stylist("Hairy Potter", "Coloring");
     myStylist.save();
     Stylist updateStylistName = new Stylist("Frizzy McGuire", "Coloring");
    myStylist.updateStylistName(updateStylistName.getId(), updateStylistName.getStageName());
    assertTrue(updateStylistName.getStageName() == "Frizzy McGuire");
  }

  @Test
  public void update_updateStylistSpecToNewSpec() {
    Stylist myStylist = new Stylist("Hairy Potter", "Coloring");
    myStylist.save();
    Stylist updateStylistSpec = new Stylist("Frizzy McGuire", "Coloring");
   myStylist.updateStylistSpec(updateStylistSpec.getId(), updateStylistSpec.getSpecialization());
   assertTrue(updateStylistSpec.getSpecialization() == "Coloring");
 }
}
