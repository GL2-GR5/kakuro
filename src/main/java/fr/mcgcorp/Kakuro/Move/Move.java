package fr.mcgcorp.Kakuro.Move;

import fr.mcgcorp.Kakuro.Coord;

/**
 * Représentation d'un mouvement fait par l'utilisateur.
 * Cette classe et ses descendant permettront de stocker les mouvements de l'utilisateur, et donc de les annuler ou les refaire.
 *
 * @author PECHON Erwan
 */
public abstract class Move {
  /** Coordonnée de la case où le mouvement à eu lieu. */
  protected Coord coord;

  /** Constructeur de base du mouvement. */
  protected Move() {
    this.coord = null;
  }

  /** Constructeur du mouvement. */
  protected Move(Coord coord) {
    this.coord = coord;
  }

  /** 
   * Accesseur sur les coordonnée de la case où le mouvement à eu lieu.
   * Permet au jeu de connaître la case associé à ce mouvement.
   *
   * @return cette objet Coord.
   */
  public Coord getCoord() {
    return this.coord;
  }

  /** 
   * Accesseur sur les coordonnée de la case où le mouvement à eu lieu.
   * Permet au jeu de définir la case associé à ce mouvement.
   * Cette méthode ne peut être appelé qu'une seul fois, toute appel supplémentaire sera simplement ignoré.
   *
   * @param coord la nouvelle coordonnée de la case associé à ce mouvement.
   */
  public void setCoord(Coord coord) {
    if (this.coord == null) {
      this.coord = coord;
    }
  }
}

