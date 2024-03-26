package fr.mcgcorp.managers;

import fr.mcgcorp.Tests;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe JsonFile.
 * Cette classe contient des méthodes de test pour vérifier le comportement de la classe JsonFile.
 *
 * @author Le Luët Hôa
 */
public class JsonFileTest extends Tests {
    /**
     * Méthode exécutée avant tous les tests.
     * Affiche le nom de la classe de test au début de l'exécution.
     */
    @BeforeAll
    public static void initAll(){
        printNameAtStart(JsonFileTest.class);
    }

    /**
     * Méthode exécutée après tous les tests.
     * Affiche le nom de la classe de test à la fin de l'exécution.
     */
    @AfterAll
    public static void tearDownAll(){
        printNameAtEnd(JsonFileTest.class);
    }

    /**
     * Test pour vérifier le chargement d'un fichier JSON.
     * Attendu => non null.
     */
    @Test
    public void loadRessource_contentJson(){
        JsonFile jsonFile = JsonFile.loadRessource("anotherTest.json");
        assertEquals("File : /fr/mcgcorp/anotherTest.json\n{\"message\":\"Mon test\",\"id\":42}",jsonFile.toString());
    }

    /**
     * Test pour vérifier le chargement d'un fichier JSON.
     * Attendu => non null.
     */
    @Test
    public void load_ShouldNotNull(){
      JsonFile jsonFile = null;
      try {
        jsonFile = JsonFile.load("anotherTest.json");
      } catch (Exception e) {
      }
      assertEquals("File : /fr/mcgcorp/anotherTest.json\n{\"message\":\"Mon test\",\"id\":42}",jsonFile.toString());
    }


    /**
     * Test pour vérifier la création d'un nouveau JSON.
     * Attendu => non null.
     */
    @Test
    public void create_ShouldNotNull(){
        JsonFile jsonFile = JsonFile.create("noFile");
        assertNotNull(jsonFile);
    }

    /**
     * Tests pour vérifier l'obtention d'une valeur du JSON.
     * Attendu => "John Doe".
     */
    @Test
    public void getString_JohnDoe(){
      JsonFile jsonFile = JsonFile.loadRessource("test.json");
      String name = jsonFile.getString("name");
      assertEquals("John Doe",name);
    }

    /**
     * Tests pour vérifier l'obtention d'une valeur du JSON.
     * Attendu => "Anytown".
     */
    @Test
    public void getStringFarAway_Anytown(){
      JsonFile jsonFile = JsonFile.loadRessource("test.json");
      String city = jsonFile.getString("address.city");
      assertEquals("Anytown",city);
    }

    /**
     * Tests pour vérifier l'obtention d'une valeur du JSON.
     * Attendu => 30.
     */
    @Test
    public void getInt_30(){
      JsonFile jsonFile = JsonFile.loadRessource("test.json");
      int age = jsonFile.getInt("age");
      assertEquals(30,age);
    }

    /**
     * Tests pour vérifier l'obtention d'une valeur du JSON.
     * Attendu => 175|176.
     */
    @Test
    public void getIntFromDouble_175(){
      JsonFile jsonFile = JsonFile.loadRessource("test.json");
      int height = jsonFile.getInt("height");
      assertEquals(175,height);
    }

    /**
     * Tests pour vérifier l'obtention d'une valeur du JSON.
     * Attendu => 175,5.
     */
    @Test
    public void getNumeric_175_5(){
      JsonFile jsonFile = JsonFile.loadRessource("test.json");
      Double height = jsonFile.getValue("height",Double.class);
      assertEquals(175.5,height);
    }

    /**
     * Tests pour vérifier l'obtention d'une valeur du JSON.
     * Attendu => true.
     */
    @Test
    public void isNull_true(){
      JsonFile jsonFile = JsonFile.loadRessource("test.json");
      boolean nuller = jsonFile.isNull("details.car");
      assertTrue(nuller);
    }

    /**
     * Tests pour vérifier l'obtention d'une valeur du JSON.
     * Attendu => false.
     */
    @Test
    public void isNotNull_false(){
      JsonFile jsonFile = JsonFile.loadRessource("test.json");
      boolean nuller = jsonFile.isNull("details");
      assertFalse(nuller);
    }

    /**
     * Tests pour vérifier l'obtention d'une valeur du JSON.
     * Attendu => false.
     */
    @Test
    public void getBoolean_false(){
      JsonFile jsonFile = JsonFile.loadRessource("test.json");
      boolean married = jsonFile.getBoolean("details.married");
      assertFalse(married);
    }

    /**
     * Tests pour vérifier l'obtention d'une valeur du JSON.
     * Attendu => [85,90,78,92].
     */
    @Test
    public void getArray_lst(){
      JsonFile jsonFile = JsonFile.loadRessource("test.json");
      String lst = jsonFile.getArray("grades",Integer.class).toString();
      assertEquals("[85,90,78,92]",lst);
    }

    /**
     * Tests pour vérifier l'obtention d'une valeur du JSON.
     * Attendu => ">F2NONE<".
     */
    @Test
    public void getJson_F2NONE(){
      JsonFile jsonFile = JsonFile.loadRessource("test.json");
      Details det = new Details();
      jsonFile.getJson("details",det);
      assertEquals(">F2NONE<",det.toString());
    }

    private static class Details {
      private boolean married;
      private int children;
      private String none;

      public String toString() {
        return ">"
          + ((this.married) ? ('T') : ('F'))
          + this.children
          + ((this.none == null) ? ("NONE") : (this.none))
          + '<';
      }
    }
}
