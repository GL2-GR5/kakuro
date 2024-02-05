package fr.mcgcorp;

/** Contient le résultat demandé pour une ligne et/ou une colonne.
  @inheritDoc
  */
class ResultCell extends Cell {
  //
  // Attributs d'instance
  //

  /** Résultat attendu pour une ligne */
  private int row;
  /** Résultat attendu pour une colonne */
  private int column;

  //
  // Constructeur
  //

  /** Constructeur d'une cellule de résultat
    @param row Le resultat attendu pour la ligne.
    @param column Le resultat attendu pour la colonne.
    @since V0.1
    @author Julien
    */
  ResultCell(int row, int column){
    super();
    this.row = row;
    this.column = column;
  }

  //
  // Accesseur
  //

  /** Renvoit la somme attendu pour la ligne à droite de cette cellule.
    @return Le résultat attendu pour la ligne.
    @since V0.1
    @author Julien
    */
  int getRow(){
    return this.row;
  }

  /** Renvoit la somme attendu pour la colonne en dessous de cette cellule.
    @return Le résultat attendu pour la ligne.
    @since V0.1
    @author Julien
    */
  int getColumn(){
    return this.column;
  }

  //
  // Affichage
  //

  /** Affiche une cellule de résultat
    @return La cellule formater pour affichage
    @since V0.1
    @author Erwan
    */
  @Override
  String toString(){
    String aff = "\";
    // Ajout du résultat attendu pour la colonne
    if( this.getColumn() ){
      aff = this.getColumn() + aff;
    } else {
      aff = "X" + aff;
    }
    // Ajout du résultat attendu pour la ligne
    if( this.getRow() ){
      aff+= this.getRow();
    } else {
      aff+= "X";
    }
    // Envoit de l'affichage
    return aff;
  }
}

