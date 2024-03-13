package fr.mcgcorp.controllers;

import fr.mcgcorp.fxmlbuilders.Interact;
import fr.mcgcorp.fxmlbuilders.Interact.InteractType;
import fr.mcgcorp.managers.ControllerManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Contrôleur du menu principal.
 */
public class MenuController extends Controller {

  @Override
  String getPathToFxml() {
    return "main_menu.fxml";
  }

  @Interact(id = "settingButton", type = InteractType.ON_ACTION)
  public void onSettingsButtonClicked(ActionEvent event) {
    ControllerManager.getInstance().getSettingsController().show();
  }

  @Interact(id = "quitButton", type = InteractType.ON_ACTION)
  public void onActionOnQuitButton(ActionEvent event) {
    System.out.println("On Action");
  }

  @Interact(id = "quitButton", type = InteractType.ON_MOUSE_ENTERED)
  public void onMouseEnteredOnQuitButton(MouseEvent event) {
    System.out.println("On Mouse Entered");
  }

  @Interact(id = "quitButton", type = InteractType.ON_MOUSE_EXITED)
  public void onMouseExitedOnQuitButton(MouseEvent event) {
    System.out.println("On Mouse Exited");
  }

  @Interact(id = "quitButton", type = InteractType.ON_MOUSE_CLICKED)
  public void onMouseClickedOnQuitButton(MouseEvent event) {
    System.out.println("On Mouse Clicked");
  }

  @Interact(id = "quitButton", type = InteractType.ON_MOUSE_PRESSED)
  public void onMousePressedOnQuitButton(MouseEvent event) {
    System.out.println("On Mouse Pressed");
  }

  @Interact(id = "quitButton", type = InteractType.ON_MOUSE_RELEASED)
  public void onMouseReleasedOnQuitButton(MouseEvent event) {
    System.out.println("On Mouse Released");
  }

  @Interact(id = "quitButton", type = InteractType.ON_MOUSE_DRAGGED)
  public void onMouseDraggedOnQuitButton(MouseEvent event) {
    System.out.println("On Mouse Dragged");
  }
}
