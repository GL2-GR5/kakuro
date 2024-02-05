package fr.mcgcorp;

import java.util.HashMap;

/**
 * Cellule blanche, que le joueur doit modifier pour résoudre le puzzle.
 *
 * @see Cell
 */
class WhiteCell extends Cell {

  /** Valeur de résultat de la case. */
  private int correctValue;
  /** Valeur entre par le joueur. */
  private int value;

  /** Liste des notes de la case. */
  private HashMap<Integer, Boolean> notes = new HashMap<Integer, Boolean>();

  /**
   * Constructeur d'une cellule blanche.
   *
   * @param correctValue La valeur correcte de la cellule.
   */
  public WhiteCell(int correctValue) {
    this.correctValue = correctValue;
    this.value = 0;
    for (int i = 1; i <= 9; i++) {
      notes.put(i, false);
    }
  }
  
  /**
   * Renvoie la valeur correcte de la case. 
   *
   * @return int 
   */
  public int getCorrectValue() {
    return this.correctValue;
  }

  /**
   * Renvoie la valeur entrée par le joueur.
   * 
   * @return int
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Ajoute une valeur en note à la case.
   *
   * @param valeurNote note à ajouter
   */
  public void addNote(int valeurNote) {
    this.notes.put(valeurNote, true);
  }

  /**
   * Retire une valeur des notes de la case.
   *
   * @param valeurNote note à retirer
   */
  public void removeNote(int valeurNote) {
    this.notes.put(valeurNote, false);
  }

  /**
   * Efface la valeur entrée par le joueur.
   */
  public void clearCell() {
    this.value = 0;
    for (int i : notes.keySet()) {
      notes.put(i, false);
    }
  }

  /**
   * Efface les notes de la case.
   */
  public void clearNotes() {
    for (int i : notes.keySet()) {
      notes.put(i, false);
    }
  }

  /**
   * Efface la valeur entrée par le joueur et les notes de la case.
   */
  public void clearAll() {
    this.clearCell();
    this.clearNotes();
  }
}
