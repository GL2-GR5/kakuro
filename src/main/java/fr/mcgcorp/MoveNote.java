package fr.mcgcorp;

import java.util.HashSet;

/**
 * Représentation d'un mouvement modifiant les notes d'une case.
 *
 * @see Move
 * @author PECHON Erwan
 */
class MoveNote extends Move {
  /** Notes modifié par le joueur. */
  protected HashSet<Integer> notesOld;
  /** Nouvelle notes saisie par le joueur. */
  protected HashSet<Integer> notesNew;

  /**
   * Constructeur d'un mouvement modifiant les notes.
   *
   * @param notesOld Les notes de la cellule, avant ce mouvement.
   * @param notesNew Les notes de la cellule, après ce mouvement.
   */
  MoveNote(HashSet<Integer> notesOld, HashSet<Integer> notesNew) {
    super();
    this.notesOld = notesOld;
    this.notesNew = notesNew;
  }

  /**
   * Renvoie les anciennes notes de la case.
   *
   * @return Le vieux set de notes
   */
  public HashSet<Integer> getNotesOld() {
    return this.notesOld;
  }

  /**
   * Renvoie les nouvelles notes de la case.
   *
   * @return Le nouveaux set de notes
   */
  public HashSet<Integer> getNotesNew() {
    return this.notesNew;
  }
}
