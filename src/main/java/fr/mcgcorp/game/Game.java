package fr.mcgcorp.game;

/*
 * import non utiliser pour le moment.
 */
//import java.io.Serializable;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;

//package interne
import fr.mcgcorp.game.error.EntryError;
import fr.mcgcorp.game.error.TypeEntryError;
import fr.mcgcorp.game.grid.Cell;
import fr.mcgcorp.game.grid.Grid;
import fr.mcgcorp.game.grid.ResultCell;
import fr.mcgcorp.game.grid.WhiteCell;
import fr.mcgcorp.game.move.Move;
//package externe
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * La classe gérant tout le dérouler du jeu.
 * <br>
 * Cette classe gère le dérouler du jeu en fonction des messages envoyé par le
 * contrôleur de l'interface graphique.
 * <br>
 * <img src="doc-files/Game.svg" alt="Diagramme de la classes Game" width="100%" />
 *
 * @author PECHON Erwan
 */
public final class Game {
  /** L'instance du Kakuro actuellement utilisé. */
  protected static Game kakuro = null;

  /** La plus grande valeur pouvant ce trouver dans une WhiteCell. */
  protected int cstMaxValue = 9;
  /** La plus petite valeur pouvant ce trouver dans une WhiteCell. */
  protected int cstMinValue = 1;
  /** La pile des mouvement effectué par le joueur. */
  protected Deque<Move> lstMove;
  /** La pile des mouvement effectué par le joueur, puis annulé. */
  protected Deque<Move> lstMoveCancel;
  /** La liste des erreurs connue. */
  protected List<EntryError> lstEntryErrors;
  /** L'indice du dernier mouvement avant qu'il n'y est une erreur. */
  protected int correctState = -1;
  /** La grille du jeu de Kakuro. */
  protected Grid grid = null;
  /** L'opérateur à appliquer afin d'obtenir le résultat d'une zone. */
  protected IntBinaryOperator function = (res, val) -> res + val;

  /**
   * Le constructeur du jeu de Kakuro.
   * 
   * Ce constructeur sert à préparer les variables pour tout type de jeu.
   * Pour initialiser le jeu pour des paramètres spécifiques, il faut
   * passer par la méthode @link Kakuro#initialize.
   */
  private Game() {
    this.lstMove = new ArrayDeque<Move>();
    this.lstMoveCancel = new ArrayDeque<Move>();
    this.correctState = -1;
    this.grid = null;
  }

  /**
   * Crée, initialise et lance une partie de Kakuro.
   * Cette méthode prends tout les paramètres nécessaire pour lancer une partie de
   * Kakuro et prépare le jeu en conséquence.
   * 
   * @param nbLine   Le nombre de lignes que doit-avoir la grille de Kakuro.
   * @param nbColumn Le nombre de colonnes que doit-avoir la grille de Kakuro.
   * @return **true** Si la nouvelle partie à put être créer.
   */
  public boolean createGame(int nbLine, int nbColumn) {
    if (Game.kakuro != null) {
      return false;
    }
    Game kakuro = new Game();
    this.grid = new Grid(nbLine, nbColumn);
    Game.kakuro = kakuro;
    return true;
  }

  /**
   * Charge et lance une partie sauvegardé de Kakuro.
   * Cette méthode prends tout les paramètres nécessaire pour recharger une partie de
   * Kakuro et prépare le jeu en conséquence.
   *
   * @param nomF le nom du fichier de sauvegarde.
   * @return **true** Si la nouvelle partie à put être créer.
   */
  public boolean chargeGame(String nomF) {
    if (Game.kakuro != null) {
      return false;
    }
    Game kakuro = new Game();
    this.grid = new Grid(5, 6);
    Game.kakuro = kakuro;
    return true;
  }

  /**
   * Obtient la valeur maximal d'une case blanche.
   *
   * @return la valeur maximal d'une case blanche.
   */
  public static int getMaxValue() {
    if (Game.kakuro == null) {
      return 0;
    }
    return Game.kakuro.cstMaxValue;
  }

  /**
   * Obtient la valeur minimal d'une case blanche.
   *
   * @return la valeur minimal d'une case blanche.
   */
  public static int getMinValue() {
    if (Game.kakuro == null) {
      return 0;
    }
    return Game.kakuro.cstMinValue;
  }

  /**
   * Obtient la valeur sauvegardé dans une case blanche, lorsque cette dernière
   * n'à pas de valeur.
   *
   * @return la valeur null d'une case blanche.
   */
  public static int getNullValue() {
    if (Game.kakuro == null) {
      return 0;
    }
    int minValue = Game.kakuro.cstMinValue;
    if (minValue > 0) {
      minValue = 0;
    }
    return minValue - 1;
  }

  /**
   * Obtient les coordonnées de la dernière case de la grille de kakuro.
   *
   * @return Les coordonnées de la dernière case de la grille de Kakuro.
   */
  public static Coord getLastCoord() {
    if (Game.kakuro == null) {
      return null;
    }
    return Game.kakuro.grid.getLastCoord();
  }

  /**
   * Obtient l'instance actuel du Kakuro.
   *
   * @return La partie de kakuro en cours.
   */
  public static Game getInstance() {
    return Game.kakuro;
  }




  /**
   * Obtient les erreurs demandé.
   *
   * @param predicate Comment choisir les erreurs.
   * @return La liste des erreurs de la zone demandé.
   */
  public List<EntryError> getEntryErrors(Predicate<EntryError> predicate) {
    return this.lstEntryErrors.stream().filter(predicate).collect(Collectors.toList());
  }

  /**
   * Mets à jour l'affichage de toutes les cellules.
   *
   * @param consumer La donnée de la cellule à mettre à jour (Contenu/Erreur)
   */
  public void update(Consumer<Coord> consumer) {
    Grid.GridIterator it = this.grid.iterator();
    while (it.hasNext()) {
      consumer.accept(it.getCoord());
    }
  }

  /**
   * Mets à jour l'affichage d'une seule cellule.
   *
   * @param coord Les coordonnées de la cellule à mettre à jour. #TODO
   */
  public void updateCell(Coord coord) {
    // String s = this.grid.getCell(coord).serialize();
    // Dire au controller d'afficher s en coord.
  }

  /**
   * Mets à jour l'affichage de l'erreur d'une seule cellule.
   *
   * @param coord Les coordonnées de la cellule à mettre à jour.
   */
  public void updateErr(Coord coord){
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
    // Sauvegarder uniquement les actions réaliser depuis la dernière
    // sauvegarde.
  }

  /**
   * Fonction à appelé pour quitter le jeu.
   * Assure que le jeu soit bien sauvegardé.
   * Ferme le fichier de sauvegarde.
   *
   */
  public void quit() {
    this.save(); // Sauvegardé le jeu avant de perdre le jeu.
    Game.kakuro = null; // Perdre le kakuro afin de pouvoir en créer un nouveau.
  }

  /**
   * Qu'elle aide donnée au joueur.
   */
  public void help() {
  }

  /**
   * Permet de saisir une valeur dans une case.
   *
   * @param coord Les coordonnées de la case à modifier.
   * @param value La valeur à saisir.
   */
  public void set(Coord coord, int value) {
    Cell cell = this.grid.getCell(coord);
    if ((cell != null) && (cell instanceof WhiteCell)) {
      Move move = ((WhiteCell) cell).setValue(value);
      if (move != null) {
        move.setCoord(coord);
        lstMove.add(move);
      }
    }
  }

  /**
   * Permet de saisir une valeur dans une case.
   *
   * @param coord Les coordonnées de la case à modifier.
   * @param notes Les notes à saisir.
   */
  public void set(Coord coord, Set<Integer> notes) {
    Cell cell = this.grid.getCell(coord);
    if ((cell != null) && (cell instanceof WhiteCell)) {
      Move move = ((WhiteCell) cell).setNotes(notes);
      if (move != null) {
        move.setCoord(coord);
        lstMove.add(move);
      }
    }
  }

  /**
   * Permet d'obtenir le contenu d'une case.
   *
   * @param coord Les coordonnées de la case à interrogé
   * @return La case formater pour transmission.
   */
  public String getCell(Coord coord) {
    Cell cell = this.grid.getCell(coord);
    if (cell == null) {
      return null;
    }
    return cell.serialize();
  }

  /**
   * Permet d'obtenir le contenu d'une case.
   *
   * @param coord Les coordonnées de la case à interrogé
   * @return La case formater pour affichage.
   */
  public String affCell(Coord coord) {
    Cell cell = this.grid.getCell(coord);
    if (cell == null) {
      return null;
    }
    return cell.toString();
  }

  /**
   * Permet d'obtenir les notes d'une cellule.
   *
   * @param coord Les coordonnées de la case à interrogé
   * @return Une copie du set de note de la cellule.
   */
  public Set<Integer> getNotes(Coord coord) {
    Cell cell = this.grid.getCell(coord);
    if ((cell != null) && (cell instanceof WhiteCell)) {
      Set<Integer> notes = ((WhiteCell) cell).getNotes();
      if (notes != null) {
        return notes;
      }
    }
    return null;
  }

  /**
   * Permet de déplacer une action d'une pile à une autre.
   *
   * @param origin La pile à dépiler
   * @param destination La pile à empiler
   * @param useOld Qu'elle méthode du mouvement faut-il utilisé ?
   * @return Les coordonnées de la case modifié
   */
  private Coord swapDeque(Deque<Move> origin, Deque<Move> destination, boolean useOld) {
    if (origin.isEmpty()) {
      return null;
    }
    Move move = origin.pop();

    Coord coord = move.getCoord();
    Cell cell = this.grid.getCell(coord);
    if (cell == null) {
      return null;
    }
    if (!(cell instanceof WhiteCell)) {
      return null;
    }

    ((WhiteCell) cell).traiteMove(move, useOld);

    destination.push(move);
    return move.getCoord();
  }

  /**
   * Annuler le dernier mouvement.
   *
   * @return Les coordonnées de la case modifié
   */
  public Coord undo() {
    return swapDeque(this.lstMove, this.lstMoveCancel, true);
  }

  /**
   * Refaire le dernier mouvement annuler.
   *
   * @return Les coordonnées de la case modifié
   */
  public Coord redo() {
    return swapDeque(this.lstMoveCancel, this.lstMove, false);
  }

  /**
   * Retourner au dernier mouvement où le jeu n'avait pas d'erreurs.
   *
   * @return Les coordonnées des cases modifiés
   */
  public Coord[] backToLastCorrectState() {
    List<Coord> setModification = new ArrayList<Coord>();
    while (this.correctState < this.lstMove.size()) {
      setModification.add(this.undo());
    }
    return setModification.toArray(new Coord[0]);
  }

  /**
   * Fouille toute la grille à la recherche d'erreurs.
   */
  public void check() {
    lstEntryErrors.clear();
    // Checker les zones horizontal.
    Grid.GridIterator[] lstArea = this.grid.getLineAreas();
    for (Grid.GridIterator area : lstArea) {
      this.check(area, true); // Checker toute la zone.
    }
    // Checker les zones verticale.
    lstArea = this.grid.getColumnAreas();
    for (Grid.GridIterator area : lstArea) {
      this.check(area, false); // Checker toute la zone.
    }
    // Vérifier si il n'y à aucune erreur.
    this.checkCorrectState();
  }

  /**
   * Vérifie si les deux zones d'une cellule sont correct.
   *
   * @param coord Les coordonnées de la case à questionnée.
   */
  public void check(Coord coord) {
    Grid.GridIterator[] areas = this.grid.getAreas(coord);
    // Checker les zones horizontal.
    this.check(areas[0], true); // Checker toute la zone.
    // Checker les zones vertical.
    this.check(areas[1], false); // Checker toute la zone.
    // Vérifier si il n'y à aucune erreur.
    this.checkCorrectState();
  }

  /**
   * Vérifier si une zone de cellule est correct.
   *
   * @param it L'itérateur sur la zone de cellule blanche.
   * @param checkLine Checker la ligne ? Ou la colonne ?
   * @return Est-ce que la fonction c'est terminé ?
   */
  private boolean check(Grid.GridIterator it, boolean checkLine) {
    if (!(it.hasNext())) {
      return false;
    }
    // Obtenir les constante.
    int minVal = Game.getMinValue();
    int nbVal = Game.getMaxValue() - minVal;
    // Préparer les accumulateurs.
    Coord[] lstDoublons = new Coord[nbVal];
    int res = 0;
    // Obtenir la limite de zone.
    int lim = 0;
    Cell cell = it.next(); // Obtenir la cellule de résultat.
    TypeEntryError typeArea = TypeEntryError.NO_ERR;
    if (cell instanceof ResultCell) {
      if (checkLine) {
        lim = ((ResultCell) cell).getLine();
        typeArea = TypeEntryError.OVER_RESULT_LINE;
      } else {
        lim = ((ResultCell) cell).getColumn();
        typeArea = TypeEntryError.OVER_RESULT_COLUMN;
      }
    } else {
      return false;
    }
    Coord coordResult = it.getCoord();
    Coord coord;
    // Traversé toute la zone à checker.
    while (it.hasNext()) {
      // Obtenir la nouvelle cellule à checker.
      cell = it.next();
      coord = it.getCoord();
      if (!(cell instanceof WhiteCell)) {
        if (res > lim) {
          this.addError(new EntryError(coordResult, typeArea));
        }
        return false;
      }
      // Transformé la cellule en cellule blanche
      WhiteCell whiteCell = ((WhiteCell) cell);
      // Obtenir la valeur de la cellule.
      int valCell = whiteCell.getValue();
      // Vérifier.
      if (! whiteCell.badValue()) {
        this.addError(new EntryError(coord, TypeEntryError.VALUE));
      }
      if (lstDoublons[valCell - minVal] == null) {
        lstDoublons[valCell - minVal] = coord;
      } else {
        this.addError(new EntryError(lstDoublons[valCell - minVal], TypeEntryError.DOUBLE));
        this.addError(new EntryError(coord, TypeEntryError.DOUBLE));
      }
      res = this.function.applyAsInt(res, valCell);
    }
    if (res > lim) {
      this.addError(new EntryError(coordResult, typeArea));
    }
    return true;
  }

  /**
   * Vérifie si la grille est dans un état sans erreur de saisie.
   */
  public void checkCorrectState() {
    // Si l'on est actuellement dans un état correct
    if (this.correctState < 0) {
      // Et si il y à des erreurs
      if (this.lstEntryErrors.size() != 0) {
        // On sauvegarde l'indice de l'état avant l'apparition des
        // erreurs.
        this.correctState = lstEntryErrors.size() - 2;
      }
    } else {
      // Si l'on est dans un état incorrect
      // Et si il n'y à pas d'erreur
      if (this.lstEntryErrors.size() == 0) {
        // On retourne dans un état correct
        this.correctState = -1;
      }
    }
  }

  /**
   * Ajouter une erreur dans la liste des erreurs, si elle n'y existe pas.
   * Cherche dans la liste si il y à déjà cette erreur.
   * Si elle n'y est pas encore, il faut l'y ajouter.
   *
   * @param e L'erreur à ajouter.
   */
  private void addError(EntryError e) {
    if (!(this.lstEntryErrors.contains(e))) {
      this.lstEntryErrors.add(e);
    }
  }


  /**
   * Formate le jeu pour transmission.
   *
   * @return la grille du jeu formater pour être transmise.
   */
  public String serializeGrid() {
    return this.grid.serialize();
  }
}
