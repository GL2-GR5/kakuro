package fr.mcgcorp.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class gameController extends Controller{
  @Override
  String getPathToFxml() {
    return "main_menu.fxml";
  }

  public void onButtonClick(ActionEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();




  }

}
