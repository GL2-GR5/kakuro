package fr.mcgcorp.managers;

import fr.mcgcorp.controllers.CampaignController;
import fr.mcgcorp.controllers.Controller;
import fr.mcgcorp.controllers.MenuController;
import fr.mcgcorp.controllers.ModSelectorController;
import fr.mcgcorp.controllers.SettingsController;

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

  private Controller currentController;
  private boolean fullScreen;
  private final MenuController menuController;
  private final ModSelectorController modSelectorController;
  private final SettingsController settingsController;
  private final CampaignController campaignController;

  /**
   * Constructeur de la classe ControllerManager.
   * Initialise les différents contrôleurs de l'application.
   */
  private ControllerManager() {
    this.menuController = new MenuController();
    this.modSelectorController = new ModSelectorController();
    this.settingsController = new SettingsController();
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

  public SettingsController getSettingsController() {
    return settingsController;
  }

  public CampaignController getCampaignController() {
    return campaignController;
  }

  public Controller getCurrentController() {
    return currentController;
  }

  public void setCurrentController(Controller currentController) {
    this.currentController = currentController;
  }

  public boolean isFullScreen() {
    return fullScreen;
  }

  public void setFullScreen(boolean b) {
    this.fullScreen = b;
  }
}