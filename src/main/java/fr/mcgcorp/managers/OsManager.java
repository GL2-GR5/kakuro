package fr.mcgcorp.managers;

/**
 * Classe permettant de gérer l'OS utilisé.
 *
 * @author Luca POURCEAU
 */
public class OsManager {

  /**
   * Instance de la classe.
   */
  private static OsManager instance;

  /**
   * Méthode permettant de récupérer l'instance de la classe.
   *
   * @return l'instance de la classe
   */
  public static OsManager getInstance() {
    if (instance == null) {
      instance = new OsManager();
    }
    return instance;
  }

  /**
   * Constructeur de la classe.
   */
  public OsManager() {
    osType = OsType.getByName(System.getProperty("os.name").toLowerCase());
  }

  /**
   * Type de l'OS.
   */
  private final OsType osType;

  /**
   * Méthode permettant de récupérer le type de l'OS.
   *
   * @return le type de l'OS
   */
  public OsType getOsType() {
    return osType;
  }

  /**
   * Enumération des types d'OS.
   */
  public enum OsType {
    /**
     * windows.
     */
    WINDOWS("win"),
    /**
     * mac.
     */
    MAC("mac"),
    /**
     * linux.
     */
    LINUX("nux"),
    /**
     * solaris.
     */
    SOLARIS("sunos");

    /**
     * Clé de l'OS.
     */
    private final String key;

    /**
     * Constructeur de l'enum.
     *
     * @param key la clé de l'OS
     */
    OsType(String key) {
      this.key = key;
    }

    /**
     * Méthode permettant de récupérer le type de l'OS à partir du nom du système.
     *
     * @param os le nom de l'OS
     * @return OsType le type de l'OS
     */
    private static OsType getByName(String os) {
      for (OsType t : OsType.values()) {
        if (os.contains(t.key)) {
          return t;
        }
      }
      throw new IllegalArgumentException("OS not found");
    }
  }
}
