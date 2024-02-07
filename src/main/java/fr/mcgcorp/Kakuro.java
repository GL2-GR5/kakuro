package fr.mcgcorp;

import java.util.ArrayDeque;

/*
// Gestion de la sauvegarde
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
*/

// Autres
import java.util.ArrayList;

/** La classe gérant tout le dérouler du jeu.
 * @author PECHON Erwan
 *
 * Cette classe gére le dérouler du jeu en fonction des messages envoyé par le contrôlleur de l'interface graphique.
 *
 */
class Kakuro implements Serializable {
  /** La pile des mouvement effectué par le joueur. */
  protected Deque<Move> lstMove;
  /** La pile des mouvement effectué par le joueur, puis annulé. */
  protected Deque<Move> lstMove_cancel;
  /** L'indice du dernier mouvement avant qu'il n'y est une erreur. */
  protected int lastCorrectState = -1;
  /** La grille du jeu de Kakuro. */
  protected Cell[][] grid = null;

  /** Le constructeur du jeu de Kakuro.
   * @author PECHON Erwan
   *
   * Ce constructeur sert à préparer les variables pour tout type de jeu.
   * Pour initialiser le jeu pour des paramètres spécifiques, il faut passer par la méthode @link Kakuro#initialize.
   */
  Kakuro(){
    this.lstMove = new ArrayDeque<Move>();
    this.lstMove_cancel = new ArrayDeque<Move>();
    this.lastCorrectState = 1;
    this.grid = null;
  }

  /** Initialise une partie de Kakuro et la lance.
   * @author PECHON Erwan
   * @param nbLine Le nombre de lignes que doit-avoir la grille de Kakuro.
   * @param nbColumn Le nombre de colonnes que doit-avoir la grille de Kakuro.
   *
   * Cette méthode prends tout les paramètres nécéssaire pour lancer une partie de Kakuro et prépare le jeu en conséquence.
   */
  public void initialize(int nbLine,int nbColumn){
    this.grid = new Cell[nbLine][nbColumn];
    for( int i=0 ; i<nbLine ; i++ ){
      for( int j=0 ; j<nbColumn ; j++ ){
        if( i < j ){
          this.grid[i][j] = new Cell();
        } else if( i==j ){
          if( (i%3) == 0 ){
            this.grid[i][j] = new ResultCell(null,i);
          } else if( (i%3) == 0 ){
            this.grid[i][j] = new ResultCell(i,null);
          } else {
            this.grid[i][j] = new ResultCell(i,i*2);
          }
        } else {
          this.grid[i][j] = new ValueCell(i);
        }
      }
    }
  }
  /** Accesseur sur le type des cases du jeu
   * @author PECHON Erwan
   * @return Une matrice comportement le nom de la classe de chaque cellule de la grille du jeu.
   *
   * Cette fonction permettra d'initialiser la grille du jeu du côté de l'interface graphique.
   *
   */
  public String[][] getMatriceClasses(){
    String[][] matr = new String[this.grid.length][this.grid[0].length];
    for( int i=0 ; i<this.grid.length ; i++ ){
      for( int j=0 ; j<this.grid.length ; j++ ){
        matr[i][j] = this.grid[i][j].getClass().getName();
      }
    }
    return matr;
  }
  /** Gestion de la sauvegarde de l'état du jeu.
   * @author
   *
   * Cette fonction définit comment le jeu doit-être sauvegardé.
   * Elle devra être invoquer régulièrement (environ tous les 10 coups) afin d'éviter la perte d'une partie du à une coupure de courant.
   * Afin de limiter les accès mémoire, la sauvegarde devra se faire dans un fichier binaire et il faudra que seul les éléments ayant était modifié depuis la dernière sauvegarde soit enregistrer (le reste l'est déjà et ne doit pas être écraser.)
   *
   */
  public void save(){
  }
  /** Fonction à appelé pour quitter le jeu
   * @author
   *
   * - Assure que le jeu soit bien sauvegardé.
   * - Ferme le fichier de sauvegarde.
   *
   */
  public void quit(){
    this.save();
  }

  /** Qu'elle aide donnée au joueur ?
   * @author
   */
  public void help(){
  }

  /** Permet d'obtenir une case.
   * @author PECHON Erwan
   * @param coord Les coordonnée de la case à obtenire.
   * @return La case demandé.
   *
   * Permet de vérifier que les coordonnée soit correct.
   */
  private Cell getCell(Coord coord){
    Cell cell = null;
    try{
      cell = this.grid[coord.getL()][coord.getC()];
    } catch(ArrayIndexOutOfBoundsException e){
      return null;
    }
    return cell;
  }
  /** Permet d'obtenir une case.
   * @author PECHON Erwan
   * @param coord Les coordonnée de la case à obtenire.
   * @param classCell La classe que la cellule doit avoir.
   * @return La case demandé caster dans sa classe.
   *
   * Permet de vérifier que les coordonnée soit correct.
   */
  private <T> T getCell(Coord coord, Class<T> classCell){
    Cell cell = this.getCell(coord);
    if( cell == null ){
      return null;
    }
    if( cell.isInstance(classCell) ){
      return classCell.cast( cell );
    }
    return null;
  }
  /** Permet d'obtenir une ligne.
   * @author PECHON Erwan
   * @param line La ligne à obtenire
   * @return La ligne demandé
   */
  private Cell[] getRow(int line){
    Cell[] row = null;
    try{
      row = this.grid[ line ];
    } catch(ArrayIndexOutOfBoundsException e){
      return null;
    }
    return row;
  }
  /** Permet d'obtenir une colonne.
   * @author PECHON Erwan
   * @param column La colonne à obtenire
   * @return La colonne demandé
   */
  private Cell[] getCol(int column){
    if( (column < 0) || (this.grid[0].length <= column) ){
      return null;
    }
    Cell[] col = new Cell[ this.grid[0].length ];
    for( int i=0 ; i<this.grid.length ; i++ ){
      col[i] = this.grid[i][column];
    }
    return col;
  }


  /** Permet d'obtenir la valeur d'une case blanche.
   * @author PECHON Erwan
   * @param coord Les coordonées de la case à interrogé
   * @return La valeur demandé
   */
  public int getValue(Coord coord){
    ValueCell cell = this.getCell( coord , ValueCell.class );
    if( cell == null ){
      return null;
    }
    return cell.getValue();
  }
  /** Permet de modifier la valeur d'une case blanche.
   * @author PECHON Erwan
   * @param coord Les coordonées de la case à modifié
   * @param value La valeur à saisire
   */
  public Coord setValue(Coord coord,int value){
    ValueCell cell = this.getCell( coord , ValueCell.class );
    if( cell == null ){
      return null;
    }
    cell.setValue(value);
    return coord;
  }

  /** Permet d'obtenir le résultat attendu pour une ligne
   * @author PECHON Erwan
   * @param coord Les coordonées de la case de consigne
   * @return La valeur demandé
   */
  public int getResultRow(Coord coord){
    ResultCell cell = this.getCell( coord , ResultCell.class );
    if( cell == null ){
      return null;
    }
    return cell.getRow();
  }
  /** Permet d'obtenir le résultat attendu pour une colonne
   * @author PECHON Erwan
   * @param coord Les coordonées de la case de consigne
   * @return La valeur demandé
   */
  public int getResultColumn(Coord coord){
    ResultCell cell = this.getCell( coord , ResultCell.class );
    if( cell == null ){
      return null;
    }
    return cell.getColumn();
  }

  /** Permet de déplacer une action d'une pile à une autre.
   * @author PECHON Erwan
   * @param origin La pile à dépiler
   * @param destination La pile à empiler
   * @param useOld Qu'elle méthode du mouvement faut-il utilisé ?
   * @return Les coordonnées de la case modifié
   */
  private Coord swapDeque(Deque origin, Deque destination, boolean useOld){
    if( origin.isEmpty() ){
      return null;
    }
    Move move = origin.pop();

    Coord coord = move.getCoord();
    ValueCell cell = this.getCell( coord , ValueCell );
    if( cell == null ){
      return null;
    }

    if( move instanceof MoveValue ){
      int value = -1;
      if( useOld ){
        value = move.getValue_old();
      } else {
        value = move.getValue_new();
      }
      cell.restoreValue( value );
    } else if( move instanceof MoveNote ){
      HashSet<Integer> notes = null;
      if( useOld ){
        notes = move.getNotes_old();
      } else {
        notes = move.getNotes_new();
      }
      cell.restoreNotes( notes );
    } else {
      return null;
    }

    destination.push( move );
    return move.getCoord();
  }
  /** Annuler le dernier mouvement
   * @author PECHON Erwan
   * @return Les coordonnées de la case modifié
   */
  public Coord undo(){
    return swapDeque(this.lstMove,this.lstMove_cancel,true);
  }
  /** Refaire le dernier mouvement annuler
   * @author PECHON Erwan
   * @return Les coordonnées de la case modifié
   */
  public Coord redo(){
    return swapDeque(this.lstMove_cancel,this.lstMove,false);
  }
  /** Retourner au dernier mouvement où le jeu n'avait pas d'erreurs
   * @author PECHON Erwan
   * @return Les coordonnées des cases modifiés
   */
  public Coord[] backToLastCorrectState(){
    HashSet<Coord> setModification = new HashSet<>();
    while( this.lastCorrectState < this.lstMove.size() ){
      setModification.add( this.undo() );
    }
    return setModification.toArray();
  }

  /** Concatener deux tableau de cellule d'erreur
   * @author PECHON Erwan
   * @param array1 Le tableau à mettre à l'avant du nouveau tableau
   * @param array2 Le tableau à mettre à l'arrière du nouveau tableau
   * @return Les deux tableaux fusionnée.
   */
  private static CellError[] concatArrays(CellError[] array1, CellError[] array2) {
    if( array1 == null ){
      return array2;
    }
    if( array2 == null ){
      return array1;
    }
    CellError[] result = Arrays.copyOf(array1, array1.length + array2.length);
    System.arraycopy(array2, 0, result, array1.length, array2.length);
    return result;
  }
  /** Fouille toute la grille à la recherche d'erreurs
   * @author PECHON Erwan
   * @return La liste des Erreurs découvertes
   */
  public CellError[] check(){
    CellError[] lstError = null;
    Coord coord = null;
    for( int i=0 ; i<this.grid.length ; i++ ){
      for( int j=0 ; j<this.grid[i].length ; j++ ){
        coord = new createCoord_matriciel(i,j);
        lstError = this.concatArrays( lstError , this.check(coord) );
      }
    }
    if( CellError == null ){
      return null;
    }
    if( CellError.length != 0 ){
      if( this.lastCorrectState < 0 ){
        this.lastCorrectState = this.lstMove.size();
      }
    }
    return CellError;
  }
  /** Vérifie si une cellule est correct
   * @author PECHON Erwan
   * @param coord Les coordonées de la case à questionnée.
   * @return La liste des Erreurs découvertes
   */
  public CellError[] check(Coord coord){
    Cell cell = this.getCell(coord);
    if( cell == null ){
      return null;
    }
    Cell[] row = this.getRow();
    Cell[] col = this.getCol();
    if( cell instanceof ValueCell ){
      int value = ((ValueCell) cell).getValue();
      if( value == ValueCell.NULL_VALUE ){
        return new CellError[0];
      }
      ArrayList<CellError> lstError = new ArrayList<>();
      Coord coordError = null;
      for( int i=0 ; i<row.length ; i++ ){
        if( i != coord.getC() ){
          if( value == row[i].getValue() ){
            coordError = new createCoord_matriciel( coord.getL() , i );
            lstError.add( new CellError(coordError,TypeError.DOUBLE) );
          }
        }
      }
      for( int i=0 ; i<col.length ; i++ ){
        if( i != coord.getL() ){
          if( value == col[i].getValue() ){
            coordError = new createCoord_matriciel( i , coord.getC() );
            lstError.add( new CellError(coordError,TypeError.DOUBLE) );
          }
        }
      }
      if( ! ((ValueCell) cell).isCorrect() ){
        lstError.add( new CellError(coord,TypeError.VALUE) );
      }
      return lstError.toArray();
    } else if( cell instanceof ResultCell ){
      int resultRow = ((ResultCell) cell).getRow();
      int resultCol = ((ResultCell) cell).getCol();
      ArrayList<CellError> lstError = new ArrayList<>();
      Coord coordError = null;
      int value = -1;
      int res = 0;
      if( resultRow != null ){
        for( int i=coord.getC()+1 ; i<row.length ; i++ ){
          if( ! (row[i] instanceof ValueCell) ){
            break;
          }
          value = row[i].getValue();
          if( value != ValueCell.NULL_VALUE ){
            res+= value;
            if( res > resultRow ){
              coordError = new createCoord_matriciel( coord.getL() , i );
              lstError.add( new CellError(coordError,TypeError.OVER_RESULT_ROW) );
              break;
            }
          }
        }
      }
      if( resultCol != null ){
        for( int i=coord.getL()+1 ; i<col.length ; i++ ){
          if( ! (col[i] instanceof ValueCell) ){
            break;
          }
          value = col[i].getValue();
          if( value != ValueCell.NULL_VALUE ){
            res+= value;
            if( res > resultCol ){
              coordError = new createCoord_matriciel( i , coord.getC() );
              lstError.add( new CellError(coordError,TypeError.OVER_RESULT_LINE) );
              break;
            }
          }
        }
      }
      return lstError.toArray();
    } else {
      return new CellError[0];
    }
  }

  /** Ré-initialise le tableau
   * @author PECHON Erwan
   * @return La liste des cases modifié
   */
  public Coord[] clean(){
    ArrayList<Coord> lstModif = new ArrayList<>();
    Coord coord = null;
    for( int i=0 ; i<this.grid.length ; i++ ){
      for( int j=0 ; j<this.grid[i].length ; j++ ){
        coord = createCoord_matriciel(i,j);
        if( this.clean(coord) != null ){
          lstModif.add(coord);
        }
      }
    }
    return lstModif.toArray();
  }
  /** Ré-initialise une cellule
   * @author PECHON Erwan
   * @param coord Les coordonées de la case à modifié.
   * @return La cases modifié
   */
  public Coord clean(Coord coord){
    ValueCell cell = this.getCell( coord , ValueCell.class );
    if( cell == null ){
      return null;
    }
    cell.clean();
  }

  /** Ré-initialise les notes de toutes les cellules
   * @author PECHON Erwan
   * @return La liste des cases modifié
   */
  public Coord[] cleanNotes(){
    ArrayList<Coord> lstModif = new ArrayList<>();
    Coord coord = null;
    for( int i=0 ; i<this.grid.length ; i++ ){
      for( int j=0 ; j<this.grid[i].length ; j++ ){
        coord = createCoord_matriciel(i,j);
        if( this.cleanNotes(coord) != null ){
          lstModif.add(coord);
        }
      }
    }
    return lstModif.toArray();
  }
  /** Ré-initialise les notes d'une cellule
   * @author PECHON Erwan
   * @param coord Les coordonées de la case à modifié.
   * @return La cases modifié
   */
  public Coord cleanNotes(Coord coord){
    ValueCell cell = this.getCell( coord , ValueCell.class );
    if( cell == null ){
      return null;
    }
    cell.cleanNotes();
  }

  /** Obtient les notes inscrites sur une case
   * @author PECHON Erwan
   * @param coord Les coordonées de la case à questionnée.
   * @return La liste des notes inscrites
   */
  public int[] getNotes(Coord coord){
    ValueCell cell = this.getCell( coord , ValueCell.class );
    if( cell == null ){
      return null;
    }
    return cell.getNotes();
  }
  /** Obtient les notes inscrites sur une case
   * @author PECHON Erwan
   * @param coord Les coordonées de la case à modifié.
   * @param notes La liste des notes inscrites
   * @return Les coordonées de la cellule modifié.
   */
  public Coord setNotes(Coord coord,int[] notes){
    ValueCell cell = this.getCell( coord , ValueCell.class );
    if( cell == null ){
      return null;
    }
    cell.getNotes(notes);
    return coord;
  }
}
