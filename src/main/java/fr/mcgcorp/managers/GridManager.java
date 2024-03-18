package fr.mcgcorp.managers;

public class GridManager {

  private static final GridManager instance = new GridManager();
  private final JsonFile file = new JsonFile("grids.json");

  public static GridManager getInstance() {
    return instance;
  }

  private GridManager() {
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