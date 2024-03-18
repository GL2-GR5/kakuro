package fr.mcgcorp.fxml.managers;

import fr.mcgcorp.fxml.annotations.Interact;
import fr.mcgcorp.fxml.controllers.Controller;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class InteractManager {

  private static InteractManager interactManager;

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
          throw new IllegalArgumentException("No target id specified for the interact");
        }
        if (interact.type() == null) {
          throw new IllegalArgumentException("No type specified for the interact");
        }

        final Set<Node> affectedNodes = new HashSet<>();

        for (String id : interact.id()) {
          affectedNodes.addAll(controller.lookupAll(id));
        }

        if (affectedNodes.isEmpty()) {
          continue;
        }

        final Class<? extends Node> nodeClass = affectedNodes.iterator().next().getClass();

        for (Node n : affectedNodes) {
          if (n.getClass() != nodeClass) {
            throw new IllegalArgumentException("The affected nodes are not of the same type for " + n.getId());
          }
        }

        if (method.getReturnType() != void.class) {
          throw new IllegalArgumentException("The method " + method.getName() + " does not have the correct return type. Expected: void");
        }

        if (interact.type() == Interact.InteractType.ON_ACTION) {
          if (!ButtonBase.class.isAssignableFrom(nodeClass)) {
            throw new IllegalArgumentException("The item type " + nodeClass + " does not support the ON_ACTION type");
          }
          if (method.getParameterCount() != 1 || method.getParameterTypes()[0] != ActionEvent.class) {
            throw new IllegalArgumentException("The method " + method.getName()
                + " does not have the correct signature. Expected: method(ActionEvent event)");
          }

          affectedNodes.stream().map(n -> (ButtonBase) n).forEach(n -> n.setOnAction(e -> {
            try {
              method.invoke(controller, e);
            } catch (Exception ex) {
              throw new RuntimeException("Error while invoking the method " + method.getName(), ex);
            }
          }));
        } else {
          Class<? extends InputEvent> eventClass;

          switch (interact.type()) {
            case ON_MOUSE_CLICKED:
            case ON_MOUSE_ENTERED:
            case ON_MOUSE_EXITED:
            case ON_MOUSE_PRESSED:
            case ON_MOUSE_RELEASED:
            case ON_MOUSE_DRAGGED:
              eventClass = MouseEvent.class;
              break;
            case ON_KEY_PRESSED:
            case ON_KEY_RELEASED:
            case ON_KEY_TYPED:
              eventClass = KeyEvent.class;
              break;
            case ON_SCROLL:
            case ON_SCROLL_STARTED:
            case ON_SCROLL_FINISHED:
              eventClass = ScrollEvent.class;
              break;
            default:
              throw new IllegalArgumentException("The type " + interact.type() + " is not supported");
          }

          if (method.getParameterCount() != 1 || method.getParameterTypes()[0] != eventClass) {
            throw new IllegalArgumentException("The method " + method.getName()
                + " does not have the correct signature. Expected: method(" + eventClass.getSimpleName() + " event)");
          }

          Method associatedMethod;
          try {
            associatedMethod = nodeClass.getMethod(interact.type().getMethodName(), EventHandler.class);
          } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("The item type " + interact.id()[0].getClass() + " does not support the " + interact.type() + " type");
          }

          affectedNodes.forEach(n -> {
            EventHandler<? super InputEvent> handler = e -> {
              try {
                method.invoke(controller, e);
              } catch (Exception ex) {
                throw new RuntimeException("Error while invoking the method " + method.getName(), ex);
              }
            };
            try {
              associatedMethod.invoke(n, handler);
            } catch (Exception e) {
              throw new RuntimeException("Error while invoking the method " + associatedMethod.getName(), e);
            }
          });
        }
      }
    }
  }
}
