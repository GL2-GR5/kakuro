package fr.mcgcorp.fxmlbuilders;

import javafx.event.ActionEvent;

public interface ItemAction {

  default void onItemAction(ActionEvent event) {
    // Do nothing
  }
}
