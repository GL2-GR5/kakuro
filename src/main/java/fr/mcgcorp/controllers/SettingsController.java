package fr.mcgcorp.controllers;

import fr.mcgcorp.fxmlbuilders.InterfaceStage;
import fr.mcgcorp.fxmlbuilders.OverStage;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;


public class SettingsController extends Controller {

  private InterfaceStage stage;

  @FXML
  MenuButton menuScreenButton;


  @Override
  protected InterfaceStage getStage() {
    if (this.stage == null) {
      this.stage = new OverStage();
    }
    return this.stage;
  }

  @Override
  String getPathToFxml() {
    return "settings.fxml";
  }
}
