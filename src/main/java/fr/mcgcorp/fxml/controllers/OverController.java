package fr.mcgcorp.fxml.controllers;

import fr.mcgcorp.fxml.managers.ControllerManager;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class OverController extends Controller {

  private boolean blurParent;

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

  public final void close() {
    if (this.blurParent) {
      ControllerManager.getInstance().getCurrentController().getStage().getScene().getRoot().setEffect(null);
    }
    this.getStage().close();
  }

  @Override
  public final void show() {
    Stage parent = ControllerManager.getInstance().getCurrentController().getStage();

    final double stageWidth = ((Pane) this.getStage().getScene().getRoot()).getPrefWidth();
    final double stageHeight = ((Pane) this.getStage().getScene().getRoot()).getPrefHeight();
    final double x = parent.getX() + parent.getWidth() / 2 - stageWidth / 2;
    final double y = parent.getY() + parent.getHeight() / 2 - stageHeight / 2;
    this.getStage().setX(x);
    this.getStage().setY(y);

    if (this.blurParent) {
      parent.getScene().getRoot().setEffect(new BoxBlur(3, 3, 3));
    }
    this.stage.show();
  }
}