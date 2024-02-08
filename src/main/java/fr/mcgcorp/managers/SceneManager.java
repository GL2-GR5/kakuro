package fr.mcgcorp.managers;

import fr.mcgcorp.Main;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class SceneManager {

  private static SceneManager Instance;

  public static SceneManager getInstance() {
    if (Instance == null) {
      Instance = new SceneManager();
    }
    return Instance;
  }

  private Scene menuScene;

  public Scene getMenuScene() {
    if (this.menuScene == null) {
      this.menuScene = loadScene("Menu.fxml");
    }
    return menuScene;
  }

  private Scene loadScene(String pathToFxml) {
    Scene scene = null;
    try {
      FXMLLoader loader = FXMLLoader.load(Main.class.getResource(pathToFxml));
      scene = new Scene(loader.load());
      System.out.println(loader.getController().getClass());
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (scene == null) {
      throw new IllegalArgumentException(pathToFxml + " not found");
    }
    return scene;
  }

}
