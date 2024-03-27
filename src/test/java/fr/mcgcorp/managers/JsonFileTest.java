package fr.mcgcorp.managers;

import fr.mcgcorp.Tests;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
    JsonFile.testMode = true;
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

  private String kakuroDir = "/home/erwan/Documents/01-Apprentissage/L3/GL2/kakuro/";
  private String dir = kakuroDir + "src/main/resources/fr/mcgcorp/";
  private String testFile = "test.json";
  private String testFile2 = "anotherTest.json";

  /**
   * Test pour vérifier le chargement d'un fichier JSON.
   */
  @Test
  public void loadRessource_contentJson(){
    try {
      String strVerif = "JsonFile :\n{\"message\":\"Mon test\",\"id\":42,\"lst\":[52,48,76]}";
      JsonFile jsonFile = new JsonFile(this.testFile2);
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier le chargement d'un fichier JSON.
   */
  @Test
  public void load_contentJson(){
    try {
      String strVerif = "JsonFile :\n{\"message\":\"Mon test\",\"id\":42,\"lst\":[52,48,76]}";
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile2), false);
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier le chargement d'un fichier JSON.
   */
  @Test
  public void loadForEdit_contentJson(){
    try {
      String strVerif = "JsonFile : >Edit in " + this.dir + this.testFile2 + "<\n{\"message\":\"Mon test\",\"id\":42,\"lst\":[52,48,76]}";
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile2), true);
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier la création d'un fichier JSON.
   */
  @Test
  public void create_contentJson(){
    try {
      String strVerif = "JsonFile : >Edit in A_SUPPRIMER_TEST_KAKURO.JSON<\n{}";
      String file = "A_SUPPRIMER_TEST_KAKURO.JSON";
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + file), true);
      assertEquals(strVerif, jsonFile.toString());
      Files.delete(jsonFile.getFile());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }


  /**
   * Test pour vérifier le découpage du JSON.
   */
  @Test
  public void getChildren_contentJson(){
    try {
      String strVerif = "JsonFile : >Sous arbre<\n{\"married\":false,\"worker\":true,\"children\":2,\"car\":null}";
      JsonFile jsonFile = (new JsonFile(this.testFile)).getChildren("details");
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier si un noeud existe.
   */
  @Test
  public void isExist_shouldBeNull(){
    try {
      String nodePath = "details.married";
      String path = (new JsonFile(this.testFile)).isExist(nodePath);
      assertNull(path);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier si un noeud existe.
   */
  @Test
  public void isExist_shouldBeNotNull(){
    try {
      String nodePath = "details.married";
      String path = (new JsonFile(this.testFile)).isExist(nodePath + ".false");
      assertEquals(nodePath+"<-break", path);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier si un noeud est null.
   */
  @Test
  public void isNull_true(){
    try {
      String nodePath = "details.car";
      boolean rep = new JsonFile(this.testFile).isNull(nodePath);
      assertTrue(rep);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier si un noeud est null.
   */
  @Test
  public void isNull_false(){
    try {
      String nodePath = "grades";
      boolean rep = new JsonFile(this.testFile).isNull(nodePath);
      assertFalse(rep);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier l'obtention d'une valeur du JSON.
   */
  @Test
  public void getBoolean_true(){
    try {
      String nodePath = "details.worker";
      boolean rep = new JsonFile(this.testFile).getBoolean(nodePath);
      assertTrue(rep);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier l'obtention d'une valeur du JSON.
   */
  @Test
  public void getBoolean_false(){
    try {
      String nodePath = "details.married";
      boolean rep = new JsonFile(this.testFile).getBoolean(nodePath);
      assertFalse(rep);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier l'obtention d'une valeur du JSON.
   */
  @Test
  public void getInt_30(){
    try {
      JsonFile jsonFile = new JsonFile(this.testFile);
      int age = jsonFile.getInt("age");
      assertEquals(30, age);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier l'obtention d'une valeur du JSON.
   */
  @Test
  public void getIntFromDouble_175(){
    try {
      JsonFile jsonFile = new JsonFile(this.testFile);
      int height = jsonFile.getInt("height");
      assertEquals(175, height);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier l'obtention d'une valeur du JSON.
   */
  @Test
  public void getNumeric_175_5(){
    try {
      JsonFile jsonFile = new JsonFile(this.testFile);
      Double height = jsonFile.getValue("height", Double.class);
      assertEquals(175.5, height);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier l'obtention d'une valeur du JSON.
   */
  @Test
  public void getString_JohnDoe(){
    try {
      JsonFile jsonFile = new JsonFile(this.testFile);
      String name = jsonFile.getString("name");
      assertEquals("John Doe", name);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier l'obtention d'une valeur du JSON.
   */
  @Test
  public void getStringFarAway_Anytown(){
    try {
      JsonFile jsonFile = new JsonFile(this.testFile);
      String city = jsonFile.getString("address.city");
      assertEquals("Anytown", city);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier l'obtention d'une valeur du JSON.
   */
  @Test
  public void getArray_lst(){
    try {
      JsonFile jsonFile = new JsonFile(this.testFile);
      String lst = jsonFile.getArray("grades", Integer.class).toString();
      assertEquals("[85, 90, 78, 92]", lst);
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier l'obtention d'une valeur du JSON.
   */
  @Test
  public void getJson_F2NONE(){
    try {
      JsonFile jsonFile = new JsonFile(this.testFile);
      Details det = new Details();
      jsonFile.getJson("details", det);
      assertEquals(">F2NONE<", det.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
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

  /**
   * Enlever un noeud dans le Json.
   */
  @Test
  public void removeNode_ContentJson(){
    try {
      String strVerif = "notVerif";
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile), true);
      String[] paths = jsonFile.getFields(null);
      jsonFile.removeNode(paths);
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier le placement d'une valeur dans le JSON.
   */
  @Test
  public void setNull_contentJson(){
    try {
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile2), true);
      String strVerif = "notVerif";
      jsonFile.set("message");
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier le placement d'une valeur dans le JSON.
   */
  @Test
  public void setBoolean_contentJson(){
    try {
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile2), true);
      String strVerif = "notVerif";
      jsonFile.set("Valeur", true);
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier le placement d'une valeur dans le JSON.
   */
  @Test
  public void setInt_contentJson(){
    try {
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile2), true);
      String strVerif = "notVerif";
      jsonFile.set("Valeur", 3012);
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier le placement d'une valeur dans le JSON.
   */
  @Test
  public void setDouble_contentJson(){
    try {
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile2), true);
      String strVerif = "notVerif";
      jsonFile.set("lst.2", 30.12);
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier le placement d'une valeur dans le JSON.
   */
  @Test
  public void setFloat_contentJson(){
    try {
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile2), true);
      String strVerif = "notVerif";
      jsonFile.set("lst.6", 30.12);
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier le placement d'une valeur dans le JSON.
   */
  @Test
  public void setString_contentJson(){
    try {
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile2), true);
      String strVerif = "notVerif";
      jsonFile.set("Valeur", "Ça marche");
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier le placement d'une valeur dans le JSON.
   */
  @Test
  public void setListe_contentJson(){
    try {
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile2), true);
      String strVerif = "notVerif";
      ArrayList<Integer> lst = new ArrayList<>();
      int[] tab = {32, 45, 85, 47, 32, 18, 64};
      for (int i : tab) {
        lst.add(i);
      }
      jsonFile.set("Valeur", lst);
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  /**
   * Test pour vérifier le placement d'une valeur dans le JSON.
   */
  @Test
  public void setJson_contentJson(){
    try {
      JsonFile jsonFile = new JsonFile(Paths.get(this.dir + this.testFile2), true);
      String strVerif = "notVerif";
      char c = 'c';
      jsonFile.set("lst", new testT() {
        private String fieldA = "aaaa";
        private int fieldB = 1234;
        private char fieldC = c;
        private String fieldD = null;
        private boolean Success = true;
      });
      assertEquals(strVerif, jsonFile.toString());
    } catch (Exception e) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      System.out.print(stackTrace[1].getMethodName());
      System.out.print("(" + stackTrace[1].getFileName() + ":");
      System.out.println(stackTrace[1].getLineNumber() + ") :");
      e.printStackTrace();
      assertTrue(false);
    }
  }

  private interface testT {
  }
}
