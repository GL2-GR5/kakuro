package fr.mcgcorp.game.move;

//package externe
import java.util.Set;

/**
 * Représentation d'un mouvement modifiant les notes d'une case.
 *
 * @see Move
 * @author PECHON Erwan
 */
public class MoveNotes extends Move {
  /** Notes modifiées par le joueur. */
  protected Set<Integer> notesOld;
  /** Nouvelles notes saisies par le joueur. */
  protected Set<Integer> notesNew;

  /**
   * Constructeur d'un mouvement modifiant les notes.
   *
   * @param notesOld Les notes de la cellule, avant ce mouvement.
   * @param notesNew Les notes de la cellule, après ce mouvement.
   */
  public MoveNotes(Set<Integer> notesOld, Set<Integer> notesNew) {
    super();
    this.notesOld = notesOld;
    this.notesNew = notesNew;
  }

  @Override
  public Object getOld() {
    return this.notesOld;
  }

  @Override
  public Object getNew() {
    return this.notesNew;
  }
}