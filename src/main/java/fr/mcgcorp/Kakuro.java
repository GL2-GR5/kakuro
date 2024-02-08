package fr.mcgcorp;

/*
 * import non utiliser pour le moment.
 */
//import java.io.Serializable;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;

// Autres
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 * La classe gérant tout le dérouler du jeu.
 * Cette classe gère le dérouler du jeu en fonction des messages envoyé par le
 * contrôleur de l'interface graphique.
 *
 * @author PECHON Erwan
 */
class Kakuro {
  /** Valeur Maximal authorisé. */
  public static final int MAX_VALUE = 9;
  /** Valeur Minimal authorisé. */
  public static final int MIN_VALUE = 1;
  /** Valeur null (aucune valeur). */
  public static final int NULL_VALUE = Kakuro.MIN_VALUE - 1;

  /** La pile des mouvement effectué par le joueur. */
  protected Stack<Move> lstMove;
  /** La pile des mouvement effectué par le joueur, puis annulé. */
  protected Stack<Move> lstMoveCancel;
  /** L'indice du dernier mouvement avant qu'il n'y est une erreur. */
  protected int lastCorrectState = -1;
  /** La grille du jeu de Kakuro. */
  protected Cell[][] grid = null;

  /**
   * Le constructeur du jeu de Kakuro.
   * 
   * Ce constructeur sert à préparer les variables pour tout type de jeu.
   * Pour initialiser le jeu pour des paramètres spécifiques, il faut
   * passer par la méthode @link Kakuro#initialize.
   */
  Kakuro() {
    this.lstMove = new Stack<Move>();
    this.lstMoveCancel = new Stack<Move>();
    this.lastCorrectState = -1;
    this.grid = null;
  }

  /**
   * Initialise une partie de Kakuro et la lance.
   * Cette méthode prends tout les paramètres nécéssaire pour lancer une partie de
   * Kakuro et prépare le jeu en conséquence.
   * 
   * @param nbLine   Le nombre de lignes que doit-avoir la grille de Kakuro.
   * @param nbColumn Le nombre de colonnes que doit-avoir la grille de Kakuro.
   */
  public void initialize(int nbLine, int nbColumn) {
    this.grid = new Cell[nbLine][nbColumn];
    for (int i = 0; i < nbLine; i++) {
      for (int j = 0; j < nbColumn; j++) {
        if (i < j) {
          this.grid[i][j] = new Cell();
        } else if (i == j) {
          if ((i % 3) == 0) {
            this.grid[i][j] = new ResultCell(-1, i);
          } else if ((i % 3) == 0) {
            this.grid[i][j] = new ResultCell(i, -1);
          } else {
            this.grid[i][j] = new ResultCell(i, i * 2);
          }
        } else {
          this.grid[i][j] = new WhiteCell(i);
        }
      }
    }
  }

  /**
   * Accesseur sur le type des cases du jeu.
   * Cette fonction permettra d'initialiser la grille du jeu du côté de
   * l'interface graphique.
   *
   * @return Une matrice comportement le nom de la classe de chaque cellule de la
   *         grille du jeu.
   */
  public String[][] getMatriceClasses() {
    String[][] matr = new String[this.grid.length][this.grid[0].length];
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid.length; j++) {
        matr[i][j] = this.grid[i][j].getClass().getName();
      }
    }
    return matr;
  }

  /**
   * Gestion de la sauvegarde de l'état du jeu.
   *
   * Cette fonction définit comment le jeu doit-être sauvegardé.
   * Elle devra être invoquer régulièrement (environ tous les 10 coups)
   * afin d'éviter la perte d'une partie du à une coupure de courant.
   * Afin de limiter les accès mémoire, la sauvegarde devra se faire dans
   * un fichier binaire et il faudra que seul les éléments ayant était
   * modifié depuis la dernière sauvegarde soit enregistrer (le reste
   * l'est déjà et ne doit pas être écraser.)
   */
  public void save() {
  }

  /**
   * Fonction à appelé pour quitter le jeu.
   * Assure que le jeu soit bien sauvegardé.
   * Ferme le fichier de sauvegarde.
   *
   */
  public void quit() {
    this.save();
  }

  /**
   * Qu'elle aide donnée au joueur.
   */
  public void help() {
  }

  /**
   * Permet d'obtenir une case.
   * 
   * @param coord Les coordonnée de la case à obtenire.
   * @return La case demandé.
   *
   *         Permet de vérifier que les coordonnée soit correct.
   */
  private Cell getCell(Coord coord) {
    Cell cell = null;
    try {
      cell = this.grid[coord.getLine()][coord.getColumn()];
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
    return cell;
  }

  /**
   * Permet d'obtenir une case.
   * Permet de vérifier que les coordonnée soit correct.
   * 
   * @param coord     Les coordonnée de la case à obtenire.
   * @param classCell La classe que la cellule doit avoir.
   * @param <T>       La classe de la cellule demandé.
   * @return La case demandé caster dans sa classe.
   */
  private <T> T getCell(Coord coord, Class<T> classCell) {
    Cell cell = this.getCell(coord);
    if (cell == null) {
      return null;
    }
    if (classCell.isInstance(cell)) {
      return classCell.cast(cell);
    }
    return null;
  }

  /**
   * Permet d'obtenir une ligne.
   * 
   * @param line La ligne à obtenire
   * @return La ligne demandé
   */
  private Cell[] getRow(int line) {
    Cell[] row = null;
    try {
      row = this.grid[line];
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
    return row;
  }

  /**
   * Permet d'obtenir une colonne.
   * 
   * @param column La colonne à obtenire
   * @return La colonne demandé
   */
  private Cell[] getCol(int column) {
    if ((column < 0) || (this.grid[0].length <= column)) {
      return null;
    }
    Cell[] col = new Cell[this.grid[0].length];
    for (int i = 0; i < this.grid.length; i++) {
      col[i] = this.grid[i][column];
    }
    return col;
  }

  /**
   * Permet d'obtenir la valeur d'une case blanche.
   *
   * @param coord Les coordonées de la case à interrogé
   * @return La valeur demandé
   */
  public int getValue(Coord coord) {
    WhiteCell cell = this.getCell(coord, WhiteCell.class);
    if (cell == null) {
      return -1;
    }
    return cell.getValue();
  }

  /**
   * Permet de modifier la valeur d'une case blanche.
   * 

   * @param coord Les coordonnées de la case à modifié
   * @param value La valeur à saisie
   * @return Les coordonnées de la case modifié
   */
  public Coord setValue(Coord coord, int value) {
    WhiteCell cell = this.getCell(coord, WhiteCell.class);
    if (cell == null) {
      return null;
    }
    cell.setValue(value);
    return coord;
  }

  /**
   * Permet d'obtenir le résultat attendu pour une ligne.
   *
   * @param coord Les coordonées de la case de consigne
   * @return La valeur demandé
   */
  public int getResultRow(Coord coord) {
    ResultCell cell = this.getCell(coord, ResultCell.class);
    if (cell == null) {
      return -1;
    }
    return cell.getRow();
  }

  /**
   * Permet d'obtenir le résultat attendu pour une colonne.
   *
   * @param coord Les coordonées de la case de consigne
   * @return La valeur demandé
   */
  public int getResultColumn(Coord coord) {
    ResultCell cell = this.getCell(coord, ResultCell.class);
    if (cell == null) {
      return -1;
    }
    return cell.getColumn();
  }

  /**
   * Permet de déplacer une action d'une pile à une autre.
   *
   * @param origin      La pile à dépiler
   * @param destination La pile à empiler
   * @param useOld      Qu'elle méthode du mouvement faut-il utilisé ?
   * @return Les coordonnées de la case modifié
   */
  private Coord swapStack(Stack origin, Stack destination, boolean useOld) {
    if (origin.isEmpty()) {
      return null;
    }
    Move move = ((Move) origin.pop());

    Coord coord = move.getCoord();
    WhiteCell cell = this.getCell(coord, WhiteCell.class);
    if (cell == null) {
      return null;
    }

    if (move instanceof MoveValue) {
      MoveValue moveValue = (MoveValue) move;
      int value = -1;
      if (useOld) {
        value = moveValue.getValueOld();
      } else {
        value = moveValue.getValueNew();
      }
      if (value == Kakuro.NULL_VALUE) {
        cell.clear();
      } else {
        cell.setValue(value);
      }
    } else if (move instanceof MoveNote) {
      MoveNote moveNote = (MoveNote) move;
      HashSet<Integer> notes = null;
      if (useOld) {
        notes = moveNote.getNotesOld();
      } else {
        notes = moveNote.getNotesNew();
      }
      if (notes == null) {
        cell.clearNotes();
      } else {
        cell.setNotes(notes);
      }
    } else {
      return null;
    }

    destination.push(move);
    return move.getCoord();
  }

  /**
   * Annuler le dernier mouvement.
   *
   * @return Les coordonnées de la case modifié
   */
  public Coord undo() {
    return swapStack(this.lstMove, this.lstMoveCancel, true);
  }

  /**
   * Refaire le dernier mouvement annuler.
   *
   * @return Les coordonnées de la case modifié
   */
  public Coord redo() {
    return swapStack(this.lstMoveCancel, this.lstMove, false);
  }

  /**
   * Retourner au dernier mouvement où le jeu n'avait pas d'erreurs.
   *
   * @return Les coordonnées des cases modifiés
   */
  public Coord[] backToLastCorrectState() {
    HashSet<Coord> setModification = new HashSet<>();
    while (this.lastCorrectState < this.lstMove.size()) {
      setModification.add(this.undo());
    }
    return setModification.toArray(new Coord[setModification.size()]);
  }

  /**
   * Concatener deux tableau de cellule d'erreur.
   *
   * @param array1 Le tableau à mettre à l'avant du nouveau tableau
   * @param array2 Le tableau à mettre à l'arrière du nouveau tableau
   * @return Les deux tableaux fusionnée.
   */
  private static CellError[] concatArrays(CellError[] array1, CellError[] array2) {
    if (array1 == null) {
      return array2;
    }
    if (array2 == null) {
      return array1;
    }
    CellError[] result = Arrays.copyOf(array1, array1.length + array2.length);
    System.arraycopy(array2, 0, result, array1.length, array2.length);
    return result;
  }

  /**
   * Fouille toute la grille à la recherche d'erreurs.
   *
   * @return La liste des Erreurs découvertes
   */
  public CellError[] check() {
    CellError[] lstError = null;
    Coord coord = null;
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        coord = Coord.createCoord_matriciel(i, j);
        lstError = this.concatArrays(lstError, this.check(coord));
      }
    }
    if (lstError == null) {
      return null;
    }
    if (lstError.length != 0) {
      if (this.lastCorrectState < 0) {
        this.lastCorrectState = this.lstMove.size();
      }
    }
    return lstError;
  }

  /**
   * Vérifie si une cellule est correct.
   *
   * @param coord Les coordonées de la case à questionnée.
   * @return La liste des Erreurs découvertes
   */
  public CellError[] check(Coord coord) {
    Cell cell = this.getCell(coord);
    if (cell == null) {
      return null;
    }
    Cell[] row = this.getRow(coord.getLine());
    Cell[] col = this.getCol(coord.getColumn());
    if (cell instanceof WhiteCell) {
      int value = ((WhiteCell) cell).getValue();
      if (value == Kakuro.NULL_VALUE) {
        return new CellError[0];
      }
      ArrayList<CellError> lstError = new ArrayList<>();
      Coord coordError = null;
      for (int i = 0; i < row.length; i++) {
        if (i != coord.getColumn()) {
          if ((row[i] instanceof WhiteCell) && (value == ((WhiteCell) row[i]).getValue())) {
            coordError = Coord.createCoord_matriciel(coord.getLine(), i);
            lstError.add(new CellError(coordError, TypeError.DOUBLE));
          }
        }
      }
      for (int i = 0; i < col.length; i++) {
        if (i != coord.getLine()) {
          if ((col[i] instanceof WhiteCell) && (value == ((WhiteCell) col[i]).getValue())) {
            coordError = Coord.createCoord_matriciel(i, coord.getColumn());
            lstError.add(new CellError(coordError, TypeError.DOUBLE));
          }
        }
      }
      if (!((WhiteCell) cell).isCorrect()) {
        lstError.add(new CellError(coord, TypeError.VALUE));
      }
      return lstError.toArray(new CellError[lstError.size()]);
    } else if (cell instanceof ResultCell) {
      int resultRow = ((ResultCell) cell).getRow();
      int resultCol = ((ResultCell) cell).getColumn();
      ArrayList<CellError> lstError = new ArrayList<>();
      Coord coordError = null;
      int value = Kakuro.NULL_VALUE;
      int res = 0;
      if (resultRow != Kakuro.NULL_VALUE) {
        for (int i = coord.getColumn() + 1; i < row.length; i++) {
          if (!(row[i] instanceof WhiteCell)) {
            break;
          }
          value = ((WhiteCell) row[i]).getValue();
          if (value != Kakuro.NULL_VALUE) {
            res += value;
            if (res > resultRow) {
              coordError = Coord.createCoord_matriciel(coord.getLine(), i);
              lstError.add(new CellError(coordError, TypeError.OVER_RESULT_ROW));
              break;
            }
          }
        }
      }
      if (resultCol != Kakuro.NULL_VALUE) {
        for (int i = coord.getLine() + 1; i < col.length; i++) {
          if (!(col[i] instanceof WhiteCell)) {
            break;
          }
          value = ((WhiteCell) col[i]).getValue();
          if (value != Kakuro.NULL_VALUE) {
            res += value;
            if (res > resultCol) {
              coordError = Coord.createCoord_matriciel(i, coord.getColumn());
              lstError.add(new CellError(coordError, TypeError.OVER_RESULT_LINE));
              break;
            }
          }
        }
      }
      return lstError.toArray(new CellError[lstError.size()]);
    } else {
      return new CellError[0];
    }
  }

  /**
   * Ré-initialise le tableau.
   *
   * @return La liste des cases modifié
   */
  public Coord[] clean() {
    ArrayList<Coord> lstModif = new ArrayList<>();
    Coord coord = null;
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        coord = Coord.createCoord_matriciel(i, j);
        if (this.clean(coord) != null) {
          lstModif.add(coord);
        }
      }
    }
    return lstModif.toArray(new Coord[lstModif.size()]);
  }

  /**
   * Ré-initialise une cellule.
   *
   * @param coord Les coordonées de la case à modifié.
   * @return La cases modifié
   */
  public Coord clean(Coord coord) {
    WhiteCell cell = this.getCell(coord, WhiteCell.class);
    if (cell == null) {
      return null;
    }
    cell.clearAll();
    return coord;
  }

  /**
   * Ré-initialise les notes de toutes les cellules.
   *
   * @return La liste des cases modifié
   */
  public Coord[] cleanNotes() {
    ArrayList<Coord> lstModif = new ArrayList<>();
    Coord coord = null;
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        coord = Coord.createCoord_matriciel(i, j);
        if (this.cleanNotes(coord) != null) {
          lstModif.add(coord);
        }
      }
    }
    return lstModif.toArray(new Coord[lstModif.size()]);
  }

  /**
   * Ré-initialise les notes d'une cellule.
   *
   * @param coord Les coordonées de la case à modifié.
   * @return La cases modifié
   */
  public Coord cleanNotes(Coord coord) {
    WhiteCell cell = this.getCell(coord, WhiteCell.class);
    if (cell == null) {
      return null;
    }
    cell.clearNotes();
    return coord;
  }

  /**
   * Obtient les notes inscrites sur une case.
   *
   * @param coord Les coordonées de la case à questionnée.
   * @return La liste des notes inscrites
   */
  public int[] getNotes(Coord coord) {
    WhiteCell cell = this.getCell(coord, WhiteCell.class);
    if (cell == null) {
      return null;
    }
    return cell.getNotes();
  }

  /**
   * Obtient les notes inscrites sur une case.
   *
   * @param coord Les coordonées de la case à modifié.
   * @param notes La liste des notes inscrites
   * @return Les coordonées de la cellule modifié.
   */
  public Coord setNotes(Coord coord, int[] notes) {
    WhiteCell cell = this.getCell(coord, WhiteCell.class);
    if (cell == null) {
      return null;
    }
    cell.setNotes(notes);
    return coord;
  }
}