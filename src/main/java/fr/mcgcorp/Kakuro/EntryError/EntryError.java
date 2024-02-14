package fr.mcgcorp.Kakuro.EntryError;

import fr.mcgcorp.Kakuro.Coord;

/**
 * Détail l'erreur de saisie sur une cellule.
 *
 * @author PECHON Erwan
 */
public class EntryError {
  /** Coordonnée de la case où l'erreur à eu lieu. */
  protected Coord coord = null;
  /** L'erreur détecté sur cette cellule. */
  protected TypeEntryError error = null;

  /**
   * Constructeur de base du rapport d'erreur.
   */
  EntryError() {
  }

  /**
   * Constructeur de rapport d'erreur.
   *
   * @param coord Les coordonnées de la cellule où une erreur à était détecté.
   */
  EntryError(Coord coord) {
    this.coord = coord;
  }

  /**
   * Constructeur de rapport d'erreur.
   *
   * @param error Le type d'erreur détecté.
   */
  EntryError(TypeEntryError error) {
    this.error = error;
  }

  /**
   * Constructeur de rapport d'erreur.
   *
   * @param coord Les coordonnées de la cellule où une erreur à était détecté.
   * @param error Le type d'erreur détecté.
   */
  EntryError(Coord coord, TypeEntryError error) {
    this.coord = coord;
    this.error = error;
  }

  /**
   * Accesseur sur les coordonnée de la case où l'erreur à eu lieu.
   * Permet au jeu de connaître la case associé à cette erreur.
   *
   * @return Les coordonnée de l'erreur.
   */
  public Coord getCoord() {
    return this.coord;
  }

  /**
   * Accesseur sur les coordonnée de la case où l'erreur à eu lieu.
   * Permet de choisir une case.
   * Les coordonnée ne peuvent-être changé que si il n'y en avais pas encore de
   * définit.
   *
   * @param coord Les coordonnée de l'erreur.
   */
  public void setCoord(Coord coord) {
    if (this.coord == null) {
      this.coord = coord;
    }
  }

  /**
   * Accesseur sur le type d'erreur détecté.
   * Permet au jeu de connaître le type d'erreur ayant eu lieu.
   *
   * @return Le type de l'erreur.
   */
  public TypeEntryError getError() {
    return this.error;
  }

  /**
   * Accesseur sur le type d'erreur détecté.
   * Permet au jeu de connaître le type d'erreur ayant eu lieu.
   * Le type d'erreur ne peut-être changé que si il n'y en avais pas encore de
   * définit.
   *
   * @param error Le type de l'erreur.
   */
  public void setTypeEntryError(TypeEntryError error) {
    if (this.error == null) {
      this.error = error;
    }
  }
}
