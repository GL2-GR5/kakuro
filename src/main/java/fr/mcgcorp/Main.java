package fr.mcgcorp;

import fr.mcgcorp.managers.ControllerManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Classe principale de l'application.
 * <img src="doc-files/Main.png" alt="Logo"/>
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
    launch(args);
  }

  /**
   * Méthode de démarrage graphique de l'application.
   *
   * @param primaryStage la fenêtre principale de l'application
   */
  @Override
  public void start(Stage primaryStage) {
    ControllerManager.getInstance().getMenuController().show();
  }
}