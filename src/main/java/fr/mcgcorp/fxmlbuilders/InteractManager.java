package fr.mcgcorp.fxmlbuilders;

import fr.mcgcorp.controllers.Controller;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class InteractManager {

  private static InteractManager interactManager;
  Map<String[], Pair<Object, Method>> interactMap;

  public static InteractManager getInstance() {
    if (interactManager == null) {
      interactManager = new InteractManager();
    }
    return interactManager;
  }

  public void registerAll(Controller controller) {
    for (final Method method : controller.getClass().getDeclaredMethods()) {
      if (method.isAnnotationPresent(Interact.class)) {
        final Interact interact = method.getAnnotation(Interact.class);

        if (interact.id().length == 0) {
          throw new IllegalArgumentException("No id specified for the interact");
        }
        if (interact.action() == null) {
          throw new IllegalArgumentException("No action specified for the interact");
        }
        if (interact.type() == null) {
          throw new IllegalArgumentException("No type specified for the interact");
        }

        for (String id : interact.id()) {
          if (controller.getStage().getScene().lookup(id) == null) {
            throw new IllegalArgumentException("The id " + id + " does not exist in the scene");
          }
          if (!controller.getStage().getScene().lookup(id).getClass().equals(interact.type().getAssociatedType())) {
            throw new IllegalArgumentException("The type of the interact does not match the type of the node");
          }
        }

        if (interact.type().getAssociatedType().equals(Button.class)) {
          for (String id : interact.id()) {
            ((Button) controller.getStage().getScene().lookup(id)).setOnAction((event) -> {
              try {
                method.invoke(controller, event);
              } catch (Exception e) {
                e.printStackTrace();
              }
            });
          }
        }
      }
    }
  }
}
