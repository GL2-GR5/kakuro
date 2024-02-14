package fr.mcgcorp.controllers;

import fr.mcgcorp.managers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;


public class OptionController extends Controller {

  @FXML
  MenuButton menuScreenButton;

  @Override
  String getPathToFxml() {
    return "settings.fxml";
  }

  public void onItemAction(ActionEvent event) throws RuntimeException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    switch (((Node) event.getSource()).getId()) {
      //case "checkClavierNumerique" -> ;
      case "pleinEcranItem":
        menuScreenButton.setText("Plein Ecran");
        ControllerManager.getInstance().setFullScreen(true);
        break; // A modifier
      case "fenetreItem":
        menuScreenButton.setText("Fenêtré");
        ControllerManager.getInstance().setFullScreen(false);
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
