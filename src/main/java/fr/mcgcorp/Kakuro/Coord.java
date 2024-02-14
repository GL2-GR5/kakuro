package fr.mcgcorp.Kakuro;

import java.util.function.Supplier;

/**
 * Représentation d'une coordonnée dans un milieu matriciel et/ou graphique.
 * Cette classe à pour but de rassemblé les coordonnées transmise de méthodes en méthodes, afin de les rendre plus compréhensible.
 * L'objectif secondaire de cette classe est de servir de traducteur entre :
 * - les coordonnée de l'interface utilisateur
 * - les coordonnée de la matrice de jeu.
 *
 * @author PECHON Erwan
 */
public class Coord implements Comparable<Coord> {
  /** L'indice de la cellule dans la matrice de jeu, aplatie. */
  protected int numCell = 0;

  /**
   * Le constructeur de coordonnée de base.
   * Pointe la première cellule de la matrice de jeu.
   */
  Coord() {
    this.numCell = 0;
  }

  /**
   * Le constructeur de coordonnée dans le milieu graphique.
   * Pointe la cellule correspondant à l'id.
   *
   * @param id L'id de la case à ciblé.
   */
  Coord(String id) {
    String idSplit[] = id.split("_");
    this.numCell = Integer.parseInt(idSplit[0]) * Kakuro.LAST_COORD().getColumn();
    this.numCell+= Integer.parseInt(idSplit[1]);
  }

  /**
   * Le constructeur de coordonnée dans le milieu matriciel.
   * Pointe la cellule ce trouvant sur la **line**-ième ligne, et la
   * **column**-ième colonne.
   *
   * @param line Le numéro de ligne de la cellule visé.
   * @param column Le numéro de colonne de la cellule visé.
   */
  Coord(int line, int column) {
    this.numCell = line * Kakuro.LAST_COORD().getColumn();
    this.numCell+= column;
  }

  /**
   * Le constructeur de coordonnée dans le milieu matriciel aplatie.
   * Pointe la cellule correspondant à l'indice demandé.
   *
   * @param indice L'indice de la cellule à ciblé.
   */
  Coord(int indice) {
    this.numCell = indice;
  }




  /**
   * Un accesseur sur une coordonnée graphique.
   *
   * @return L'id de la case.
   */
  public String getId() {
    return "" + this.getLine() + "_" + this.getColumn();
  }




  /**
   * Un accesseur sur une coordonnée matriciel.
   *
   * @return L'indice de la ligne où se trouve la case.
   */
  public int getLine() {
    return this.numCell / Kakuro.LAST_COORD().getColumn();
  }

  /**
   * Un accesseur sur une coordonnée matriciel.
   *
   * @return L'indice de la colonne où se trouve la case.
   */
  public int getColumn() {
    return this.numCell % Kakuro.LAST_COORD().getColumn();
  }




  /**
   * Un accesseur sur une coordonnée matriciel aplatie.
   *
   * @return L'indice de la case à ciblé.
   */
  public int getIndice() {
    return this.numCell;
  }

  /**
   * Un accesseur sur une coordonnée matriciel aplatie.
   *
   * @param indice L'indice de la case à ciblé.
   */
  protected void setIndice(int indice) {
    this.numCell = indice;
  }




  /**
   * Cible la cellule suivante.
   *
   * @return Est-on passé à une nouvelle ligne ?
   */
  public boolean nextCell() {
    this.numCell+= 1;
    return (this.numCell % Kakuro.LAST_COORD().getColumn()) == 0;
  }

  /**
   * Cible la cellule précédente.
   *
   * @return Est-on passé à la ligne précédente ?
   */
  public boolean previousCell() {
    boolean restart = (this.numCell % Kakuro.LAST_COORD().getColumn()) == 0;
    this.numCell-= 1;
    return restart;
  }

  /**
   * Cible la cellule de la ligne suivante.
   *
   * @return Est-on passé à une nouvelle colonne ?
   */
  public boolean nextLine() {
    Coord lim = Kakuro.LAST_COORD();
    if (this.getLine() == lim.getLine()) {
      this.newColumn();
    }
    this.numCell+= lim.getColumn();
    return false;
  }

  /**
   * Cible la cellule de la ligne précédente.
   *
   * @return Est-on passé à la colonne précédente ?
   */
  public boolean previousLine() {
    if (this.getLine() == 0) {
      return this.oldColumn();
    }
    this.numCell-= Kakuro.LAST_COORD().getColumn();
    return false;
  }

  /**
   * Va au début de la colonne suivante.
   *
   * @return true
   */
  public boolean newColumn() {
    this.numCell = this.getColumn() + 1;
    return true;
  }

  /**
   * Va au début de la colonne précédente.
   *
   * @return true
   */
  public boolean oldColumn() {
    this.numCell = this.getColumn() - 1;
    return true;
  }

  /**
   * Va au début de la ligne suivante.
   *
   * @return true
   */
  public boolean newLine() {
    this.numCell = this.getLine() + 1;
    return true;
  }

  /**
   * Va au début de la ligne précédente.
   *
   * @return true
   */
  public boolean oldLine() {
    this.numCell = this.getLine() - 1;
    return true;
  }

  /**
   * Fait plusieurs mouvement dans la direction demandé..
   *
   * @param i;
   * @return Combien de fois le bout de la matrice à était atteint ?
   */
  public int moveCoord(Supplier<Boolean> method, int nbRep) {
    int nbTrue = 0;
    for( int i=0 ; i<nbRep ; i++ ){
      if( method.get() ){
        nbTrue++;
      }
    }
    return nbTrue;
  }




  /**
   * Test si les coordonnée cible bien une case faisant partie du jeu.
   *
   * @return **true** si la case ciblé fait bien partie de la grille du jeu.
   */
  public boolean exist(){
    if( this.numCell < 0 ){
      return false;
    }
    if( Kakuro.LAST_COORD().getIndice() < 0 ){
      return false;
    }
    return true;
  }

  /**
   * Test si cette objet est égale à un autre.
   *
   * @param o L'objet avec lequel ce comparer.
   * @return **true** si les deux coordonnées ciblent la même cellules.
   */
  @Override
  public boolean equals(Object obj){
    // Vérifie si l'objet est null
    if (obj == null) {
      return false;
    }
    // Vérifie si les deux objets sont le même objet
    if (this == obj) {
      return true;
    }
    // Vérifie si les objets appartiennent à la même classe
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    // Compare les valeurs des deux instances
    return (this.getIndice() == ((Coord) obj).getIndice());
  }

  /**
   * Compare les coordonnée actuel à d'autres coordonnées.
   * Si cette objet est inférieur à **coord**, renvoi une valeur négative.
   * Si cette objet est égale à **coord**, renvoi 0.
   * Si cette objet est supérieur à **coord**, renvoi une valeur positive.
   *
   * @param coord Les coordonnée avec lesquels ce comparer.
   * @return le nombre de case d'écart entre les deux coordonnée.
   */
  @Override
  public int compareTo(Coord coord){
    return this.getIndice() - coord.getIndice();
  }

  /**
   * Copie les coordonnées.
   *
   * @param coord Les coordonnée à écraser.
   * @return **coord**
   */
  public Coord copy(Coord coord) {
    coord.setIndice(this.getIndice());
    return coord;
  }

  /**
   * Copie les coordonnées.
   *
   * @return La copie des coordonnée.
   */
  public Coord copy() {
    return new Coord(this.getIndice());
  }
}
