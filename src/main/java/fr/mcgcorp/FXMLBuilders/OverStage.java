package fr.mcgcorp.FXMLBuilders;

import fr.mcgcorp.managers.ControllerManager;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OverStage {

  private final Stage stage;
  private final Stage owner;

  public OverStage(Stage owner, Scene scene) {
    this.owner = owner;
    this.stage = new Stage();
    this.stage.initModality(Modality.APPLICATION_MODAL);
    this.stage.initStyle(StageStyle.UNDECORATED);
    this.stage.initOwner(owner);
    this.stage.setScene(scene);
    this.stage.setOnCloseRequest(event -> {
      this.owner.getScene().getRoot().setEffect(null);
    });
  }

  public void show() {
    this.owner.getScene().getRoot().setEffect(new BoxBlur(5, 5, 3));
    this.stage.show();
  }
}
