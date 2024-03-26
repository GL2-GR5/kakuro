package fr.mcgcorp;

import fr.mcgcorp.profile.ProfileManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale de l'application.
 * @author GOUDET Lucas, HOUGET Julien, LAHMAR Marwan, LE LUET Hôa, PECHON Erwan, POURCEAU Luca, PUREN Mewen
 * @see Application
 */
public class Main extends Application {
  /**
   * Méthode principale de l'application.
   * lance l'application.
   *
   * @param args les arguments de la ligne de commande
   */
  public static void main(String[] args) {
    ProfileManager profileManager = ProfileManager.getInstance();

    launch(args);
  }

  /**
   * Méthode de démarrage graphique de l'application.
   * remplissage temporaire en attendant la création de l'interface graphique. #TODO
   *
   * @param primaryStage la fenêtre principale de l'application
   * @throws Exception si une erreur survient
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
    primaryStage.setTitle("Kakuro");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}

