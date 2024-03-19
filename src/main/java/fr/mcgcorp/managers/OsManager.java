package fr.mcgcorp.managers;

import java.util.Arrays;

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
    WINDOWS("windows"),
    /**
     * Mac OS.
     */
    MAC("mac", "darwin"),
    /**
     * Linux OS.
     */
    LINUX("unix", "linux", "debian", "ubuntu", "centos", "fedora", "arch");

    /**
     * Constructeur de la classe OsType.
     *
     * @param key la clé du type de système d'exploitation.
     */
    OsType(String... keys) {
      this.keys = keys;
    }

    /**
     * La clé du type de système d'exploitation.
     */
    private final String[] keys;

    /**
     * Obtenir le type de système d'exploitation par son nom.
     *
     * @param name le nom du système d'exploitation.
     * @return le type de système d'exploitation.
     */
    private OsType getOsByName(String name) {
      for (OsType os : OsType.values()) {
        for (String key : os.keys) {
          if (name.contains(key)) {
            return os;
          }
        }
      }
      return null;
    }
  }
}
