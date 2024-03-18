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


  private static ControllerManager instance;

  private Controller currentController;
  private final MenuController menuController;
  private final ModSelectorController modSelectorController;
  private final SettingsController settingsController;
  private final CampaignController campaignController;
  private final GameController gameController;

  public static ControllerManager getInstance() {
    if (instance == null) {
      instance = new ControllerManager();
    }
    return instance;
  }

  private ControllerManager() {
    this.menuController = new MenuController();
    this.modSelectorController = new ModSelectorController();
    this.settingsController = new SettingsController();
    this.campaignController = new CampaignController();
    this.gameController = new GameController();
  }

  public Controller getCurrentController() {
    return currentController;
  }

  public void setCurrentController(Controller currentController) {
    this.currentController = currentController;
  }

  public MenuController getMenuController() {
    return menuController;
  }

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
}