package fr.mcgcorp.controllers;

import fr.mcgcorp.Main;
import fr.mcgcorp.fxmlbuilders.InteractManager;
import fr.mcgcorp.managers.ControllerManager;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe abstraite représentant un contrôleur.
 */
public abstract class Controller {

  protected Stage stage;

  /**
   * Constructeur par défaut.
   */
  public Controller() {
    this.load();
    InteractManager.getInstance().registerAll(this);
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
      Scene scene = new Scene(loader.load());
      getStage().setScene(scene);
      loader.setController(this);
    } catch (Exception e) {
      throw new RuntimeException("Impossible de charger le fichier FXML", e);
    }
  }

  public void show() {
    getStage().show();
    ControllerManager.getInstance().setCurrentController(this);
  }

  public Stage getStage() {
    if (this.stage == null) {
      this.stage = new Stage();
    }
    return this.stage;
  }
}