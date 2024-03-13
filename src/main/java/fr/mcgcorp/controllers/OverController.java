package fr.mcgcorp.controllers;

import fr.mcgcorp.managers.ControllerManager;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class OverController extends Controller {

  abstract String getPathToFxml();

  @Override
  public final Stage getStage() {
    if (this.stage == null) {
      this.stage = new Stage();
      this.stage.initStyle(StageStyle.UNDECORATED);
      this.stage.initModality(Modality.APPLICATION_MODAL);
    }
    return this.stage;
  }

  @Override
  public final void show() {
    Stage stage = this.getStage();
    Controller parent = ControllerManager.getInstance().getCurrentController();
    parent.getStage().getScene().getRoot().setEffect(new BoxBlur());
    stage.setOnCloseRequest(event -> {
      ControllerManager.getInstance().setCurrentController(parent);
      parent.getStage().getScene().getRoot().setEffect(null);
    });

    super.show();
  }
}