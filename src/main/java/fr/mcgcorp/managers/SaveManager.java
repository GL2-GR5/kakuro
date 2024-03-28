package fr.mcgcorp.managers;

import fr.mcgcorp.managers.OsManager.OsType;
import java.nio.file.Path;

public class SaveManager {

  private static final SaveManager instance = new SaveManager();
  private final OsType osType = OsManager.getInstance().getOsType();
  private Path saveDir;
  private SaveManager() {
    setSaveDir();
    initSaveDir();
    initFiles();
  }

  private void setSaveDir() {
    this.saveDir =  switch (this.osType) {
      case WINDOWS -> Path.of(System.getenv("APPDATA"), "Kakuro");
      case MAC -> Path.of(System.getProperty("user.home"), "Library", "Application Support", "Kakuro");
      default -> Path.of(System.getProperty("user.home"), ".game", "Kakuro");
    };
  }

  private void initSaveDir() {
    if (!this.saveDir.toFile().exists()) {
      if (!this.saveDir.toFile().mkdirs()) {
        throw new RuntimeException("Impossible de créer le dossier de sauvegarde.");
      }
    }
  }

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

  public static SaveManager getInstance() {
    return instance;
  }

  public Path getSaveDir() {
    return this.saveDir;
  }

  public Path getProfileDir() {
    return this.saveDir.resolve("profiles");
  }

  public Path getScoresDir() {
    return this.saveDir.resolve("scores");
  }
}