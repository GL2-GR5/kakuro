package fr.mcgcorp.fxml.managers;

import fr.mcgcorp.fxml.controllers.CampaignController;
import fr.mcgcorp.fxml.controllers.Controller;
import fr.mcgcorp.fxml.controllers.GameController;
import fr.mcgcorp.fxml.controllers.MenuController;
import fr.mcgcorp.fxml.controllers.ModSelectorController;
import fr.mcgcorp.fxml.controllers.SettingsController;

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
  private final GameController gameController = new GameController();

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

  public GameController getGameController() {
    return gameController;
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
    this.getCurrentController().getStage().setFullScreen(b);
  }
}