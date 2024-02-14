package fr.mcgcorp.managers;

import fr.mcgcorp.controllers.MenuController;
import fr.mcgcorp.controllers.ModSelectorController;

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

  private ControllerManager() {
    this.menuController = new MenuController();
    this.modSelectorController = new ModSelectorController();
  }

  public MenuController getMenuController() {
    return menuController;
  }

  public ModSelectorController getModSelectorController() {
    return modSelectorController;
  }
}
