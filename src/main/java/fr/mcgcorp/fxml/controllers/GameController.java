package fr.mcgcorp.fxml.controllers;

import fr.mcgcorp.Coord;
import fr.mcgcorp.fxml.annotations.Interact;
import fr.mcgcorp.fxml.annotations.Interact.InteractType;
import fr.mcgcorp.fxml.managers.ControllerManager;
import fr.mcgcorp.fxml.managers.InteractManager;
import java.util.Arrays;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GameController extends Controller {

  private final AnchorPane gamePane;

  public GameController() {
    super();

    this.gamePane = (AnchorPane) this.lookup("gamePane");

    this.stage.getScene().setOnKeyTyped(event -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        Platform.runLater(() -> ControllerManager.getInstance().getMenuController().show());
      }
    });

    String serialized = "b:r6/:r23/:b:b:r/12:w:w:r23/:r6/:r/18:w:w:w:w:r/20:w:w:w:w:b:b:r/8:w:w";
    this.initGrid(serialized);

    InteractManager.getInstance().registerAll(this);
  }

  public void initGrid(String serialized) {
    int caseSize = 25;

    double width = ((ScrollPane) this.lookup("scroll")).getPrefWidth();
    double height = ((ScrollPane) this.lookup("scroll")).getPrefHeight();

    int widthCaseNumber = (int) (width / caseSize);
    int heightCaseNumber = (int) (height / caseSize);

    int gridSize = (int) (Math.sqrt(serialized.split(":").length));

    this.gamePane.setPrefSize(caseSize * widthCaseNumber, caseSize * heightCaseNumber);

    int startGridX = widthCaseNumber / 2 - gridSize / 2;
    int endGridX = startGridX + gridSize;

    int startGridY = heightCaseNumber / 2 - gridSize / 2;
    int endGridY = startGridY + gridSize;

    List<String> serializedList = Arrays.asList(serialized.split(":"));
    System.out.println(serializedList);

    int index = 0;

    for (int i = 0; i < widthCaseNumber; i++) {
      for (int j = 0; j < heightCaseNumber; j++) {
        Rectangle r = new Rectangle(i * caseSize, j * caseSize, caseSize, caseSize);
        r.setId("case:" + i + "_" + j);
        if (i >= startGridX && i < endGridX && j >= startGridY && j < endGridY) {
          String s = serializedList.get(index++);
          System.out.println(s);
          if (s.equals("b")) {
            r.setStyle("-fx-fill: #0E1544; -fx-stroke: black; -fx-stroke-width: 1;");
          } else if (s.equals("w")) {
            r.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
          } else {
            r.setStyle("-fx-fill: gray; -fx-stroke: black; -fx-stroke-width: 1;");;
          }
        } else {
          r.setStyle("-fx-fill: #0E1544; -fx-stroke: black; -fx-stroke-width: 1;");
        }
        this.gamePane.getChildren().add(r);
      }
    }
  }

  @Override
  String getPathToFxml() {
    return "game.fxml";
  }



  @Interact(id = "settingsButton", type = InteractType.ON_ACTION)
  public void onSettingsButtonClicked(ActionEvent event) {
    ControllerManager.getInstance().getSettingsController().show();
  }

  @Interact(id = {"case.*"}, type = InteractType.ON_MOUSE_CLICKED)
  public void onCaseHover(MouseEvent event) {
    System.out.println("Clicked the case: " + ((Node) event.getSource()).getId());
  }

  @Interact(id = "gamePane", type = InteractType.ON_SCROLL)
  public void onGamePaneScroll(ScrollEvent event) {
    System.out.println("Scrolling the game pane");
  }

  @Interact(id = "quitButton", type = InteractType.ON_ACTION)
  public void onQuitButtonClicked(ActionEvent event) {
    ControllerManager.getInstance().getMenuController().show();
  }
}