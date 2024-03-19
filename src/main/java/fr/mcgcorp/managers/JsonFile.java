package fr.mcgcorp.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.POJONode;
import fr.mcgcorp.Main;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * La classe manipulant les objets JSON de Jackson, et gérant leurs sauvegarde.
 */
public class JsonFile {
  /** L'objet JSON à gérer. */
  private final JsonNode root;
  /** Le fichier de sauvegarde du JSON à gérer. */
  private String file;

  /**
   * Constructeur de la classe JsonFile.
   *
   * @param pathFile Chemin du fichier JSON (Si aucun fichier n'est donné, un JSON vide ({}) sera créer).
   */
  private JsonFile(String pathFile) {
    URL url = Main.class.getResource(pathFile);
    ObjectMapper mapper = new ObjectMapper();

    String sourceData;

    try {
      if (url == null) {
        sourceData = "{}";
      } else {
        sourceData = Files.lines(Paths.get(url.toURI())).collect(Collectors.joining());
        this.file = pathFile;
      }
    } catch (IOException e) {
      throw new RuntimeException("Error reading file", e);
    } catch (URISyntaxException e) {
      throw new RuntimeException("Invalid URL format", e);
    }

    try {
      this.root = mapper.readTree(sourceData);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Charge le contenu d'un fichier JSON.
   *
   * @param pathFile Chemin du fichier JSON.
   */
  public static JsonFile load(String pathFile) {
    if (pathFile == null) {
      throw new RuntimeException("Aucun fichier n'à était renseigné.");
    }
    return new JsonFile(pathFile);
  }

  /**
   * Crée un nouveau fichier JSON.
   *
   * @param pathFile Chemin du fichier JSON.
   */
  public static JsonFile create(String pathFile) {
    JsonFile o = new JsonFile(null);
    o.file = pathFile;
    return o;
  }

  /**
    * Navigue dans le JSON, jusqu'au noeud demandé.
    *
    * @param path Le chemin de la racine au noeud (champs1.indiceListe.champs3)
    */
  private List<String> getPath(String path) {
    if (!path.matches("^[a-zA-Z0-9.]+$")) {
      throw new RuntimeException("Invalid path (" + path + "). Must be [a-zA-Z0-9.]+ (node1.node2.node3...)");
    }
    return Arrays.asList(path.split("\\."));
  }

  /*
   * Obtient le noeud voulut.
   */
  private JsonNode getNode(String path) {
    List<String> paths = getPath(path);
    JsonNode node = root;

    for (String p : paths) {
      node = node.get(p);
    }

    return node;
  }

  /**
   * Récupère un tableau JSON à partir d'un chemin donné.
   *
   * @param path  Chemin du tableau JSON.
   * @param clazz Classe des éléments du tableau.
   * @return Le tableau JSON.
   */
  public <T> List<T> getArray(String path, Class<? extends T> clazz) {
    JsonNode node = getNode(path);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return Arrays.asList(mapper.treeToValue(node, clazz));
    } catch (IOException e) {
      throw new RuntimeException("Error converting JSON to array", e);
    }
  }

  /**
   * Récupère un tableau de booléens à partir d'un chemin donné.
   *
   * @param path Chemin du tableau de booléens.
   * @return Le tableau de booléens.
   */
  public boolean[] getBinary(String path) {
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
  public boolean getBoolean(String path) {
    return getNode(path).asBoolean();
  }

  /**
   * Vérifie si la valeur à un chemin donné est nulle.
   *
   * @param path Chemin de la valeur.
   * @return true si la valeur est nulle, sinon false.
   */
  public boolean isNull(String path) {
    return getNode(path).isNull();
  }

  /**
   * Récupère une valeur numérique à partir d'un chemin donné.
   *
   * @param path  Chemin de la valeur numérique.
   * @param clazz Classe de la valeur numérique.
   * @return La valeur numérique.
   */
  public <T extends Number> T getNumeric(String path, Class<? extends T> clazz) {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = getNode(path);
    try {
      return mapper.treeToValue(node, clazz);
    } catch (Exception e) {
      throw new RuntimeException("Impossible de récupérer le nombre", e);
    }
  }

  /**
   * Récupère un entier à partir d'un chemin donné.
   *
   * @param path Chemin de l'entier.
   * @return L'entier.
   */
  public int getInt(String path) {
    return getNode(path).asInt();
  }

  /**
   * Récupère un objet JSON à partir d'un chemin donné et le mappe vers une classe.
   *
   * @param path  Chemin de l'objet JSON.
   * @param clazz Classe de l'objet Java.
   * @return L'objet Java rempli avec les données de l'objet JSON.
   */
  public <T> T getJson(String path, Class<T> clazz) {
    JsonNode node = getNode(path);
    if (!(node instanceof POJONode)) {
      throw new RuntimeException("Node at path " + path + " is not a POJONode");
    }

    ObjectMapper mapper = new ObjectMapper();
    T instance;

    try {
      instance = clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Error creating instance of class", e);
    }

    POJONode pojoNode = (POJONode) node;

    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (pojoNode.has(field.getName())) {
        try {
          field.setAccessible(true);
          Object value = mapper.treeToValue(pojoNode.get(field.getName()), field.getType());
          field.set(instance, value);
        } catch (Exception e) {
          throw new RuntimeException("Error setting field value", e);
        }
      }
    }

    return instance;
  }

  /**
   * Récupère une chaîne de caractères à partir d'un chemin donné.
   *
   * @param path Chemin de la chaîne de caractères.
   * @return Lachaîne de caractères.
   */
  public String getString(String path) {
    return getNode(path).asText();
  }
}

