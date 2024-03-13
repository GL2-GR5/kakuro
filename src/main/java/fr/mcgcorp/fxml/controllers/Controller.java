package fr.mcgcorp.fxml.controllers;

import fr.mcgcorp.Main;
import fr.mcgcorp.fxml.managers.ControllerManager;
import fr.mcgcorp.fxml.managers.InteractManager;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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

  public Node lookup(String id) {
    return this.lookup(id, this.getStage().getScene().getRoot());
  }

  private Node lookup(String id, Parent parent) {
    for (Node n : parent.getChildrenUnmodifiable()) {
      if (n.getId() != null && n.getId().matches(id)) {
        return n;
      }
      if (n instanceof Parent) {
        Node result = this.lookup(id, (Parent) n);
        if (result != null) {
          return result;
        }
      }
      if (n instanceof ScrollPane) {
        Node result = this.lookup(id, (Parent) ((ScrollPane) n).getContent());
        if (result != null) {
          return result;
        }
      }
    }
    return null;
  }

  public Set<Node> lookupAll(String id) {
    return lookupAll(id, this.getStage().getScene().getRoot(), new HashSet<Node>());
  }

  private Set<Node> lookupAll(String id, Parent parent, Set<Node> nodes) {
    for (Node n : parent.getChildrenUnmodifiable()) {
      if (n.getId() != null && n.getId().matches(id)) {
        nodes.add(n);
        continue;
      }
      if (n instanceof Parent) {
        Set<Node> result = this.lookupAll(id, (Parent) n, nodes);
        if (result != null) {
          nodes.addAll(result);
          continue;
        }
      }
      if (n instanceof ScrollPane) {
        Set<Node> result = this.lookupAll(id, (Parent) ((ScrollPane) n).getContent(), nodes);
        if (result != null) {
          nodes.addAll(result);
        }
      }
    }
    return null;
  }
}