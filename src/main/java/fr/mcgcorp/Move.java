package fr.mcgcorp;

/**
 * Représentation d'un mouvement fait par l'utilisateur.
 * @author PECHON Erwan
 *
 * Cette classe et ses descendant permettront de stocker les mouvements de l'utilisateur, et donc de les annuler ou les refaires.
 *
 */
abstract class Move {
  /** Coordonée de la case où le mouvement à eu lieu. */
  protected Coord coord;

  /** Constructeur du mouvement
   * @author PECHON Erwan
   */
  protected Move() {
    this.coord = null;
  }

  /** Accesseur sur les coordonnée de la case où le mouvement à eu lieu.
   * @author PECHON Erwan
   *
   * Permet au jeu de connaître la case associé à ce mouvement.
   *
   */
  public Coord getCoord() {
    return this.coord;
  }
  /** Accesseur sur les coordonnée de la case où le mouvement à eu lieu.
   * @author PECHON Erwan
   *
   * Permet au jeu de définire la case associé à ce mouvement.
   * Cette méthode ne peut être appelé qu'une seul fois, toute appel supplémentaire sera simplement ignoré.
   *
   */
  public void setCoord(Coord coord) {
    if( this.coord == null ) {
      this.coord = coord;
    }
  }
}

