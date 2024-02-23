package fr.mcgcorp.fxmlbuilders;

import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OverStage implements InterfaceStage {

  private final Stage stage;
  private Stage owner;

  public OverStage() {
    this.stage = new Stage();
    this.stage.initModality(Modality.APPLICATION_MODAL);
    this.stage.initStyle(StageStyle.UNDECORATED);
    this.stage.setOnCloseRequest(
        event -> {
          event.consume();
          this.close();
        }
    );
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  @Override
  public void setScene(Scene scene) {
    this.stage.setScene(scene);
  }

  @Override
  public Scene getScene() {
    return stage.getScene();
  }

  @Override
  public void onOpen() {
    this.owner.getScene().getRoot().setEffect(new BoxBlur());
  }

  @Override
  public void onClose() {
    this.owner.getScene().getRoot().setEffect(null);
    this.owner = null;
  }

  public void show(Stage ownerStage) {
    if (stage.getOwner() == null) {
      stage.initOwner(ownerStage);
    }
    this.owner = ownerStage;
    this.show();
    onOpen();
  }
}
