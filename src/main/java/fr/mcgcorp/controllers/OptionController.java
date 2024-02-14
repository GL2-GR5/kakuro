package fr.mcgcorp.controllers;

import fr.mcgcorp.managers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class OptionController extends Controller {


  @Override
  String getPathToFxml() {
    return "settings.fxml";
  }

  public void onButtonClick(ActionEvent event) throws RuntimeException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    switch (((Node) event.getSource()).getId()) {
      //case "checkClavierNumerique" -> ;
      case "pleinEcranItem":
        stage.setFullScreen(true);
        break; // A modifier
      case "fenetreItem":
        stage.setFullScreen(false);
        break; // A modifier
      //case "sliderSon" ->
      case "leaveButton":
        stage.getOwner().getScene().getRoot().setEffect(null);
        stage.close();
        break;
      default: throw new RuntimeException("Bouton non reconnu");
    }
  }
}
