package fr.mcgcorp.fxml.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Interact {
  String[] id() default {};
  InteractType type() default InteractType.ON_ACTION;

  enum InteractType {
    ON_ACTION("setOnAction"),
    ON_MOUSE_CLICKED("setOnMouseClicked"),
    ON_MOUSE_ENTERED("setOnMouseEntered"),
    ON_MOUSE_EXITED("setOnMouseExited"),
    ON_MOUSE_PRESSED("setOnMousePressed"),
    ON_MOUSE_RELEASED("setOnMouseReleased"),
    ON_MOUSE_DRAGGED("setOnMouseDragged"),
    ON_KEY_PRESSED("setOnKeyPressed"),
    ON_KEY_RELEASED("setOnKeyReleased"),
    ON_KEY_TYPED("setOnKeyTyped"),
    ON_SCROLL("setOnScroll"),
    ON_SCROLL_STARTED("setOnScrollStarted"),
    ON_SCROLL_FINISHED("setOnScrollFinished");

    private final String methodName;

    InteractType(String methodName) {
      this.methodName = methodName;
    }

    public String getMethodName() {
      return methodName;
    }
  }
}