package fr.mcgcorp.managers;

import fr.mcgcorp.managers.OsManager.OsType;
import java.nio.file.Path;

/**
 * Cette classe est utilisée pour gérer les sauvegardes.
 *
 * @version 1.0
 * @since 1.0
 * @see OsManager
 * @author POURCEAU Luca, PUREN Mewen
 */
public class SaveManager {

  /**
   * Le singleton de la classe SaveManager.
   */
  private static final SaveManager instance = new SaveManager();
  /**
   * Le type de système d'exploitation actuel.
   */
  private final OsType osType = OsManager.getInstance().getOsType();
  /**
   * Le dossier de sauvegarde.
   */
  private Path saveDir;
  
  /**
   * Constructeur de la classe SaveManager.
   */
  private SaveManager() {
    setSaveDir();
    initSaveDir();
    initFiles();
  }

  /**
   * Définir le dossier de sauvegarde.
   */
  private void setSaveDir() {
    this.saveDir =  switch (this.osType) {
      case WINDOWS -> Path.of(System.getenv("APPDATA"), "Kakuro");
      case MAC -> Path.of(System.getProperty("user.home"), "Library", "Application Support", "Kakuro");
      default -> Path.of(System.getProperty("user.home"), ".game", "Kakuro");
    };
  }

  /**
   * Initialiser le dossier de sauvegarde.
   */
  private void initSaveDir() {
    if (!this.saveDir.toFile().exists()) {
      if (!this.saveDir.toFile().mkdirs()) {
        throw new RuntimeException("Impossible de créer le dossier de sauvegarde.");
      }
    }
  }

  /**
   * Initialiser les fichiers de sauvegarde.
   */
  private void initFiles() {
    Path profilesDir = this.saveDir.resolve("profiles");
    if (!profilesDir.toFile().exists()) {
      if (!profilesDir.toFile().mkdirs()) {
        throw new RuntimeException("Impossible de créer le dossier de sauvegarde des profils.");
      }
    }
    Path scoresDir = this.saveDir.resolve("scores");
    if (!scoresDir.toFile().exists()) {
      if (!scoresDir.toFile().mkdirs()) {
        throw new RuntimeException("Impossible de créer le dossier de sauvegarde des scores.");
      }
    }
  }

  /**
   * Obtenir l'instance de SaveManager.
   *
   * @return l'instance de SaveManager.
   */
  public static SaveManager getInstance() {
    return instance;
  }

  /**
   * Obtenir le dossier de sauvegarde.
   *
   * @return le dossier de sauvegarde.
   */
  public Path getSaveDir() {
    return this.saveDir;
  }

  /**
   * Obtenir le dossier des profils.
   *
   * @return le dossier des profils.
   */
  public Path getProfileDir() {
    return this.saveDir.resolve("profiles");
  }

  /**
   * Obtenir le dossier des scores.
   *
   * @return le dossier des scores.
   */
  public Path getScoresDir() {
    return this.saveDir.resolve("scores");
  }
}