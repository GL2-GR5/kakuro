package fr.mcgcorp.profile;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe permettant de gérer les profils.
 * 
 * @author POURCEAU Luca
 */
public class ProfileManager {
  /** Instance du Profile manager. */
  private static final ProfileManager instance = new ProfileManager();
  /** La liste des profils. */
  private final Set<Profile> profiles = new HashSet<>();

  /**
   * Permet de récupérer l'instance de la classe.
   * 
   * @return l'instance de la classe
   */
  public static ProfileManager getInstance() {
    return instance;
  }

  /**
   * Permet d'ajouter un profil.
   * 
   * @param profile le profil à ajouter
   */
  public void addProfile(Profile profile) {
    profiles.add(profile);
  }

  /**
   * Permet de supprimer un profil.
   * 
   * @param profile le profil à supprimer
   */
  public void removeProfile(Profile profile) {
    profiles.remove(profile);
  }

  /**
   * Permet de récupérer la liste des profils.
   * 
   * @return la liste des profils
   */
  public Set<Profile> getProfiles() {
    return profiles;
  }

  /**
   * Permet de récupérer un profil par son nom.
   * 
   * @param name le nom du profil
   * @return le profil
   */
  public Profile getProfile(String name) {
    for (Profile profile : profiles) {
      if (profile.getName().equals(name)) {
        return profile;
      }
    }
    return null;
  }

  /**
   * Permet de savoir si la liste des profils est vide.
   * 
   * @return true si la liste est vide, false sinon
   */
  public boolean isEmpty() {
    return profiles.isEmpty();
  }

  /**
   * Permet de savoir si un profil existe.
   * 
   * @param name le nom du profil
   * @return true si le profil existe, false sinon
   */
  public boolean exists(String name) {
    for (Profile profile : profiles) {
      if (profile.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }
}
