package fr.mcgcorp.controllers;

import fr.mcgcorp.Main;
import fr.mcgcorp.fxmlbuilders.InterfaceStage;
import fr.mcgcorp.fxmlbuilders.ItemAction;
import fr.mcgcorp.fxmlbuilders.ItemType;
import java.net.URL;
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
  }

  /**
   * Stage associé au contrôleur.
   */
  protected abstract InterfaceStage getStage();

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

  protected void loadItem(ItemType itemType, ItemAction itemAction) {
    for (Node node : getStage().getScene().getRoot().lookupAll(itemType.getType())) {
      if (node instanceof Button button) {
        button.setOnAction(itemAction::onItemAction);
      }
    }
  }

  public void show() {
    getStage().show();
  }
}