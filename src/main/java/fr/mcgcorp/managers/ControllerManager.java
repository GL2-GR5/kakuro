package fr.mcgcorp.managers;

import fr.mcgcorp.controllers.CampaignController;
import fr.mcgcorp.controllers.MenuController;
import fr.mcgcorp.controllers.ModSelectorController;
import fr.mcgcorp.controllers.OptionController;

/**
 * Classe singleton qui gère les différents contrôleurs de l'application.
 */
public class ControllerManager {

  /**
   * Instance singleton de la classe.
   */
  private static ControllerManager instance;

  /**
   * Getter de l'instance singleton de la classe.
   *
   * @return ControllerManager l'instance singleton de la classe
   */
  public static ControllerManager getInstance() {
    if (instance == null) {
      instance = new ControllerManager();
    }
    return instance;
  }

  private boolean fullScreen;

  /**
   * Contrôleur du menu principal.
   */
  private final MenuController menuController;

  /**
   * Contrôleur du sélecteur du mode de jeu.
   */
  private final ModSelectorController modSelectorController;
  private final OptionController optionController;
  private final CampaignController campaignController;

  /**
   * Constructeur de la classe ControllerManager.
   * Initialise les différents contrôleurs de l'application.
   */
  private ControllerManager() {
    this.menuController = new MenuController();
    this.modSelectorController = new ModSelectorController();
    this.optionController = new OptionController();
    this.campaignController = new CampaignController();
    this.fullScreen = false;
  }

  /**
   * Getter du contrôleur du menu principal.
   *
   * @return MenuController le contrôleur du menu principal
   */
  public MenuController getMenuController() {
    return menuController;
  }

  /**
   * Getter du contrôleur du sélecteur du mode de jeu.
   *
   * @return ModSelectorController le contrôleur du sélecteur du mode de jeu
   */
  public ModSelectorController getModSelectorController() {
    return modSelectorController;
  }

  public OptionController getOptionController() {
    return optionController;
  }

  public CampaignController getCampaignController() {
    return campaignController;
  }

  public boolean getFullScreen() {
    return fullScreen;
  }

  public void setFullScreen(boolean b) {
    this.fullScreen = b;
  }

}