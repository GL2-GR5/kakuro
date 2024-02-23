package fr.mcgcorp.game.grid;

//package interne
import fr.mcgcorp.game.Coord;
import fr.mcgcorp.game.Game;
//package externe
import java.lang.Iterable;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * La grille du jeu.
 * Stocke les cellules et permet de facilement naviguer dedans.
 *
 * @author PECHON Erwan
 */
public class Grid implements Iterable<Cell> {
  /**
   * L'itérateur sur la grille de jeu.
   *
   * @author PECHON Erwan
   */
  public class GridIterator implements Iterator<Cell> {
    /** La dernière cellule renvoyé. */
    Coord cell = null;
    /** La première cellule accessible. */
    Coord start = new Coord();
    /** La dernière cellule accessible. */
    Coord end = Game.getLastCoord();
    /** Comment doit-on ce déplacer ? (méthode de déplacement de Coord). */
    Function<Coord, Boolean> moveMethod = (c) -> c.nextCell();
    /** Est-ce que la vue est un block ou une liste ?. */
    boolean block = false;
    /** Combien y a-t-il eu de retour à la ligne/colonne ?. */
    int nbReturn = 0;

    /**
     * Le constructeur basique.
     */
    GridIterator() {
    }

    /**
     * Le constructeur de l'itérateur.
     * Si le mode block est activé (true), l'itération se fera à
     * l'intérieur du block de cellule allant de start à end.
     * Si le mode block est désactivé, l'itération se fera sur toutes les
     * cellules entre start et end (comme une liste).
     *
     * @param start La première cellule à renvoyé.
     * @param end La dernière case à renvoyé.
     * @param block Si == true, ne sort pas du block définit par start-end.
     * @param moveMethod La méthode de déplacement. @see Coord.
     */
    GridIterator(Coord start, Coord end, boolean block, Function<Coord, Boolean> moveMethod) {
      // Si la case de départ existe, la positionné.
      // Sinon, resté sur la première cellule.
      if ((start != null) && end.exist()) {
        this.start = start;
      }
      // Si la case de fin existe, la positionné.
      // Sinon, resté sur la dernière cellule.
      if ((end != null) && end.exist()) {
        this.end = end;
      }
      // Choisir le mode de parcours block ou liste.
      this.block = block;
      // Si il y à une méthode de déplacement.
      if (moveMethod != null) {
        // Tester si elle peut déplacer une coordonnée.
        Coord test = new Coord();
        Coord test2 = test.copy();
        moveMethod.apply(test2);
        // Modifié la méthode de déplacement par default,
        // si il y à eu un déplacement.
        if (!(test.equals(test2))) {
          this.moveMethod = moveMethod;
        }
      }
    }

    @Override
    public boolean hasNext() {
      // Tester si l'itération à commencer
      if (this.cell == null) {
        return true;
      }
      // Tester si la cellule est dans la grille
      if (! this.cell.exist()) {
        return false;
      }
      // Tester si la cellule n'à pas dépasser la fin
      return this.cell.compareTo(this.end) <= 0;
    }

    @Override
    public Cell next() throws NoSuchElementException {
      // Tester si il y à une cellule à envoyé.
      if (! this.hasNext()) {
        throw new NoSuchElementException();
      }
      // Ciblé la cellule à envoyé.
      if (this.cell == null) {
        // Ciblé la première cellule de la zone.
        this.cell = this.start.copy();
      } else {
        // Si l'étape précédent n'était pas un retour chariot
        if (this.nbReturn == 0) {
          // Ciblé la cellule suivante.
          if (this.moveMethod.apply(this.cell)) {
            this.nbReturn++;
          }
          if (this.block) {
            while (! this.inBlock()) {
              if (this.moveMethod.apply(this.cell)) {
                this.nbReturn++;
              }
            }
          }
        } else {
          this.nbReturn--;
          return null;
        }
      }
      // Envoyé la cellule.
      return Grid.this.getCell(this.cell);
    }

    /**
     * Test si l'itérateur est dans le block à parcourir.
     *
     * @return **true** si l'itérateur est dans le block.
     */
    private boolean inBlock() {
      // Tester si l'itération à commencer
      if (this.cell == null) {
        return true;
      }
      // Tester si la cellule est entre la ligne de début et celle de fin.
      int line = this.cell.getLine();
      if ((this.start.getLine() <= line) && (line <= this.end.getLine())) {
        // Tester si la cellule est entre la colonne de début et celle de fin.
        int col = this.cell.getColumn();
        if ((this.start.getColumn() <= col) && (col <= this.end.getColumn())) {
          return true;
        }
      }
      return false;
    }

    /**
     * Obtenir les coordonnées de la dernière cellule envoyé.
     *
     * @return Les coordonnées de la dernière cellule envoyé.
     */
    public Coord getCoord() {
      return this.cell;
    }
  }

  /** La grille du jeu. */
  protected Cell[][] grid;
  /** La liste de toutes les coordonnées de cellule de résultat. */
  protected List<Coord> lstCoordResCell;

  /**
   * Le constructeur de la grille du jeu.
   *
   * @param nbLine   Le nombre de lignes que doit-avoir la grille de Kakuro.
   * @param nbColumn Le nombre de colonnes que doit-avoir la grille de Kakuro.
   */
  public Grid(int nbLine, int nbColumn) {
    this.lstCoordResCell = new ArrayList<Coord>();
    this.grid = new Cell[nbLine][nbColumn];
    for (int i = 0; i < nbLine; i++) {
      for (int j = 0; j < nbColumn; j++) {
        if (i < j) {
          this.grid[i][j] = new BlackCell();
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
        this.lstCoordResCell.add(new Coord(i, j));
      }
    }
  }

  /**
   * Obtenir une cellule de la grille.
   *
   * @param coord Les coordonnées de la cellule demandé.
   * @return La cellule.
   */
  public Cell getCell(Coord coord) {
    if (! coord.exist()) {
      return null;
    }
    try {
      return this.grid[coord.getLine()][coord.getColumn()];
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  }

  /**
   * Obtenir une cellule de la grille.
   * La cellule est la première cellule matchant une condition.
   *
   * @param it L'itérateur définissant le déplacement.
   * @param predicate La condition pour accepter une cellule.
   * @return La cellule.
   */
  Cell getCell(GridIterator it, Predicate<Cell> predicate) {
    return this.getCell(this.getCoord(it, predicate));
  }

  /**
   * Obtenir les coordonnées d'une cellule de la grille.
   * La cellule est la première cellule matchant une condition.
   *
   * @param it L'itérateur définissant le déplacement.
   * @param predicate La condition pour accepter une cellule.
   * @return La cellule.
   */
  Coord getCoord(GridIterator it, Predicate<Cell> predicate) {
    while (it.hasNext()) {
      Cell cell = it.next();
      if (predicate.test(cell)) {
        return it.getCoord();
      }
    }
    return null;
  }

  @Override
  public GridIterator iterator() {
    return new GridIterator();
  }

  /**
   * Obtenir un itérateur sur la grille.
   * L'itérateur peut avoir des comportement différent selon les paramètres
   * qui lui sont donnée (vue sur laquelle itérer, butée, sens d'itération).
   *
   * @param start La première cellule à renvoyé.
   * @param end La dernière case à renvoyé.
   * @param block Si == true, ne sort pas du block définit par start-end.
   * @param moveMethod La méthode de déplacement. @see Coord.
   * @return Un itérateur sur la grille du jeu.
   */
  GridIterator iterator(Coord start, Coord end, boolean block, Function<Coord, Boolean> moveMethod) {
    return new GridIterator(start, end, block, moveMethod);
  }

  /**
   * Obtenir les zones de la cellule.
   * Les cellules noires ne font partie d'aucune zone.
   * Chaque autres cellules fait partie d'une zone vertical et d'une zone
   * horizontal.
   * Chaque zone commence par une cellule de résultat suivit d'un nombre
   * quelconque de cellule blanche.
   *
   * @param coord La cellule dont on veut récupérer les zones.
   * @return 0:La zone horizontal | 1:La zone vertical
   */
  public GridIterator[] getAreas(Coord coord) {
    // Créer l'objet à renvoyer
    GridIterator[] areas = new GridIterator[2];
    areas[0] = null;
    areas[1] = null;
    Coord start = null;
    Coord end = null;

    // Obtenir la cellule de résultat de la zone horizontal
    end = new Coord(coord.getLine(), 0);
    GridIterator it = this.iterator(coord.copy(), end, false, (c) -> (c.previousCell()));
    start = this.getCoord(it, (c) -> (c instanceof ResultCell));
    if (start.getLine() != coord.getLine()) {
      start = null;
    }
    end = null;
    it = null;

    // Obtenir la dernière cellule blanche de la zone horizontal
    end = new Coord(coord.getLine(), Game.getLastCoord().getColumn());
    it = this.iterator(coord.copy(), end, false, null);
    end = this.getCoord(it, (c) -> (!(c instanceof WhiteCell)));
    if (end == null) {
      end = new Coord(coord.getLine(), Game.getLastCoord().getColumn());
    } else {
      end.previousCell();
    }
    if (end.getLine() != coord.getLine()) {
      end = null;
    }
    it = null;

    // Préparer l'envoi de la zone horizontal
    if ((start != null) && (end != null)) {
      areas[0] = this.iterator(start, end, false, null);
    }
    start = null;
    end = null;


    // Obtenir la cellule de résultat de la zone vertical
    end = new Coord(0, coord.getColumn());
    it = this.iterator(coord.copy(), end, false, (c) -> (c.previousLine()));
    start = this.getCoord(it, (c) -> (c instanceof ResultCell));
    if (start.getLine() != coord.getLine()) {
      start = null;
    }
    end = null;
    it = null;

    // Obtenir la dernière cellule blanche de la zone vertical
    end = new Coord(Game.getLastCoord().getLine(), coord.getColumn());
    it = this.iterator(coord.copy(), end, false, (c) -> (c.previousLine()));
    end = this.getCoord(it, (c) -> (!(c instanceof WhiteCell)));
    if (end == null) {
      end = new Coord(coord.getLine(), Game.getLastCoord().getColumn());
    } else {
      end.previousCell();
    }
    if (end.getLine() != coord.getLine()) {
      end = null;
    }
    it = null;

    // Préparer l'envoi de la zone vertical
    if ((start != null) && (end != null)) {
      areas[1] = this.iterator(start, end, false, (c) -> (c.nextLine()));
    }
    start = null;
    end = null;


    // Renvoyer l'objet construit
    return areas;
  }

  /**
   * Obtenir toutes les zones horizontales.
   * Les cellules noires ne font partie d'aucune zone.
   * Chaque autres cellules fait partie d'une zone vertical et d'une zone
   * horizontal.
   * Chaque zone commence par une cellule de résultat suivit d'un nombre
   * quelconque de cellule blanche.
   *
   * @return La liste de toutes les zones horizontales.
   */
  public GridIterator[] getLineAreas() {
    GridIterator[] lstArea = new GridIterator[this.lstCoordResCell.size()];
    for (int i = 0; i < lstArea.length; i++) {
      Coord coord = this.lstCoordResCell.get(i);
      // Obtenir la dernière cellule blanche de la zone horizontal
      Coord end = new Coord(coord.getLine(), Game.getLastCoord().getColumn());
      GridIterator it = this.iterator(coord.copy(), end, false, null);
      end = this.getCoord(it, (c) -> (!(c instanceof WhiteCell)));
      if (end == null) {
        end = new Coord(coord.getLine(), Game.getLastCoord().getColumn());
      } else {
        end.previousCell();
      }
      if (end.getLine() != coord.getLine()) {
        end = null;
      }

      // Préparer l'envoi de la zone horizontal
      if ((coord != null) && (end != null)) {
        lstArea[i] = this.iterator(coord, end, false, null);
      }
      coord = null;
      end = null;
      it = null;
    }
    return lstArea;
  }

  /**
   * Obtenir toutes les zones verticales.
   * Les cellules noires ne font partie d'aucune zone.
   * Chaque autres cellules fait partie d'une zone vertical et d'une zone
   * horizontal.
   * Chaque zone commence par une cellule de résultat suivit d'un nombre
   * quelconque de cellule blanche.
   *
   * @return La liste de toutes les zones verticales.
   */
  public GridIterator[] getColumnAreas() {
    GridIterator[] lstArea = new GridIterator[this.lstCoordResCell.size()];
    for (int i = 0; i < lstArea.length; i++) {
      Coord coord = this.lstCoordResCell.get(i);
      // Obtenir la dernière cellule blanche de la zone horizontal
      Coord end = new Coord(Game.getLastCoord().getLine(), coord.getColumn());
      GridIterator it = this.iterator(coord.copy(), end, false, (c) -> (c.nextLine()));
      end = this.getCoord(it, (c) -> (!(c instanceof WhiteCell)));
      if (end == null) {
        end = new Coord(coord.getLine(), Game.getLastCoord().getColumn());
      } else {
        end.previousLine();
      }
      if (end.getLine() != coord.getLine()) {
        end = null;
      }

      // Préparer l'envoi de la zone horizontal
      if ((coord != null) && (end != null)) {
        lstArea[i] = this.iterator(coord, end, false, (c) -> (c.nextLine()));
      }
      coord = null;
      end = null;
      it = null;
    }
    return lstArea;
  }

  /**
   * Méthode préparant la grille du jeu à être transmise.
   *
   * @return La grille du jeu formater pour la transmission.
   */
  public String serialize() {
    StringBuilder s = new StringBuilder();
    for (Cell cell : this) {
      if (cell == null) {
        s.append('\n');
      } else {
        s.append(cell.serialize() + '|');
      }
    }
    return s.toString();
  }

  /**
   * Accesseur sur les coordonnées de la dernière case.
   *
   * @return Les coordonnées de la dernière case.
   */
  public Coord getLastCoord() {
    return new Coord((this.grid.length - 1), (this.grid[0].length - 1));
  }
}

