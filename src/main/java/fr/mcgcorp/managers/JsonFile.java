package fr.mcgcorp.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.POJONode;
import fr.mcgcorp.Main;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * La classe manipulant les objets JSON de Jackson, et gérant leurs sauvegarde.
 *
 * @author Luca et Erwan PECHON
 */
public class JsonFile {
  /** L'objet JSON à gérer. */
  private final JsonNode root;
  /** Le fichier de sauvegarde du JSON à gérer. */
  private final Path file;
  /** Peut-on modifié le fichier ?. */
  private boolean saveAuthorized = true;

  /**
   * Constructeur de la classe JsonFile.
   *
   * @param pathFile Chemin du fichier JSON (Si aucun fichier n'est donné, un JSON vide ({}) sera créer).
   * @param withLoad Faut-il chargé le contenu du fichier demandé ?
   * @param inRessource Faut-il cherché le fichier dans les ressources du jar ?
   * @throws IOException Excption jeté en cas de problème avec l'existance du fichier demander.
   * @throws RuntimeException Exception jeté en cas de problème lors de la création du JsonFile.
   */
  private JsonFile(String pathFile, boolean withLoad, boolean inRessource) throws IOException {
    if (pathFile == null) {
      throw new RuntimeException("Aucun fichier n'à était renseigné.");
    }

    // Obtenir le chemin vers le fichier demandé
    if (inRessource) {
      URL url = Main.class.getResource(pathFile);
      if (url == null) {
        throw new RuntimeException("Impossible de récupérer la ressource demandé.");
      }
      this.saveAuthorized = false;
      try {
        this.file = Paths.get(url.toURI());
      } catch (URISyntaxException e) {
        throw new RuntimeException("Invalid URL format", e);
      }
    } else {
      this.file = Paths.get(pathFile);
    }
    // Vérifier que le chemin est était obtenu
    if (this.file == null) {
      throw new RuntimeException("Impossible d'accéder au fichier demandé.");
    }
    if (withLoad) {
      if (!(Files.exists(this.file))) {
        throw new IOException("Le fichier demandé n'existe pas : " + this.file);
      }
    } else {
      if (Files.exists(this.file)) {
        throw new IOException("Le fichier demandé existe déjà : " + this.file);
      }
    }

    String sourceData;

    // Obtenir le JSON
    try {
      if (withLoad) {
        sourceData = Files.lines(this.file).collect(Collectors.joining());
      } else {
        sourceData = "{}";
        Files.createFile(this.file);
      }
    } catch (IOException e) {
      throw new RuntimeException("Error reading file", e);
    }

    try {
      ObjectMapper mapper = new ObjectMapper();
      this.root = mapper.readTree(sourceData);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Charge le contenu d'un fichier JSON, ce trouvant dans les ressources.
   *
   * @param pathFile Chemin du fichier JSON.
   * @return L'objet JsonFile créer, avec le JSON demandé chargé à l'intérieur.
   */
  public static JsonFile loadRessource(String pathFile) {
    try {
      return new JsonFile(pathFile, true, true);
    } catch (IOException e) {
      throw new RuntimeException("La ressource demandé ne semble pas exister.", e);
    }
  }

  /**
   * Charge le contenu d'un fichier JSON.
   *
   * @param pathFile Chemin du fichier JSON.
   * @return L'objet JsonFile créer, avec le JSON demandé chargé à l'intérieur.
   * @throws IOException Exception jeté en cas d'inexistance du fichier demander.
   */
  public static JsonFile load(String pathFile) throws IOException {
    return new JsonFile(pathFile, true, false);
  }

  /**
   * Crée un nouveau fichier JSON.
   *
   * @param pathFile Chemin du fichier JSON.
   * @return L'objet JsonFile créer, avec le JSON demandé chargé à l'intérieur.
   */
  public static JsonFile create(String pathFile) {
    try {
      return new JsonFile(pathFile, false, false);
    } catch (IOException e) {
      throw new RuntimeException("Le fichier demandé existe déjà.", e);
    }
  }

  /**
   * Navigue dans le JSON, jusqu'au noeud demandé.
   *
   * @param path Le chemin de la racine au noeud (champs1.indiceListe.champs3)
   * @return Le chemin dans une liste.
   */
  private List<String> getPath(String path) {
    if (!path.matches("^[a-zA-Z0-9.]+$")) {
      throw new RuntimeException("Invalid path (" + path + "). Must be [a-zA-Z0-9.]+ (node1.node2.node3...)");
    }
    return Arrays.asList(path.split("\\."));
  }

  /**
   * Obtient le noeud voulut.
   *
   * @param path  Chemin du tableau JSON.
   * @return Le noeud demandé.
   * @throws NoSuchElementException Avertit de l'accès à la valeur d'une clé n'existant pas.
   */
  private JsonNode getNode(String path) throws NoSuchElementException {
    String p = null;
    String pathPassed = null;
    JsonNode node = this.root;

    Iterator<String> it = getPath(path).iterator();
    while (it.hasNext()) {
      p = it.next();
      node = node.get(p);
      if (node == null) {
        String pathBreak = p;
        while (it.hasNext()) {
          pathBreak += "." + it.next();
        }
        throw new NoSuchElementException("Le chemin demandé est cassé : " + pathPassed + "||" + pathBreak);
      } else {
        if (pathPassed == null) {
          pathPassed = p;
        } else {
          pathPassed += "." + p;
        }
      }
    }

    return node;
  }

  /**
   * Test si un chemin existe.
   *
   * @param path  Chemin à tester
   * @return null si le chemin existe, ou, le chemin jusqu'au premier noeud inexistant.
   */
  public String isExist(String path) {
    try {
      this.getNode(path);
    } catch (NoSuchElementException e) {
      String errorMessage = e.getMessage();
      if (errorMessage != null && errorMessage.contains("Le chemin demandé est cassé")) {
        // Analyser le message pour extraire pathPassed
        String[] parts = errorMessage.split("\\|\\|");
        if (parts.length == 2) {
          String pathInfo = parts[0];
          String[] pathInfoParts = pathInfo.split(": ");
          if (pathInfoParts.length == 2) {
            return pathInfoParts[1]; // Renvoit du contenu de pathPassed
          }
        }
      }
      throw new RuntimeException("Il semble y avoir un problème dans la lecture du fichier JSON. veuillez le recharger.");
    }
    return null;
  }

  /**
   * Récupère un tableau JSON à partir d'un chemin donné.
   *
   * @param path  Chemin du tableau JSON.
   * @param clazz Classe des éléments du tableau.
   * @return Le tableau JSON.
   */
  public <T> List<T> getArray(String path, Class<? extends T> clazz) throws NoSuchElementException {
    JsonNode node = getNode(path);
    ObjectMapper mapper = new ObjectMapper();
    List lst = new ArrayList();
    try {
      for (JsonNode element : node) {
        lst.add(mapper.treeToValue(element, clazz));
      }
    } catch (IOException e) {
      throw new RuntimeException("Error converting JSON to array", e);
    }
    return lst;
  }

  /**
   * Récupère un tableau de booléens à partir d'un chemin donné.
   *
   * @param path Chemin du tableau de booléens.
   * @return Le tableau de booléens.
   */
  public boolean[] getBinary(String path) throws NoSuchElementException {
    JsonNode node = getNode(path);
    if (!node.isArray()) {
      throw new RuntimeException("Node at path " + path + " is not an array");
    }

    boolean[] binaryArray = new boolean[node.size()];
    for (int i = 0; i < node.size(); i++) {
      binaryArray[i] = node.get(i).asBoolean();
    }
    return binaryArray;
  }

  /**
   * Récupère un booléen à partir d'un chemin donné.
   *
   * @param path Chemin du booléen.
   * @return Le booléen.
   */
  public boolean getBoolean(String path) throws NoSuchElementException {
    return getNode(path).asBoolean();
  }

  /**
   * Vérifie si la valeur à un chemin donné est nulle.
   *
   * @param path Chemin de la valeur.
   * @return true si la valeur est nulle, sinon false.
   */
  public boolean isNull(String path) throws NoSuchElementException {
    return getNode(path).isNull();
  }

  /**
   * Récupère une valeur à partir d'un chemin donné.
   *
   * @param path  Chemin de la valeur.
   * @param clazz Classe de la valeur.
   * @return La valeur numérique.
   */
  public <T> T getValue(String path, Class<? extends T> clazz) throws NoSuchElementException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = getNode(path);
    try {
      return mapper.treeToValue(node, clazz);
    } catch (Exception e) {
      throw new RuntimeException("Impossible de récupérer le " + clazz.getName(), e);
    }
  }

  /**
   * Récupère un entier à partir d'un chemin donné.
   *
   * @param path Chemin de l'entier.
   * @return L'entier.
   */
  public int getInt(String path) throws NoSuchElementException {
    return getNode(path).asInt();
  }

  /**
   * Récupère un objet JSON à partir d'un chemin donné et le mappe vers une classe.
   *
   * @param path  Chemin de l'objet JSON.
   * @param instance Une instance qui sera modifié afin de récupérer le contenu du JSON.
   */
  public <T> void getJson(String path, T instance) throws NoSuchElementException {
    JsonNode node = getNode(path);
    if (node.getNodeType() != JsonNodeType.OBJECT) {
      throw new RuntimeException("Node at path " + path + " is not a POJONode");
    }

    ObjectMapper mapper = new ObjectMapper();

    Field[] fields = instance.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (node.has(field.getName())) {
        try {
          field.setAccessible(true);
          Object value = mapper.treeToValue(node.get(field.getName()), field.getType());
          field.set(instance, value);
        } catch (Exception e) {
          throw new RuntimeException("Error setting field value", e);
        }
      }
    }
  }

  /**
   * Récupère une chaîne de caractères à partir d'un chemin donné.
   *
   * @param path Chemin de la chaîne de caractères.
   * @return Lachaîne de caractères.
   */
  public String getString(String path) throws NoSuchElementException {
    return getNode(path).asText();
  }

  /**
   * Formate l'objet pour l'affichage dans le terminal.
   *
   * @return L'objet formater pour l'affichage.
   */
  public String toString() {
    return "File : " + this.file
      + ((this.saveAuthorized) ? ("?withSave") : (""))
      + "\n"
      + this.root.toString();
  }
}

