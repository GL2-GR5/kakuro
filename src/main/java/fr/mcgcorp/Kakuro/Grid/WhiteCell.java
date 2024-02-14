package fr.mcgcorp.Grid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Cellule blanche, que le joueur doit modifier pour résoudre le puzzle.
 *
 * @see Cell
 */
class WhiteCell implements Cell {
  /** Valeur de résultat de la case. */
  private int correctValue;
  /** Valeur entrée par le joueur. */
  private int value;
  /** Liste des notes de la case. */
  private Set<Integer> notes;

  /**
   * Constructeur d'une cellule blanche.
   *
   * @param correctValue La valeur correcte de la cellule.
   */
  public WhiteCell(int correctValue) {
    this.correctValue = correctValue;
    this.notes = new HashSet<Integer>();
    this.clearAll();
  }

  /**
   * Renvoie la valeur correcte de la case.
   *
   * @return La valeur que le joueur doit saisir pour avoir bon.
   */
  public int getCorrectValue() {
    return this.correctValue;
  }

  /**
   * Renvoie la valeur entrée par le joueur.
   *
   * @return La valeur saisie par le joueur.
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Saisit la valeur entrée par le joueur.
   *
   * @param value La valeur donnée par le joueur.
   */
  public void setValue(int value) {
    if ((Kakuro.MIN_VALUE() <= value) && (value <= Kakuro.MAX_VALUE())) {
      this.value = value;
    }
    if (value == Kakuro.NULL_VALUE()){
      this.value = value;
    }
  }

  /**
   * Vérifie si la valeur saisit par l'utilisateur est la bonne valeur.
   *
   * @return résultat
   */
  public boolean isCorrect() {
    return (this.value == this.correctValue);
  }

  /**
   * Ajoute une valeur en note à la case.
   *
   * @param valeurNote note à ajouter
   */
  public void addNote(int valeurNote) {
    if ((Kakuro.MIN_VALUE <= valeurNote) && (valeurNote <= Kakuro.MAX_VALUE)) {
      this.notes.add(valeurNote);
    }
  }

  /**
   * Ajoute une valeur en note à la case.
   *
   * @param valeurNote note à ajouter
   */
  public void addNote(Integer valeurNote) {
    if ((Kakuro.MIN_VALUE <= valeurNote) && (valeurNote <= Kakuro.MAX_VALUE)) {
      this.notes.add(valeurNote);
    }
  }

  /**
   * Retire une valeur des notes de la case.
   *
   * @param valeurNote note à retirer
   */
  public void removeNote(int valeurNote) {
    if ((Kakuro.MIN_VALUE <= valeurNote) && (valeurNote <= Kakuro.MAX_VALUE)) {
      this.notes.remove(valeurNote);
    }
  }

  /**
   * Redéfinit le set de notes du joueur.
   *
   * @param notes Le nouveau set de notes.
   */
  public void setNotes(int[] notes) {
    this.clearNotes();
    if (notes != null) {
      for (int note : notes){
        this.addNote(note);
      }
    }
  }

  /**
   * Redéfinit le set de notes du joueur.
   *
   * @param notes Le nouveau set de notes.
   */
  public void setNotes(Set<Integer> notes) {
    this.clearNotes();
    if (notes != null) {
      for (Integer note : notes) {
        this.addNote(note);
      }
    }
  }

  /**
   * Envoi le set de notes du joueur.
   *
   * @return La liste des notes sélectionnée.
   */
  public Set<Integer> getNotes() {
    return (new HashSet<Integer>(this.notes));
  }

  /**
   * Efface la valeur entrée par le joueur.
   */
  public void clear() {
    this.value = Kakuro.NULL_VALUE();
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
