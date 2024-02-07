package fr.mcgcorp;

/**
 * Représentation d'une coordonnée dans un milieu matriciel et/ou graphique.
 * Cette classe à pour but de rassemblé les coordonnées transmise de méthodes en méthodes, afin de les rendres plus compréhensible.
 * L'objectif secondaire de cette classe est de servire de traducteur entre :
 * - les coordonnée de l'interface utilisateur
 * - les coordonnée de la matrice de jeu.
 *
 * @author PECHON Erwan
 */
class Coord {
  /** Le numéro de ligne dans la matrice de jeu. */
  protected int line;
  /** Le numéro de colonne dans la matrice de jeu. */
  protected int column;

  /**
   * Le constructeur des coordonnée est privé afin d'obligé les programmeur à utilisé des constructeurs avec des noms plus parlant à leurs besoins.
   * @author PECHON Erwan
   */
  private Coord() {
    this.line = -1;
    this.column = -1;
  }



  /**
   * Le constructeur des coordonnée dans le millieu de l'interface graphique.
   * @author PECHON Erwan
   * @param x Une coordonnée graphique
   * @param y L'autre coordonnée graphique
   * @return Les coordonnées du point désirer.
   */
  public static Coord createCoord_graphical(int x, int y) {
    Coord coord = new Coord();
    coord.setX(x);
    coord.setY(y);
    return coord;
  }

  /**
   * Un accesseur sur une coordonnée graphique.
   * @author PECHON Erwan
   * @return Une coordonnée graphique
   */
  public int getX() {
    return this.line;
  }

  /**
   * Un accesseur sur une coordonnée graphique.
   * @author PECHON Erwan
   * @return L'autre coordonnée graphique
   */
  public int getY() {
    return this.column;
  }

  /**
   * Un accesseur sur une coordonnée graphique.
   * @author PECHON Erwan
   * @param x Une coordonnée graphique
   */
  protected void setX(int x) {
    this.line = x;
  }

  /**
   * Un accesseur sur une coordonnée graphique.
   * @author PECHON Erwan
   * @param y L'autre coordonnée graphique
   */
  protected void setY(int y) {
    this.column = y;
  }



  /**
   * Le constructeur des coordonnée pour une matrice.
   * @author PECHON Erwan
   * @param line L'indice de la ligne où se trouve la case recherché.
   * @param column L'indice de la colonne où se trouve la case recherché.
   * @return Les coordonnées du point désirer.
   */
  public static Coord createCoord_matriciel(int line, int column) {
    Coord coord = new Coord();
    coord.setLine(line);
    coord.setColumn(column);
    return coord;
  }

  /**
   * Un accesseur sur une coordonnée matriciel.
   * @author PECHON Erwan
   * @return L'indice de la ligne où se trouve la case.
   */
  public int getLine() {
    return this.line;
  }

  /**
   * Un accesseur sur une coordonnée matriciel.
   * @author PECHON Erwan
   * @return L'indice de la colonne où se trouve la case.
   */
  public int getColumn() {
    return this.column;
  }

  /**
   * Un accesseur sur une coordonnée matriciel.
   * @author PECHON Erwan
   * @param line L'indice de la ligne où se trouve la case.
   */
  protected void setLine(int line) {
    this.line = line;
  }

  /**
   * Un accesseur sur une coordonnée matriciel.
   * @author PECHON Erwan
   * @param column L'indice de la colonne où se trouve la case.
   */
  protected void setColumn(int column) {
    this.column = column;
  }
}
