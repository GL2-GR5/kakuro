package fr.mcgcorp;

/**
 * Représentation d'un mouvement fait par l'utilisateur.
 * Cette classe et ses descendant permettront de stocker les mouvements de l'utilisateur, et donc de les annuler ou les refaire.
 *
 * @author PECHON Erwan
 */
abstract class Move {
  /** Coordonnée de la case où le mouvement à eu lieu. */
  protected Coord coord;

  /** Constructeur de base du mouvement. */
  protected Move() {
    this.coord = null;
  }

  /**
   * Constructeur du mouvement.
   *
   * @param coord Les coordonnées de la case où le mouvement c'est joué.
   */
  protected Move(Coord coord) {
    this.coord = coord;
  }

  /** 
   * Accesseur sur les coordonnée de la case où le mouvement à eu lieu.
   * Permet au jeu de connaître la case associé à ce mouvement.
   *
   * @return cette objet Coord.
   */
  Coord getCoord() {
    return this.coord;
  }

  /**
   * Accesseur sur les coordonnée de la case où le mouvement à eu lieu.
   * Permet au jeu de définir la case associé à ce mouvement.
   * Cette méthode ne peut être appelé qu'une seul fois, toute appel supplémentaire sera simplement ignoré.
   *
   * @param coord la nouvelle coordonnée de la case associé à ce mouvement.
   */
  void setCoord(Coord coord) {
    if (this.coord == null) {
      this.coord = coord;
    }
  }

  /**
   * Accesseur sur l'ancien contenu de la cellule.
   *
   * @return L'ancien contenu de la cellule.
   */
  abstract Object getOld();

  /**
   * Accesseur sur le nouveau contenu de la cellule.
   *
   * @return Le nouveau contenu de la cellule.
   */
  abstract Object getNew();
}
