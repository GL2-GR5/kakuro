package fr.mcgcorp.game.move;

/**
 * Représentation d'un mouvement modifiant la valeur d'une cellule blanche.
 * @see Move
 * @author PECHON Erwan
 */
public class MoveValue extends Move {
  /** Valeur modifiée par le joueur. */
  protected int valueOld;
  /** Nouvelle valeur saisie par le joueur. */
  protected int valueNew;

  /**
   * Constructeur d'un mouvement modifiant une valeur.
   *
   * @param valueOld La valeur de la cellule, avant ce mouvement.
   * @param valueNew La valeur de la cellule, après ce mouvement.
   */
  public MoveValue(int valueOld, int valueNew) {
    super();
    this.valueOld = valueOld;
    this.valueNew = valueNew;
  }

  @Override
  public Object getOld() {
    return this.valueOld;
  }

  @Override
  public Object getNew() {
    return this.valueNew;
  }
}

