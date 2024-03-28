package fr.mcgcorp.managers;

import fr.mcgcorp.managers.JsonFile;
import java.io.IOException;

public class GridManager {
  private static final GridManager instance = new GridManager();
  private final JsonFile file;

  public static GridManager getInstance() {
    return instance;
  }

  private GridManager() {
    JsonFile file = null;
    try {
      file = new JsonFile("grids.json");
    } catch (IOException e) {
      e.printStackTrace();
      file = null;
    }
    this.file = file;
  }

  public String getGridById(int id) {
    return file.getString("grids." + id + ".serialized");
  }

  public enum GridDifficulty {
    EASY("easy"), MEDIUM("medium"), HARD("hard");

    private final String key;

    GridDifficulty(String key) {
      this.key = key;
    }

    private String getKey() {
      return key;
    }
  }
}
