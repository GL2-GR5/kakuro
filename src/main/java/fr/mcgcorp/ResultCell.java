package fr.mcgcorp;

/**
 * Contient le résultat demandé pour une ligne et/ou une colonne.
 *
 * @author HOUGET Julien, PUREN Mewen, PECHON Erwan
 */
class ResultCell implements Cell {
  //
  // Attributs d'instance
  //

  /** Résultat attendu pour une ligne. */
  private int line;
  /** Résultat attendu pour une colonne. */
  private int column;

  //
  // Constructeur
  //

  /**
   * Constructeur d'une cellule de résultat.
   *
   * @param line Le résultat attendu pour la ligne.
   * @param column Le résultat attendu pour la colonne.
   */
  ResultCell(int line, int column) {
    super();
    this.line = line;
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
  int getLine() {
    return this.line;
  }

  /**
   * Renvoie la somme attendu pour la colonne en dessous de cette cellule.
   *
   * @return Le résultat attendu pour la colonne.
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
    return "" + this.column + "\\" + this.line;
  }

  @Override
  public String serialize() {
    return "" + this.column + ',' + this.line;
  }
}
