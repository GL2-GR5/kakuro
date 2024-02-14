package fr.mcgcorp.controllers;

import fr.mcgcorp.managers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuController extends Controller {


  BoxBlur blur = new BoxBlur(5, 5, 3);

  @Override
  String getPathToFxml() {
    return "main_menu.fxml";
  }

  public void onButtonClick(ActionEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    switch (((Node) event.getSource()).getId()) {
      case "playButton":
        Stage playStage = new Stage();
        playStage.initModality(Modality.APPLICATION_MODAL);
        playStage.initStyle(StageStyle.UNDECORATED);
        playStage.initOwner(stage);
        playStage.setScene(ControllerManager.getInstance().getModSelectorController().getScene());
        stage.getScene().getRoot().setEffect(blur);
        playStage.show();
        break;
      case "settingButton":
        Stage settingStage = new Stage();
        settingStage.initModality(Modality.APPLICATION_MODAL);
        settingStage.initStyle(StageStyle.UNDECORATED);
        settingStage.initOwner(stage);
        settingStage.setScene(ControllerManager.getInstance().getOptionController().getScene());
        stage.getScene().getRoot().setEffect(blur);
        settingStage.show();
        break;
      case "quitButton":
        stage.close();
        break;
      default:
        throw new RuntimeException("Bouton non reconnu");
    }
  }
}
