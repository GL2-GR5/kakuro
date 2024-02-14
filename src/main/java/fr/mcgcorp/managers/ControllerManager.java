package fr.mcgcorp.managers;

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

  private final MenuController menuController;
  private final ModSelectorController modSelectorController;
  private final OptionController optionController;

  private ControllerManager() {
    this.menuController = new MenuController();
    this.modSelectorController = new ModSelectorController();
    this.optionController = new OptionController();
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

}