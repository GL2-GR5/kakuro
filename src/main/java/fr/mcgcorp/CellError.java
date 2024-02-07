package fr.mcgcorp;

/**
 * Détail l'erreur de saisie sur une cellule.
 *
 * @author PECHON Erwan
 */
class CellError {
  /** Coordonnée de la case où l'erreur à eu lieu. */
  protected Coord coord;
  /** L'erreur détecté sur cette cellule. */
  protected TypeError error;

  /**
   * Constructeur de rapport d'erreur.
   *
   * @param coord Les coordonnées de la cellule où une erreur à était déteté.
   * @param error Le type d'erreur détecté.
   */
  CellError(Coord coord, TypeError error) {
    this.coord = coord;
    this.error = error;
  }

  /**
   * Accesseur sur les coordonnée de la case où l'erreur à eu lieu.
   * Permet au jeu de connaître la case associé à cette erreur.
   */
  public Coord getCoord() {
    return this.coord;
  }

  /**
   * Accesseur sur le type d'erreur détecté.
   * Permet au jeu de connaître le type d'erreur ayant eu lieu.
   * 
   */
  public TypeError getTypeError() {
    return this.error;
  }
}
