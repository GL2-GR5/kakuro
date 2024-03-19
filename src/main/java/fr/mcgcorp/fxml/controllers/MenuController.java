package fr.mcgcorp.fxml.controllers;

import fr.mcgcorp.fxml.annotations.Interact;
import fr.mcgcorp.fxml.annotations.Interact.InteractType;
import fr.mcgcorp.fxml.managers.ControllerManager;
import fr.mcgcorp.fxml.managers.InteractManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 * Contrôleur du menu principal.
 */
public class MenuController extends Controller {

  @Override
  String getPathToFxml() {
    return "main_menu.fxml";
  }

  @Interact(id = "settingButton", type = InteractType.ON_ACTION)
  public void onSettingsButtonClicked(ActionEvent event) {
    ControllerManager.getInstance().getSettingsController().show();
  }

  @Interact(id = "quitButton", type = InteractType.ON_ACTION)
  public void onQuitButton(ActionEvent event) {
    Platform.exit();
  }

  @Interact(id = "playButton", type = InteractType.ON_ACTION)
  public void onPlayButton(ActionEvent event) {
    ControllerManager.getInstance().getModSelectorController().show();
  }

}
