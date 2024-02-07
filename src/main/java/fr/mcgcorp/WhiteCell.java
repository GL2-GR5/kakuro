package fr.mcgcorp;

import java.util.HashSet;

/**
 * Cellule blanche, que le joueur doit modifier pour résoudre le puzzle.
 *
 * @see Cell
 */
class WhiteCell extends Cell {
  /** Valeur Maximal authorisé. */
  public static final int MAX_VALUE = 9;
  /** Valeur Minimal authorisé. */
  public static final int MIN_VALUE = 1;
  /** Valeur null (aucune valeur). */
  public static final int NULL_VALUE = WhiteCell.MIN_VALUE - 1;

  /** Valeur de résultat de la case. */
  private int correctValue;
  /** Valeur entrée par le joueur. */
  private int value;
  /** Liste des notes de la case. */
  private HashSet<Integer> notes;

  /**
   * Constructeur d'une cellule blanche.
   *
   * @param correctValue La valeur correcte de la cellule.
   */
  public WhiteCell(int correctValue) {
    this.correctValue = correctValue;
    this.value = WhiteCell.NULL_VALUE;
    this.notes = new HashSet<Integer>();
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
    if ((WhiteCell.MIN_VALUE < valeurNote) && (valeurNote < WhiteCell.MAX_VALUE)) {
      this.notes.add(valeurNote);
    }
  }

  /**
   * Retire une valeur des notes de la case.
   *
   * @param valeurNote note à retirer
   */
  public void removeNote(int valeurNote) {
    if ((WhiteCell.MIN_VALUE < valeurNote) && (valeurNote < WhiteCell.MAX_VALUE)) {
      this.notes.add(valeurNote);
    }
  }

  /**
   * Efface la valeur entrée par le joueur.
   */
  public void clear() {
    this.value = WhiteCell.NULL_VALUE;
  }

  /**
   * Efface les notes de la case.
   */
  public void clearNotes() {
    this.notes.clear();
  }

  /**
   * Efface la valeur entrée par le joueur et les notes de la case.
   */
  public void clearAll() {
    this.clear();
    this.clearNotes();
  }
}
