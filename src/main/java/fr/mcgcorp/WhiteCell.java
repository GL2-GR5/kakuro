package fr.mcgcorp;

import java.util.HashMap;

/**
 * Cellule blanche, que le joueur doit modifier pour résoudre le puzzle.
 *
 * @see Cell
 */
class WhiteCell extends Cell {
  private int correctValue;
  private int value;

  private HashMap<Integer, Boolean> notes = new HashMap<Integer, Boolean>();

  public WhiteCell(int correctValue) {
    this.correctValue = correctValue;
    this.value = 0;
    for (int i = 1; i <= 9; i++) {
      notes.put(i, false);
    }
  }

  public int getCorrectValue() {
    return this.correctValue;
  }

  public void setCorrectValue(int correctValue) {
    this.correctValue = correctValue;
  }

  public int getValue() {
    return this.value;
  }

  @Override
  public boolean setValue(int value) {
    this.value = value;
    return true;
  }

  @Override
  public boolean isModifiable() {
    return true;
  }

  public void addNote(int valeurNote) {
    this.notes.put(valeurNote, true);
  }

  public void removeNote(int valeurNote) {
    this.notes.put(valeurNote, false);
  }

  public void clearCell() {
    this.value = 0;
    for (int i : notes.keySet()) {
      notes.put(i, false);
    }
  }

  public void clearNotes() {
    for (int i : notes.keySet()) {
      notes.put(i, false);
    }
  }
}
