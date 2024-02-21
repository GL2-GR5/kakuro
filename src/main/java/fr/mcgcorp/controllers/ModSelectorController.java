package fr.mcgcorp.controllers;

import fr.mcgcorp.fxmlbuilders.InterfaceStage;
import fr.mcgcorp.fxmlbuilders.OverStage;

/**
 * Contrôleur du sélecteur du mode de jeu.
 */
public class ModSelectorController extends Controller {

  private InterfaceStage stage;

  @Override
  protected InterfaceStage getStage() {
    if (this.stage == null) {
      this.stage = new OverStage();
    }
    return this.stage;
  }

  @Override
  String getPathToFxml() {
    return "mod_selector.fxml";
  }
}