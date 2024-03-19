package fr.mcgcorp.fxml.controllers;

import fr.mcgcorp.fxml.annotations.Interact;
import fr.mcgcorp.fxml.annotations.Interact.InteractType;
import fr.mcgcorp.fxml.managers.ControllerManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;

/**
 * Contrôleur du sélecteur du mode de jeu.
 */
public class ModSelectorController extends OverController {

  public ModSelectorController() {
    super();
    this.setBlurParent(true);
  }

  @Override
  String getPathToFxml() {
    return "mod_selector.fxml";
  }

  @Interact(id = "campaignButton", type = InteractType.ON_ACTION)
  public void onCampaignButton(ActionEvent event) {
    ControllerManager.getInstance().getCampaignController().show();
  }

  @Interact(id = "classicButton", type = InteractType.ON_ACTION)
  public void onClassicButton(ActionEvent event) {
    ControllerManager.getInstance().getClassicController().show();
  }

  @Interact(id = "pixelButton", type = InteractType.ON_ACTION)
  public void onPixelButton(ActionEvent event) {
    ControllerManager.getInstance().getPixelController().show();
  }

  @Interact(id = "speedrunButton", type = InteractType.ON_ACTION)
  public void onSpeedRunButton(ActionEvent event) {
    ControllerManager.getInstance().getSpeedRunController().show();
  }

  @Interact(id = "quitButton", type = InteractType.ON_ACTION)
  public void onQuitButtonClicked(ActionEvent event) {
    this.close();
  }


}