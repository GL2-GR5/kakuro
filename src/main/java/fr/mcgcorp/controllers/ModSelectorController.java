package fr.mcgcorp.controllers;

import fr.mcgcorp.managers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ModSelectorController extends Controller {

  @Override
  String getPathToFxml() {
    return "mod_selector.fxml";
  }

  public void onItemAction(ActionEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    switch (((Node) event.getSource()).getId()) {
      case "campagneButton":
        stage.setScene(ControllerManager.getInstance().getModSelectorController().getScene());
        break;
      //case "classicButton" -> stage.setScene();
      //case "pixelButton" -> stage.setScene();
      //case "speedrunButton" -> stage.setScene();
      case "quitButton":
        stage.getOwner().getScene().getRoot().setEffect(null);
        stage.close();
        break;
      default: throw new RuntimeException("Bouton non reconnu");
    }
  }
}