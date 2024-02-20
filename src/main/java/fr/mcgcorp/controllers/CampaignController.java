package fr.mcgcorp.controllers;

import fr.mcgcorp.managers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CampaignController extends Controller {

  @Override
  String getPathToFxml() {
    return "campaign.fxml";
  }

  public void onItemAction(ActionEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
  }
}
