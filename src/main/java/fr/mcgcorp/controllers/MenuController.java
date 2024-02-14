package fr.mcgcorp.controllers;

import fr.mcgcorp.managers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MenuController extends Controller {

  @Override
  String getPathToFxml() {
    return "main_menu.fxml";
  }

  public void onButtonClick(ActionEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    switch (((Node) event.getSource()).getId()) {
      case "playButton" -> stage.setScene(ControllerManager.getInstance().getModSelectorController().getScene());
      case "quitButton" -> stage.close();
      default -> throw new RuntimeException("Bouton non reconnu");
    }
  }
}
