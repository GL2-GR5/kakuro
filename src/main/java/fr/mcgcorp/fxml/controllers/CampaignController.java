package fr.mcgcorp.fxml.controllers;

import fr.mcgcorp.fxml.annotations.Interact;
import fr.mcgcorp.fxml.annotations.Interact.InteractType;
import fr.mcgcorp.fxml.managers.ControllerManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class CampaignController extends Controller {

  @Override
  String getPathToFxml() {
    return "campaign.fxml";
  }

  private ScrollPane scrollPane;

  private GridPane gridPane;

  private int index, rowi, columni;

  public CampaignController() {
    super();

    this.scrollPane = (ScrollPane) this.lookup("campaignEnvironment");

    this.gridPane = new GridPane();

    scrollPane.setContent(gridPane);

    this.index = 0;
    this.rowi = 0;
    this.columni = 0;

    this.initCamp();
  }

  // Faut recupérer le json de sauvegarde là mais je verrais ça plus tard
  private void initCamp() {

    gridPane.getColumnConstraints().add(new ColumnConstraints(100));
    gridPane.getRowConstraints().add(new RowConstraints(100));
    gridPane.setAlignment(Pos.BASELINE_CENTER);

    gridPane.add(new Circle(40, Paint.valueOf("Red")), 0, 0);
    gridPane.getChildren().get(0).setOnMouseClicked(mouseEvent -> {
      this.increment();
      newLevel();
    });

  }

  private void newLevel() {
    gridPane.getColumnConstraints().add(new ColumnConstraints(100));
    gridPane.getRowConstraints().add(new RowConstraints(100));
    gridPane.setAlignment(Pos.BASELINE_CENTER);

    gridPane.add(new Circle(40, Paint.valueOf("Red")), this.index, 0);
    gridPane.getChildren().get(index).setOnMouseClicked(mouseEvent -> {
      this.increment();
      newLevel();
    });
  }

  private void increment(){
    this.index += 1;
    if(this.columni > 8){
      this.rowi += 1;

    }


  }


  private void initIncr(){
    if()
  }



}
