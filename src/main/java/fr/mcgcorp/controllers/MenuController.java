package fr.mcgcorp.controllers;

import fr.mcgcorp.fxmlbuilders.Interact;
import fr.mcgcorp.managers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Contrôleur du menu principal.
 */
public class MenuController extends Controller {

  @Override
  String getPathToFxml() {
    return "main_menu.fxml";
  }

  @Interact(id = {"#settingButton"}, action = "click", type = Interact.ItemType.BUTTON)
  public void onSettingsButtonClicked(ActionEvent event) {
    ControllerManager.getInstance().getSettingsController().show();
  }
}
