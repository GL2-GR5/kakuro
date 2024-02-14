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
    this.loadButtons();
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

  /**
   * Charge les boutons de la scène et redirige les événements vers la méthode onButtonClick.
   */
  private void loadButtons() {
    for (Node node : this.scene.getRoot().lookupAll(".button")) {
      if (node instanceof Button button) {
        button.setOnAction(this::onButtonClick);
      }
    }
  }

  /**
   * Méthode appelée lorsqu'un bouton est cliqué.
   *
   * @param event l'événement FXML associé au clic
   */
  abstract void onButtonClick(ActionEvent event);
}


