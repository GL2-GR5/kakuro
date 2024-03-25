package fr.mcgcorp.managers;

public class LangManager {

  private static final LangManager instance = new LangManager();

  private LangManager() {
  }

  public static LangManager getInstance() {
    return instance;
  }

  private Lang current = Lang.FRENCH;

  public Lang getCurrent() {
    return current;
  }

  public void setCurrent(Lang current) {
    this.current = current;
  }

  public String getText(String key) {
    return "not implemented yet";
  }

  public enum Lang {
    FRENCH("Français", "fr"),
    ENGLISH("English", "en");

    private final String displayName;
    private final String code;

    private Lang(String displayName, String code) {
      this.displayName = displayName;
      this.code = code;
    }

    public String getDisplayName() {
      return displayName;
    }
  }
}
