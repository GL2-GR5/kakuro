package fr.mcgcorp.fxmlbuilders;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface InterfaceStage {

  Stage getStage();

  default void show() {
    getStage().show();
    onOpen();
  }

  default void close() {
    getStage().close();
    onClose();
  }

  void onOpen();

  void onClose();

  void setScene(Scene scene);

  Scene getScene();
}
