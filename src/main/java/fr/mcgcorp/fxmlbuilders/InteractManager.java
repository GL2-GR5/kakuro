package fr.mcgcorp.fxmlbuilders;

import fr.mcgcorp.controllers.Controller;
import java.lang.reflect.Method;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;

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

        Class<? extends Node> nodeClass = null;

        for (String id : interact.id()) {
          if (controller.lookup(id) != null) {
            nodeClass = controller.lookup(id).getClass();
            break;
          }
        }

        if (nodeClass == null) {
          continue;
        }

        int stop = 0;
        for (String id : interact.id()) {
          if (controller.lookup(id).getClass() != nodeClass) {
            stop = 1;
          }
        }
        if (stop == 1) {
          continue;
        }

        if (interact.type() == Interact.InteractType.ON_ACTION) {
          if (!nodeClass.isAssignableFrom(ButtonBase.class)) {
            continue;
          }
          if (method.getParameterCount() != 1 || method.getParameterTypes()[0] != ActionEvent.class) {
            continue;
          }

          for (String id : interact.id()) {
            final Node n = controller.lookup(id);
            if (n == null) {
              continue;
            }

            ((ButtonBase) n).setOnAction(e -> {
              try {
                method.invoke(controller, e);
              } catch (Exception ex) {
                throw new RuntimeException("Error while invoking the method " + method.getName(), ex);
              }
            });
          }
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
            default:
              throw new IllegalArgumentException("The type " + interact.type() + " is not supported");
          }

          if (method.getParameterCount() != 1 || method.getParameterTypes()[0] != eventClass) {
            continue;
          }

          Method associatedMethod;
          try {
            associatedMethod = nodeClass.getMethod(interact.type().getMethodName(), EventHandler.class);
          } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("The item type " + interact.id()[0].getClass() + " does not support the " + interact.type() + " type");
          }

          for (String id : interact.id()) {
            final Node n = controller.lookup(id);
            if (n == null) {
              continue;
            }

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
          }
        }
      }
    }
  }
}
