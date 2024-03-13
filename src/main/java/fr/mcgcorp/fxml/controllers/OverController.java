package fr.mcgcorp.fxml.controllers;

import fr.mcgcorp.fxml.managers.ControllerManager;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.naming.ldap.Control;

public abstract class OverController extends Controller {

  private Controller parent;

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
    this.parent = ControllerManager.getInstance().getCurrentController();
    //this.parent.getStage().getScene().getRoot().setEffect(new BoxBlur());
    this.stage.setOnCloseRequest(event -> {
      System.out.println("Closing");
      this.parent.getStage().getScene().getRoot().setEffect(null);
      this.parent = null;
    });
    this.stage.show();
  }
}