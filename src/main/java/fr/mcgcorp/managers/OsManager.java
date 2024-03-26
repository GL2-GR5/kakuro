package fr.mcgcorp.managers;

/**
 * Cette classe est utilisée pour obtenir le type de système d'exploitation actuel.
 *
 * @version 1.0
 * @since 1.0
 * @see OsType
 */
public class OsManager {

  /**
   * Le singleton de la classe OsManager.
   */
  private static final OsManager instance = new OsManager();

  /**
   * Le type de système d'exploitation actuel.
   */
  private final OsType osType;

  /**
   * Constructeur de la classe OsManager.
   */
  private OsManager() {
    String os = System.getProperty("os.name").toLowerCase();
    osType = OsType.WINDOWS.getOsByName(os);
  }

  /**
   * Obtenir l'instance de OsManager.
   *
   * @return l'instance de OsManager.
   */
  public static OsManager getInstance() {
    return instance;
  }

  /**
   * Obtenir le type de système d'exploitation actuel.
   *
   * @return le type de système d'exploitation actuel.
   */
  public OsType getOsType() {
    return osType;
  }

  /**
   * Les types de système d'exploitation.
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
     * Constructeur de la classe OsType.
     *
     * @param key la clé du type de système d'exploitation.
     */
    OsType(String key) {
      this.key = key;
    }

    /**
     * La clé du type de système d'exploitation.
     */
    private final String key;

    /**
     * Obtenir le type de système d'exploitation par son nom.
     *
     * @param name le nom du système d'exploitation.
     * @return le type de système d'exploitation.
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
