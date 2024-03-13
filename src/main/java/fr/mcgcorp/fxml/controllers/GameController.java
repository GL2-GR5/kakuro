package fr.mcgcorp.fxml.controllers;

import fr.mcgcorp.fxml.annotations.Interact;
import fr.mcgcorp.fxml.annotations.Interact.InteractType;
import fr.mcgcorp.fxml.managers.ControllerManager;
import fr.mcgcorp.fxml.managers.InteractManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class GameController extends Controller {

  private final AnchorPane gamePane;

  public GameController() {
    super();

    this.gamePane = (AnchorPane) ((ScrollPane) this.getStage().getScene().lookup("#scroll")).getContent();

    String serialized = "b:r6/:r23/:b:b:r/12:w:w:r23/:r6/:r/18:w:w:w:w:r/20:w:w:w:w:b:b:r/8:w:w";
    this.initGrid(serialized);

    InteractManager.getInstance().registerAll(this);
  }

  private void initGrid(String serialized) {
    if (Math.sqrt(serialized.split(":").length) % 1 != 0) {
      throw new RuntimeException("La grille n'est pas carrée");
    }

    int caseNumber = serialized.split(":").length;
    int widthCaseNumber = (int) Math.sqrt(caseNumber);
    int heightCaseNumber = (int) Math.sqrt(caseNumber);

    int caseSize = 150;

    this.gamePane.setPrefSize(caseSize * widthCaseNumber, caseSize * heightCaseNumber);

    for (int i = 0; i < widthCaseNumber; i++) {
      for (int j = 0; j < heightCaseNumber; j++) {
        Rectangle r = new Rectangle(caseSize * i, caseSize * j, caseSize, caseSize);
        if (serialized.split(":")[i + j * widthCaseNumber].equals("w")) {
          r.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        } else {
          r.setStyle("-fx-fill: black; -fx-stroke: black; -fx-stroke-width: 1;");
        }
        r.setId("case:" + i + "_" + j);
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

  @Interact(id = {"case.*", "case:4_4"}, type = InteractType.ON_MOUSE_ENTERED)
  public void onCaseHover(MouseEvent event) {
    System.out.println("Hovering the case: " + ((Node) event.getSource()).getId());
  }
}