package fr.mcgcorp.fxmlbuilders;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Interact {

  String[] id() default {};
  String action() default "click";
  ItemType type() default ItemType.BUTTON;

  public enum ItemType {
    BUTTON(Button.class),
    MENU_ITEM(MenuItem.class);

    final Class<?> associated;

    ItemType(final Class<?> associated) {
      this.associated = associated;
    }

    public Class<?> getAssociatedType() {
      return this.associated;
    }
  }
}