package fr.mcgcorp;

/**
 * Contient le résultat demandé pour une ligne et/ou une colonne.
 *
 * @author HOUGET Julien, PUREN Mewen, PECHON Erwan
 */
class ResultCell extends Cell {
  //
  // Attributs d'instance
  //

  /** Résultat attendu pour une ligne. */
  private int row;
  /** Résultat attendu pour une colonne. */
  private int column;

  //
  // Constructeur
  //

  /**
   * Constructeur d'une cellule de résultat.
   *
   * @param row    Le résultat attendu pour la ligne.
   * @param column Le résultat attendu pour la colonne.
   */
  ResultCell(int row, int column) {
    super();
    this.row = row;
    this.column = column;
  }

  //
  // Accesseur
  //

  /**
   * Renvoie la somme attendu pour la ligne à droite de cette cellule.
   *
   * @return Le résultat attendu pour la ligne.
   */
  int getRow() {
    return this.row;
  }

  /**
   * Renvoie la somme attendu pour la colonne en dessous de cette cellule.
   *
   * @return Le résultat attendu pour la ligne.
   */
  int getColumn() {
    return this.column;
  }

  //
  // Affichage
  //

  /**
   * Affiche une cellule de résultat.
   *
   * @return La cellule formater pour affichage
   */
  @Override
  public String toString() {
    return this.column + "\\" + this.row;
  }
}
