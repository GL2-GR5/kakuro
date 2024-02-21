package fr.mcgcorp.fxmlbuilders;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClassicStage implements InterfaceStage {

  private final Stage stage;

  public ClassicStage() {
    this.stage = new Stage();
    this.stage.initModality(Modality.APPLICATION_MODAL);
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  @Override
  public void onOpen() {

  }

  @Override
  public void onClose() {

  }

  @Override
  public void setScene(Scene scene) {
    stage.setScene(scene);
  }

  @Override
  public Scene getScene() {
    return stage.getScene();
  }
}
