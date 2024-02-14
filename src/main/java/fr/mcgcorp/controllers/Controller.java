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
    this.loadItems();
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

  private final String[] itemTypes = new String[] { ".button", ".menu-item" };

  private void loadItems() {
    for (String item : this.itemTypes) {
      for (Node node : this.scene.getRoot().lookupAll(item)) {
        if (node instanceof Button button) {
          button.setOnAction(this::onItemAction);
        }
      }
    }
  }

  abstract void onItemAction(ActionEvent event);
}


