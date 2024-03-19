package fr.mcgcorp.profile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
  private final SettingsProfile settings;

  /** 
   * Statistiques.
   * Progression dans le mode campagne 
   */
  private Integer progressCampaign;
  
  /**
   * Constructeur d'un profil.
   *
   * @param name nom du profil
   */
  public Profile(String name) {
    this.name = name;
    this.settings = new SettingsProfile();
    this.progressCampaign = 0;
  }

  /**
   * Getter.
   *
   * @return le nom du profil
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Getter.
   *
   * @return les options du profil
   */
  public SettingsProfile getSettings() {
    return this.settings;
  }

  /**
   * Getter.
   *
   * @return la progression du profil dans le mode Campagne
   */
  public Integer getProgressCampaign() {
    return this.progressCampaign;
  }

  /**
   * Setter.
   *
   * @param progressCampaign progression dans la campagne
   */
  public void setKeyboardState(Integer progressCampaign) {
    this.progressCampaign = progressCampaign;
  }
}
