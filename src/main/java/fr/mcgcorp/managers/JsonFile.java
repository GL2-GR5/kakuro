package fr.mcgcorp.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
  /** Le controlleur des fichiers JSON. */
  private final ObjectMapper mapper;
  /** Le noeud pere de ce noeud. */
  private final JsonFile father;

  /**
   * Constructeur de la classe JsonFile (utilise les fichiers de ressource).
   *
   * @param father Le noeud à parcourire.
   * @param path Chemin vers le noeud demandé.
   * @throws IOException Exception jeté en cas de problème avec l'existance du fichier demander.
   * @throws RuntimeException Exception jeté en cas de problème lors de la création du JsonFile.
   */
  public JsonFile(JsonFile father, String path) throws NoSuchElementException {
    this.root = father.getNode(path);
    this.file = father.file;
    this.mapper = father.mapper;
    this.father = father;
  }

  /**
   * Constructeur de la classe JsonFile (utilise les fichiers de ressource).
   *
   * @param pathFile Chemin du fichier JSON (dans les ressources).
   * @throws IOException Exception jeté en cas de problème avec l'existance du fichier demander.
   * @throws RuntimeException Exception jeté en cas de problème lors de la création du JsonFile.
   */
  public JsonFile(String pathFile) throws IOException {
    this.file = null;
    this.father = null;
    if (pathFile == null) {
      throw new RuntimeException("Aucun fichier n'à était renseigné.");
    }
    // Obtenire le chemin du fichier.
    URL url = Main.class.getResource(pathFile);
    if (url == null) {
      throw new RuntimeException("Impossible de récupérer la ressource demandé." + pathFile);
    }
    Path file = null;
    try {
      file = Paths.get(url.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException("Invalid URL format", e);
    }
    // Vérifier l'obtention du fichier
    if (file == null) {
      throw new RuntimeException("Impossible d'accéder au fichier demandé." + url);
    }
    if (!(Files.exists(file))) {
      throw new IOException("Le fichier demandé n'existe pas : " + file);
    }
    // Obtenir le JSON
    String sourceData;
    try {
      sourceData = Files.lines(file).collect(Collectors.joining());
    } catch (IOException e) {
      throw new RuntimeException("Error reading file", e);
    }
    this.mapper = new ObjectMapper();
    try {
      this.root = this.mapper.readTree(sourceData);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Constructeur de la classe JsonFile (utilise les fichiers de
   * l'utilisateur).
   *
   * @param file Chemin du fichier JSON (fichier dans ceux de l'utilisateur).
   * @param forEdit Est-ce pour l'éditer ?
   * @throws IOException Exception jeté en cas de problème avec l'existance du fichier demander.
   * @throws RuntimeException Exception jeté en cas de problème lors de la création du JsonFile.
   */
  public JsonFile(Path file, boolean forEdit) throws IOException {
    this.father = null;
    if (file == null) {
      throw new RuntimeException("Aucun fichier n'à était renseigné.");
    }
    // Vérifier l'existance du fichier
    boolean creerFichier = false;
    if (forEdit) {
      this.file = file;
      if (!(Files.exists(file))) {
        creerFichier = true;
      }
    } else {
      this.file = null;
      if (!(Files.exists(file))) {
        throw new IOException("Le fichier demandé n'existe pas : " + file);
      }
    }
    // Obtenir le JSON
    String sourceData;
    try {
      if (creerFichier) {
        sourceData = "{}";
        Files.createFile(file);
      } else {
        sourceData = Files.lines(file).collect(Collectors.joining());
      }
    } catch (IOException e) {
      throw new RuntimeException("Error reading file", e);
    }
    this.mapper = new ObjectMapper();
    try {
      this.root = this.mapper.readTree(sourceData);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Si le noeud racine de ce JsonFile fait partie d'un ConteneurNode, renvoit le JsonFile associé.
   *
   * @return Le JsonFile contenant ce JsonFile (si il existe).
   */
  public JsonFile getFather() {
    return this.father;
  }

  /*
   * Renvoit un JsonFile gérant une partie de ce JsonFile (permet d'en simplifier la gestion)
   *
   * @param path Le chemin jusqu'au ConteneurNode au-quel ce déplacer.
   * @return Le JsonNode fils.
   */
  public JsonFile getChildren(String path) {
    return new JsonFile(this, path);
  }

  /**
   * Renvoit le fichier de sauvegarde.
   *
   * @return Le fichier de sauvegarde (si en mode édition)
   */
  public Path getFile() {
    return this.file;
  }

  /**
   * Navigue dans le JSON, jusqu'au noeud demandé.
   *
   * @param path Le chemin de la racine au noeud (champs1.indiceListe.champs3)
   * @return Le chemin dans une liste.
   */
  private List<String> getPath(String path) {
    if ((path == null) || (path == "") || (path == ".")) {
      return new ArrayList<>();
    }
    if (!path.matches("^[a-zA-Z0-9.]+$")) {
      throw new RuntimeException("Invalid path (" + path + "). Must be [a-zA-Z0-9.]+ (node1.node2.node3...)");
    }
    List<String> list = Arrays.asList(path.split("\\."));
    while (!list.isEmpty() && list.get(0).isEmpty()) {
      list.remove(0);
    }
    return list;
  }

  /*
   * Permet de s'éparer le dernier élément de l'adresse donnée.
   *
   * @param path Le chemin à diviser.
   * @return Le chemin (sans le dernier noeud) suivit du dernier noeud.
   */
  private String[] getLastField(String path) {
    int lastIndex = path.lastIndexOf('.');
    String[] res = new String[2];
    res[0] = path.substring(lastIndex + 1);
    res[1] = path.substring(0, lastIndex);
    return res;
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

    Iterator<String> it = this.getPath(path).iterator();
    while (it.hasNext()) {
      p = it.next();
      JsonNode newNode = node.get(p);
      if (newNode == null) {
        // null est renvoyé si node n'est pas un JsonObject ou si p n'y existe pas.
        try {
          newNode = node.get(Integer.parseInt(p));
        } catch (Exception e) {
          newNode = null;
        }
        if (newNode == null) {
          // null est renvoyé si p n'existe pas.
          String pathBreak = p;
          while (it.hasNext()) {
            pathBreak += "." + it.next();
          }
          throw new NoSuchElementException("Le chemin demandé est cassé : " + pathPassed + "||" + pathBreak);
        }
      }
      node = newNode;
      if (pathPassed == null) {
        pathPassed = p;
      } else {
        pathPassed += "." + p;
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
            return pathInfoParts[1] + "<-break"; // Renvoit du contenu de pathPassed
          }
        }
      }
      throw new RuntimeException("Il semble y avoir un problème dans la lecture du fichier JSON. veuillez le recharger.");
    }
    return null;
  }

  /**
   * Obtient le nom des champs d'un ConteneurJson.
   */
  public String[] getFields(String path) {
    JsonNode node = this.getNode(path);
    List<String> lstFields = new ArrayList<>();
    if (node.isObject()) {
      ObjectNode objectNode = (ObjectNode) node;
      Iterator<String> fieldNames = objectNode.fieldNames();
      while (fieldNames.hasNext()) {
        lstFields.add(fieldNames.next());
      }
    } else if (node.isArray()) {
      ArrayNode arrayNode = (ArrayNode) node;
      Iterator<JsonNode> elements = arrayNode.elements();
      int i = 0;
      while (elements.hasNext()) {
        elements.next();
        lstFields.add("" + (i++));
      }
    }
    return lstFields.toArray(new String[0]);
  }

  /**
   * Vérifie si la valeur à un chemin donné est nulle.
   *
   * @param path Chemin de la valeur.
   * @return true si la valeur est nulle, sinon false.
   */
  public boolean isNull(String path) throws NoSuchElementException {
    return this.getNode(path).isNull();
  }

  /**
   * Récupère un booléen à partir d'un chemin donné.
   *
   * @param path Chemin du booléen.
   * @return Le booléen.
   */
  public boolean getBoolean(String path) throws NoSuchElementException {
    return this.getNode(path).asBoolean();
  }

  /**
   * Récupère un entier à partir d'un chemin donné.
   *
   * @param path Chemin de l'entier.
   * @return L'entier.
   */
  public int getInt(String path) throws NoSuchElementException {
    return this.getNode(path).asInt();
  }

  /**
   * Récupère une chaîne de caractères à partir d'un chemin donné.
   *
   * @param path Chemin de la chaîne de caractères.
   * @return Lachaîne de caractères.
   */
  public String getString(String path) throws NoSuchElementException {
    return this.getNode(path).asText();
  }

  /**
   * Récupère un tableau de byte à partir d'un chemin donné.
   *
   * @param path Chemin du tableau de booléens.
   * @return Le tableau de booléens.
   */
  public byte[] getBinary(String path) throws NoSuchElementException {
    try {
      JsonNode node = this.getNode(path);
      byte[] binary = node.binaryValue();
      return Arrays.copyOf(binary, binary.length);
    } catch (IOException e) {
      // Impossible de lire les donnée binaire.
      return null;
    }
  }

  /**
   * Récupère un tableau JSON à partir d'un chemin donné.
   *
   * @param path  Chemin du tableau JSON.
   * @param clazz Classe des éléments du tableau.
   * @return Le tableau JSON.
   */
  public <T> List<T> getArray(String path, Class<? extends T> clazz) throws NoSuchElementException {
    JsonNode node = this.getNode(path);
    List<T> lst = new ArrayList<>();
    try {
      for (JsonNode element : node) {
        lst.add(this.mapper.treeToValue(element, clazz));
      }
    } catch (IOException e) {
      throw new RuntimeException("Error converting JSON to array", e);
    }
    return lst;
  }

  /**
   * Récupère une valeur à partir d'un chemin donné.
   *
   * @param path  Chemin de la valeur.
   * @param clazz Classe de la valeur.
   * @return La valeur numérique.
   */
  public <T> T getValue(String path, Class<? extends T> clazz) throws NoSuchElementException {
    JsonNode node = this.getNode(path);
    try {
      return this.mapper.treeToValue(node, clazz);
    } catch (Exception e) {
      throw new RuntimeException("Impossible de récupérer le " + clazz.getName(), e);
    }
  }

  /**
   * Récupère un objet JSON à partir d'un chemin donné et le mappe vers une classe.
   *
   * @param path  Chemin de l'objet JSON.
   * @param instance Une instance qui sera modifié afin de récupérer le contenu du JSON.
   */
  public <T> void getJson(String path, T instance) throws NoSuchElementException {
    JsonNode node = this.getNode(path);
    if (node.getNodeType() != JsonNodeType.OBJECT) {
      throw new RuntimeException("Node at path " + path + " is not a POJONode");
    }

    Field[] fields = instance.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (node.has(field.getName())) {
        try {
          field.setAccessible(true);
          Object value = this.mapper.treeToValue(node.get(field.getName()), field.getType());
          field.set(instance, value);
        } catch (Exception e) {
          throw new RuntimeException("Error setting field value", e);
        }
      }
    }
  }

  /**
   * Permet de suprimer un noeud du JsonFile.
   *
   * @param path Le chemin vers le noeud à supprimer.
   * @return Indique si la suppression à réussie.
   */
  public boolean removeNode(String path) throws NoSuchElementException {
    if (this.file == null) {
      return false;
    }
    String[] pathCut = this.getLastField(path);
    JsonNode nodePere = this.getNode(pathCut[0]);
    if (nodePere.isObject()) {
      ObjectNode node = (ObjectNode) nodePere;
      node.remove(pathCut[1]);
      return true;
    } else if (nodePere.isArray()) {
      ArrayNode node = (ArrayNode) nodePere;
      int index = Integer.parseInt(pathCut[1]);
      node.remove(index);
    } else {
      throw new NoSuchElementException("Le champs à supprimer (" + pathCut[1] + ") n'est pas dans un noeud modifiable (Object ou Array).");
    }
    return false;
  }

  /**
   * Permet de supprimer plusieurs noeuds du JsonFile.
   *
   * @param paths La liste des chemins vers les noeuds à supprimer.
   * @return Le nombre de suppression réussie.
   */
  public int removeNode(String[] paths) throws NoSuchElementException {
    int nbSuccess = 0;
    for (String path : paths) {
      if (this.removeNode(path)) {
        nbSuccess++;
      }
    }
    return nbSuccess;
  }

  /**
   * Modifie un noeud, ou le crée si il n'existe pas.
   *
   * @param path Le chemin vers le noeud à modifier.
   * @param value La valeur qui remplacera l'ancienne (si null, ne détruit pas le noeud).
   * @return Le chemin vers le noeud modifier ou creer.
   */
  private String setNode(String path, JsonNode value) throws NoSuchElementException {
    if (this.file == null) {
      return null;
    }
    String[] pathCut = this.getLastField(path);
    JsonNode nodePere = this.getNode(pathCut[0]);
    if (nodePere.isObject()) {
      ObjectNode node = (ObjectNode) nodePere;
      node.put(pathCut[1], value);
      return pathCut[1];
    } else if (nodePere.isArray()) {
      ArrayNode node = (ArrayNode) nodePere;
      int index = Integer.parseInt(pathCut[1]);
      if (node.has(index)) { // Modifier un élément du ArrayNode.
        int sizeBefore = node.size();
        node.set(index, value);
        return "" + sizeBefore;
      } else { // Ajouter un élément au ArrayNode.
        node.add(value);
      }
    } else {
      throw new NoSuchElementException("Le champs à modifier (" + pathCut[1] + ") n'est pas dans un noeud modifiable (Object ou Array).");
    }
    return null;
  }

  /**
   * Modifie un noeud, ou le crée si il n'existe pas.
   * Le nouveau noeud sera un noeud null.
   *
   * @param path Le chemin vers le noeud à modifier.
   * @return Le chemin vers le noeud modifier ou creer.
   */
  public String set(String path) throws NoSuchElementException {
    // Créer le nouveau noeud.
    JsonNode node = this.mapper.nullNode();
    // Ajouter le noeud.
    return this.setNode(path, node);
  }

  /**
   * Modifie un noeud, ou le crée si il n'existe pas.
   * Le nouveau noeud sera un boolean.
   *
   * @param path Le chemin vers le noeud à modifier.
   * @param value La valeur qui remplacera l'ancienne (si null, ne détruit pas le noeud).
   * @return Le chemin vers le noeud modifier ou creer.
   */
  public String set(String path, boolean value) throws NoSuchElementException {
    // Créer le nouveau noeud.
    JsonNode node = this.mapper.convertValue(value, JsonNode.class);
    // Ajouter le noeud.
    return this.setNode(path, node);
  }

  /**
   * Modifie un noeud, ou le crée si il n'existe pas.
   * Le nouveau noeud contiendra un entier.
   *
   * @param path Le chemin vers le noeud à modifier.
   * @param value La valeur qui remplacera l'ancienne (si null, ne détruit pas le noeud).
   * @return Le chemin vers le noeud modifier ou creer.
   */
  public String set(String path, int value) throws NoSuchElementException {
    // Créer le nouveau noeud.
    JsonNode node = this.mapper.convertValue(value, JsonNode.class);
    // Ajouter le noeud.
    return this.setNode(path, node);
  }

  /**
   * Modifie un noeud, ou le crée si il n'existe pas.
   * Le nouveau noeud contiendra un texte.
   *
   * @param path Le chemin vers le noeud à modifier.
   * @param value La valeur qui remplacera l'ancienne (si null, ne détruit pas le noeud).
   * @return Le chemin vers le noeud modifier ou creer.
   */
  public String set(String path, String value) throws NoSuchElementException {
    // Créer le nouveau noeud.
    JsonNode node = (value != null) ? this.mapper.valueToTree(value) : this.mapper.nullNode();
    // Ajouter le noeud.
    return this.setNode(path, node);
  }

  /**
   * Modifie un noeud, ou le crée si il n'existe pas.
   * Le nouveau noeud sera un binary.
   *
   * @param path Le chemin vers le noeud à modifier.
   * @param value La valeur qui remplacera l'ancienne (si null, ne détruit pas le noeud).
   * @return Le chemin vers le noeud modifier ou creer.
   */
  public String set(String path, byte[] value) throws NoSuchElementException {
    // Créer le nouveau noeud.
    JsonNode node = (value != null) ? this.mapper.convertValue(value, JsonNode.class) : this.mapper.nullNode();
    // Ajouter le noeud.
    return this.setNode(path, node);
  }

  /**
   * Modifie un noeud, ou le crée si il n'existe pas.
   * Le nouveau noeud sera une liste.
   *
   * @param path Le chemin vers le noeud à modifier.
   * @param value La valeur qui remplacera l'ancienne (si null, crée une liste vide).
   * @return Le chemin vers le noeud modifier ou creer.
   */
  public String set(String path, List value) throws NoSuchElementException {
    // Créer le nouveau noeud.
    JsonNode node = (value != null) ? this.mapper.valueToTree(value) : this.mapper.nullNode();
    // Ajouter le noeud.
    return this.setNode(path, node);
  }

  /**
   * Modifie un noeud, ou le crée si il n'existe pas.
   * Le nouveau noeud contiendra un nombre.
   *
   * @param path Le chemin vers le noeud à modifier.
   * @param value La valeur qui remplacera l'ancienne (si null, ne détruit pas le noeud).
   * @return Le chemin vers le noeud modifier ou creer.
   */
  public <T extends Number> String set(String path, T value) throws NoSuchElementException {
    // Créer le nouveau noeud.
    JsonNode node = this.mapper.convertValue(value, JsonNode.class);
    // Ajouter le noeud.
    return this.setNode(path, node);
  }

  /**
   * Modifie un noeud, ou le crée si il n'existe pas.
   * Le nouveau noeud contiendra la valeur donnée en paramètre.
   *
   * @param path Le chemin vers le noeud à modifier.
   * @param value La valeur qui remplacera l'ancienne (si null, ne détruit pas le noeud).
   * @return Le chemin vers le noeud modifier ou creer.
   */
  public <T> String set(String path, T value) throws NoSuchElementException {
    // Créer le nouveau noeud.
    JsonNode node = (value != null) ? this.mapper.valueToTree(value) : this.mapper.nullNode();
    // Ajouter le noeud.
    return this.setNode(path, node);
  }

  /**
   * Formate l'objet pour l'affichage dans le terminal.
   *
   * @return L'objet formater pour l'affichage.
   */
  public String toString() {
    return "JsonFile :"
      + ((this.file == null) ? ("") : (" >Edit in " + this.file + '<'))
      + ((this.father == null) ? ("") : (" >Sous arbre<"))
      + "\n"
      + this.root.toString();
  }
}
