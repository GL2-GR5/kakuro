package fr.mcgcorp.managers;

import fr.mcgcorp.Main;
import fr.mcgcorp.Tests;
import fr.mcgcorp.managers.SaveManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
   * Méthode pour initialiser un fichier de test.
   * @param res le fichier de ressource à copier.
   * @param path le chemin du fichier à copier.
   */
  private static void initTestFile(String res, Path path) {
    try {
      // Obtenire le fichier à copier.
      InputStream inputStream = Main.class.getResourceAsStream(res);
      if (inputStream == null) {
        throw new FileNotFoundException("Ressource non trouvée : " + res);
      }
      // Copier le fichier obtenu dans le fichier path.
      Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      throw new RuntimeException("Impossible de copier le fichier " + res + " dans " + path, e);
    }
  }

  /**
   * Méthode exécutée avant tous les tests.
   * Affiche le nom de la classe de test au début de l'exécution.
   */
  @BeforeAll
  public static void initAll(){
    printNameAtStart(JsonFileTest.class);
    initTestFile(JsonFileTest.sTestFile,JsonFileTest.testFile);
    initTestFile(JsonFileTest.sTestFile2,JsonFileTest.testFile2);
    JsonFile.testMode = true;
  }

  /**
   * Méthode exécutée après tous les tests.
   * Affiche le nom de la classe de test à la fin de l'exécution.
   */
  @AfterAll
  public static void tearDownAll(){
    try {
      Files.delete(JsonFileTest.testFile2);
    } catch (IOException e) {
      System.out.println("Fichier " + JsonFileTest.testFile2 + " n'à pût être supprimé.");
    }
    try {
    Files.delete(JsonFileTest.testFile);
    } catch (IOException e) {
      System.out.println("Fichier " + JsonFileTest.testFile + " n'à pût être supprimé.");
    }
    printNameAtEnd(JsonFileTest.class);
  }

  /**
   * Le répertoire de sauvegarde.
   */
  private static Path dir = SaveManager.getInstance().getSaveDir();
  /**
   * Le fichier de test.
   */
  private static String sTestFile = "test.json";
  /**
   * Le chemin du fichier de test.
   */
  private static Path testFile = JsonFileTest.dir.resolve(JsonFileTest.sTestFile);
  /**
   * Le fichier de test.
   */
  private static String sTestFile2 = "anotherTest.json";
  /**
   * Le chemin du fichier de test.
   */
  private static Path testFile2 = JsonFileTest.dir.resolve(JsonFileTest.sTestFile2);

  /**
   * Test pour vérifier le chargement d'un fichier JSON.
   */
  @Test
  public void loadRessource_contentJson(){
    try {
      String strVerif = "JsonFile :\n{\"message\":\"Mon test\",\"id\":42,\"lst\":[52,48,76]}";
      JsonFile jsonFile = new JsonFile(JsonFileTest.sTestFile2);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile2, false);
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
      String strVerif = "JsonFile : >Edit in " + JsonFileTest.testFile2 + "<\n{\"message\":\"Mon test\",\"id\":42,\"lst\":[52,48,76]}";
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile2, true);
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
      JsonFile jsonFile = new JsonFile(Paths.get(JsonFileTest.dir + file), true);
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
      JsonFile jsonFile = (new JsonFile(JsonFileTest.sTestFile)).getChildren("details");
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
      String path = (new JsonFile(JsonFileTest.sTestFile)).isExist(nodePath);
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
      String path = (new JsonFile(JsonFileTest.sTestFile)).isExist(nodePath + ".false");
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
      boolean rep = new JsonFile(JsonFileTest.sTestFile).isNull(nodePath);
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
      boolean rep = new JsonFile(JsonFileTest.sTestFile).isNull(nodePath);
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
      boolean rep = new JsonFile(JsonFileTest.sTestFile).getBoolean(nodePath);
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
      boolean rep = new JsonFile(JsonFileTest.sTestFile).getBoolean(nodePath);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.sTestFile);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.sTestFile);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.sTestFile);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.sTestFile);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.sTestFile);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.sTestFile);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.sTestFile);
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
  /**
   * Classe pour tester la méthode getJson.
   */
  private static class Details {

    /**
     * Les attributs de la classe.
     */
    private boolean married;
    /**
     * Les attributs de la classe.
     */
    private int children;
    /**
     * Les attributs de la classe.
     */
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile, true);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile2, true);
      String strVerif = "notVerif";
      jsonFile.set("message");
      System.out.println("$568$" + jsonFile.toString() + "$568$");
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile2, true);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile2, true);
      String strVerif = "notVerif";
      jsonFile.set("Valeur", 3012);
      System.out.println("$508$" + jsonFile.toString() + "$508$");
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile2, true);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile2, true);
      String strVerif = "notVerif";
      jsonFile.set("lst.6", 30.12);
      System.out.println("$549$" + jsonFile.toString() + "$549$");
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile2, true);
      String strVerif = "notVerif";
      jsonFile.set("Valeur", "Ça marche");
      System.out.println("$568$" + jsonFile.toString() + "$568$");
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile2, true);
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
      JsonFile jsonFile = new JsonFile(JsonFileTest.testFile2, true);
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
  /**
   * Test pour vérifier le placement d'une valeur dans le JSON.
   */
  private interface testT {
  }
}
