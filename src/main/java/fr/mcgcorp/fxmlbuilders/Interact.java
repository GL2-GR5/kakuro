package fr.mcgcorp.fxmlbuilders;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Interact {
  String[] id() default {};
  InteractType type() default InteractType.ON_ACTION;
  String[] scrollPane() default "";

  enum InteractType {
    ON_ACTION("setOnAction"),
    ON_MOUSE_CLICKED("setOnMouseClicked"),
    ON_MOUSE_ENTERED("setOnMouseEntered"),
    ON_MOUSE_EXITED("setOnMouseExited"),
    ON_MOUSE_PRESSED("setOnMousePressed"),
    ON_MOUSE_RELEASED("setOnMouseReleased"),
    ON_MOUSE_DRAGGED("setOnMouseDragged");

    private final String methodName;

    InteractType(String methodName) {
      this.methodName = methodName;
    }

    public String getMethodName() {
      return methodName;
    }
  }
}