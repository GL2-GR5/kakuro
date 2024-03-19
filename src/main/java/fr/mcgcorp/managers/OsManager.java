package fr.mcgcorp.managers;

/**
 * This class is used to get the current OS type.
 *
 * @version 1.0
 * @since 1.0
 * @see OsType
 */
public class OsManager {

  /**
   * The instance of the OsManager.
   */
  private static final OsManager instance = new OsManager();

  /**
   * The current OS type.
   */
  private final OsType osType;

  /**
   * The constructor of the OsManager.
   */
  private OsManager() {
    String os = System.getProperty("os.name").toLowerCase();
    osType = OsType.WINDOWS.getOsByName(os);
  }

  /**
   * Get the instance of the OsManager.
   *
   * @return the instance of the OsManager.
   */
  public static OsManager getInstance() {
    return instance;
  }

  /**
   * Get the current OS type.
   *
   * @return the current OS type.
   */
  public OsType getOsType() {
    return osType;
  }

  /**
   * The OS type enum.
   */
  public enum OsType {

    /**
     * Windows OS.
     */
    WINDOWS("win"),
    /**
     * Mac OS.
     */
    MAC("mac"),
    /**
     * Linux OS.
     */
    LINUX("nix"),
    /**
     * Solaris OS.
     */
    SOLARIS("nux");

    /**
     * The constructor of the OsType.
     *
     * @param key the key of the OS type.
     */
    OsType(String key) {
      this.key = key;
    }

    /**
     * Key of the OS type.
     */
    private final String key;

    /**
     * Get the OS type by the name.
     *
     * @param name the name of the OS.
     * @return the OS type.
     */
    private OsType getOsByName(String name) {
      for (OsType os : OsType.values()) {
        if (name.contains(os.key)) {
          return os;
        }
      }
      return null;
    }
  }
}
