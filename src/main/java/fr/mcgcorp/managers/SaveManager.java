package fr.mcgcorp.managers;

import fr.mcgcorp.managers.OsManager.OsType;
import java.nio.file.Path;

public class SaveManager {

  public static void main(String[] args) {
    SaveManager saveManager = SaveManager.getInstance();
  }

  private static final SaveManager instance = new SaveManager();
  private final OsType osType = OsManager.getInstance().getOsType();
  private final Path saveDir;
  private SaveManager() {
    this.saveDir = getSaveDir();
    initSaveDir();
    initFiles();
  }

  private Path getSaveDir() {
    return switch (this.osType) {
      case WINDOWS -> Path.of(System.getenv("APPDATA"), "Kakuro");
      case LINUX -> Path.of(System.getProperty("user.home"), ".kakuro");
      case MAC -> Path.of(System.getProperty("user.home"), "Library", "Application Support", "Kakuro");
      default -> Path.of(System.getProperty("user.home"), "Kakuro");
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
}