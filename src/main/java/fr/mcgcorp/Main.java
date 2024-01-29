package fr.mcgcorp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("app.fxml"));
    Parent root = loader.load();
    stage.setScene(new Scene(root));
    stage.setResizable(false);
    stage.show();
  }
}