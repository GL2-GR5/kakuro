package fr.mcgcorp.managers;

import fr.mcgcorp.controllers.CampaignController;
import fr.mcgcorp.controllers.MenuController;
import fr.mcgcorp.controllers.ModSelectorController;
import fr.mcgcorp.controllers.OptionController;

public class ControllerManager {

  private static ControllerManager instance;

  public static ControllerManager getInstance() {
    if (instance == null) {
      instance = new ControllerManager();
    }
    return instance;
  }


  private boolean fullScreen;
  private final MenuController menuController;
  private final ModSelectorController modSelectorController;
  private final OptionController optionController;
  private final CampaignController campaignController;

  private ControllerManager() {
    this.menuController = new MenuController();
    this.modSelectorController = new ModSelectorController();
    this.optionController = new OptionController();
    this.campaignController = new CampaignController();
    this.fullScreen = false;
  }

  public MenuController getMenuController() {
    return menuController;
  }

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