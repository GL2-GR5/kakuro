package fr.mcgcorp.fxml.controllers;

import fr.mcgcorp.fxml.annotations.Interact;
import fr.mcgcorp.fxml.annotations.Interact.InteractType;
import fr.mcgcorp.fxml.managers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class SettingsController extends OverController {

  @FXML
  Slider sliderSon;

  @Override
  String getPathToFxml() {
    return "settings.fxml";
  }

  @Interact(id = "leaveButton", type = InteractType.ON_ACTION)
  public void onLeaveButtonClicked(ActionEvent event) {
    this.getStage().close();
  }

  @Interact(id = "sliderSon", type = InteractType.ON_MOUSE_RELEASED)
  public void onSoundUpdate(MouseEvent event) {
    System.out.println("Son = " + sliderSon.valueProperty());
  }

  @Interact(id = "#pleinEcranItem", type = InteractType.ON_ACTION)
  public void menuButton(ActionEvent event) {
    ControllerManager.getInstance().setFullScreen(true);
  }

  @Interact(id = "#fenetreItem", type = InteractType.ON_ACTION)
  public void menuButton2(ActionEvent event) {
    ControllerManager.getInstance().setFullScreen(false);
  }
}
