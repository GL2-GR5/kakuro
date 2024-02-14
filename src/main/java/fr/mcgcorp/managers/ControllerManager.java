package fr.mcgcorp.managers;

import fr.mcgcorp.controllers.MenuController;
import fr.mcgcorp.controllers.ModSelectorController;

/**
 * Classe singleton qui gère les différents contrôleurs de l'application.
 */
public class ControllerManager {

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

  private final MenuController menuController;
  private final ModSelectorController modSelectorController;

  /**
   * Constructeur de la classe ControllerManager.
   * Initialise les différents contrôleurs de l'application.
   */
  private ControllerManager() {
    this.menuController = new MenuController();
    this.modSelectorController = new ModSelectorController();
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
}
