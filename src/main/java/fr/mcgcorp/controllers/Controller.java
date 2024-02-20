package fr.mcgcorp.controllers;

import fr.mcgcorp.Main;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;


/**
 * Classe abstraite représentant un contrôleur.
 */
public abstract class Controller {

  /**
   * Constructeur par défaut.
   */
  public Controller() {
    this.load();
    this.loadItems();
  }

  /**
   * Scène associée au contrôleur.
   */
  private Scene scene;

  /**
   * Getter de la scène associée au contrôleur.
   *
   * @return Scene la scène associée au contrôleur
   */
  public Scene getScene() {
    return scene;
  }

  /**
   * Méthode abstraite permettant de spécifier le chemin vers le fichier FXML pour chaque contrôleur.
   *
   * @return String le chemin vers le fichier FXML associé au contrôleur
   */
  abstract String getPathToFxml();

  /**
   * Charge le fichier FXML associé au contrôleur.
   */
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