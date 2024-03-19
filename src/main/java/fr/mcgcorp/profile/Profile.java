package fr.mcgcorp.profile;

import java.util.ArrayList;
import java.util.List;

public class Profile {
  // Nom du profil
  String name;

  // Options du profil
  private SettingsProfile settings;

  // Statistiques
  // Progression dans le mode campagne 
  private Integer progressCampaign;

  // Liste des profiles existant
  static List<Profile> profiles = new ArrayList<Profile>();

  // Constructeur
  Profile(String name) {
    this.name = name;
    this.settings = new SettingsProfile();
    this.progressCampaign = 0;
    profiles.add(this);
  }

  /**
   * Getter.
   */
  public SettingsProfile getSettings() {
    return this.settings;
  }

  /**
   * Getter.
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
