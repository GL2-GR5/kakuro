package fr.mcgcorp.fxmlbuilders;

public enum ItemType {
  BUTTON(".button"),
  CHECKBOX(".checkbox"),
  MENU_ITEM(".menu-item");

  private final String type;

  ItemType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }
}
