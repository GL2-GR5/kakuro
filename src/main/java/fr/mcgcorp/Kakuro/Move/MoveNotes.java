package fr.mcgcorp.Kakuro.Move;

import java.util.HashSet;
import java.util.Set;

/**
 * Représentation d'un mouvement modifiant les notes d'une case.
 *
 * @see Move
 * @author PECHON Erwan
 */
class MoveNotes extends Move {
  /** Notes modifié par le joueur. */
  protected Set<Integer> notesOld;
  /** Nouvelle notes saisie par le joueur. */
  protected Set<Integer> notesNew;

  /**
   * Constructeur d'un mouvement modifiant les notes.
   *
   * @param notesOld Les notes de la cellule, avant ce mouvement.
   * @param notesNew Les notes de la cellule, après ce mouvement.
   */
  MoveNotes(Set<Integer> notesOld, Set<Integer> notesNew) {
    super();
    this.notesOld = new HashSet<Integer>(notesOld);
    this.notesNew = new HashSet<Integer>(notesNew);
  }

  /**
   * Renvoie les anciennes notes de la case.
   *
   * @return Le vieux set de notes
   */
  public Set<Integer> getNotesOld() {
    return this.notesOld;
  }

  /**
   * Renvoie les nouvelles notes de la case.
   *
   * @return Le nouveaux set de notes
   */
  public Set<Integer> getNotesNew() {
    return this.notesNew;
  }
}
