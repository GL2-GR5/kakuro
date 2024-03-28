package fr.mcgcorp.managers;

import fr.mcgcorp.managers.JsonFile;
import java.io.IOException;

/**
 * Cette classe est utilisée pour gérer les grilles de jeu.
 *
 * @version 1.0
 * @since 1.0
 */
public class GridManager {
  /**
   * Le singleton de la classe GridManager.
   */
  private static final GridManager instance = new GridManager();
  /**
   * Le fichier JSON contenant les grilles de jeu.
   */
  private final JsonFile file;

  /**
   * Obtenir l'instance de GridManager.
   *
   * @return l'instance de GridManager.
   */
  public static GridManager getInstance() {
    return instance;
  }

  /**
   * Constructeur de la classe GridManager.
   */
  private GridManager() {
    JsonFile file = null;
    /**
     * Essayer de créer un fichier JSON contenant les grilles de jeu.
     */
    try {
      file = new JsonFile("grids.json");
    } catch (IOException e) {
      e.printStackTrace();
      file = null;
    }
    this.file = file;
  }

  /**
   * Obtenir une grille de jeu par son identifiant.
   *
   * @param id l'identifiant de la grille de jeu.
   * @return la grille de jeu.
   */
  public String getGridById(int id) {
    return file.getString("grids." + id + ".serialized");
  }

  /**
   * Obtenir une grille de jeu par sa difficulté.
   *
   * @param difficulty la difficulté de la grille de jeu.
   * @return la grille de jeu.
   */
  public enum GridDifficulty {
    EASY("easy"), MEDIUM("medium"), HARD("hard");

    /**
     * La clé de la difficulté.
     */
    private final String key;

    /**
     * Constructeur de la classe GridDifficulty.
     *
     * @param key la clé de la difficulté.
     */
    GridDifficulty(String key) {
      this.key = key;
    }

    /**
     * Obtenir la clé de la difficulté.
     *
     * @return la clé de la difficulté.
     */
    private String getKey() {
      return key;
    }
  }
}
