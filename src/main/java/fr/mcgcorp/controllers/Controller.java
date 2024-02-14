package fr.mcgcorp.controllers;

import fr.mcgcorp.Main;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public abstract class Controller {

  public Controller() {
    this.load();
    this.loadButtons();
  }

  private Scene scene;

  public Scene getScene() {
    return scene;
  }

  abstract String getPathToFxml();

  public void load() {
    URL url = Main.class.getResource(this.getPathToFxml());
    if (url == null) {
      throw new RuntimeException("Fichier FXML non trouvé");
    }

    try {
      FXMLLoader loader = new FXMLLoader(url);
      this.scene = new Scene(loader.load());
      loader.setController(this);
    } catch (Exception e) {
      throw new RuntimeException("Impossible de charger le fichier FXML", e);
    }
  }

  private void loadButtons() {
    for (Node node : this.scene.getRoot().lookupAll(".button")) {
      if (node instanceof Button button) {
        button.setOnAction(this::onButtonClick);
      }
    }
  }

  abstract void onButtonClick(ActionEvent event);
}


