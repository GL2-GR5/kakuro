package fr.mcgcorp;

/**
 * Représentation d'un mouvement modifiant la valeur d'une cellule blanche.
 * @see Move
 * @author PECHON Erwan
 */
class MoveValue extends Move {
  /** Valeur modifié par le joueur. */
  protected int valueOld;
  /** Nouvelle valeur saisie par le joueur. */
  protected int valueNew;

  /**
   * Constructeur d'un mouvement modifiant une valeur.
   *
   * @param valueOld La valeur de la cellule, avant ce mouvement.
   * @param valueNew La valeur de la cellule, après ce mouvement.
   */
  MoveValue(int valueOld, int valueNew) {
    super();
    this.valueOld = valueOld;
    this.valueNew = valueNew;
  }

  @Override
  Object getOld() {
    return this.valueOld;
  }

  @Override
  Object getNew() {
    return this.valueNew;
  }
}

