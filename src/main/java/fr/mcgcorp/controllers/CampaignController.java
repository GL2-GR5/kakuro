package fr.mcgcorp.controllers;

import fr.mcgcorp.fxmlbuilders.ClassicStage;
import fr.mcgcorp.fxmlbuilders.InterfaceStage;

public class CampaignController extends Controller {

  private InterfaceStage stage;

  @Override
  protected InterfaceStage getStage() {
    if (this.stage == null) {
      this.stage = new ClassicStage();
    }
    return this.stage;
  }

  @Override
  String getPathToFxml() {
    return "campaign.fxml";
  }
}
