package fr.mcgcorp.controllers;

import fr.mcgcorp.fxmlbuilders.ClassicStage;
import fr.mcgcorp.fxmlbuilders.InterfaceStage;
import fr.mcgcorp.fxmlbuilders.ItemAction;
import fr.mcgcorp.fxmlbuilders.ItemType;
import fr.mcgcorp.fxmlbuilders.OverStage;
import fr.mcgcorp.managers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;

/**
 * Contrôleur du menu principal.
 */
public class MenuController extends Controller {

  private ClassicStage stage;

  public MenuController() {
    super();
    loadItem(ItemType.BUTTON, new ItemAction() {
      @Override
      public void onItemAction(ActionEvent event) {
        if (((Node) event.getSource()).getId().equals("settingButton")) {
          onSettingsButton();
        }
      }
    });
  }

  private void onSettingsButton() {
    ((OverStage) ControllerManager.getInstance().getSettingsController().getStage()).show(this.stage.getStage());
  }

  @Override
  protected InterfaceStage getStage() {
    if (this.stage == null) {
      this.stage = new ClassicStage();
    }
    return this.stage;
  }

  @Override
  String getPathToFxml() {
    return "main_menu.fxml";
  }
}
