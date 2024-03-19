package fr.mcgcorp.profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Profil d'un utilisateur.
 * 
 * @author HOUGET Julien
 */
public class Profile {
  /**
   * Nom du profil.
   */
  String name;

  /**
   * Options du profil.
   */
  private SettingsProfile settings;

  /** 
   * Statistiques.
   * Progression dans le mode campagne 
   */
  private Integer progressCampaign;
  
  /**
   * Liste des profiles existant.
   */
  static List<Profile> profiles = new ArrayList<Profile>();
  
  /**
   * Constructeur d'un profil.
   * @param name nom du profil
   */
  Profile(String name) {
    this.name = name;
    this.settings = new SettingsProfile();
    this.progressCampaign = 0;
    profiles.add(this);
  }
  
  /**
   * Getter.
   * @return les options du profil
   */
  public SettingsProfile getSettings() {
    return this.settings;
  }

  /**
   * Getter.
   * @return la progression du profil dans le mode Campagne
   */
  public Integer getProgressCampaign() {
    return this.progressCampaign;
  }

  /**
   * Setter.
   * @param progressCampaign progression dans la campagne
   */
  public void setKeyboardState(Integer progressCampaign) {
    this.progressCampaign = progressCampaign;
  }
}
