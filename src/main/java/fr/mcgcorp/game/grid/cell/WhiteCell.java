package fr.mcgcorp.game.grid.cell;

//package interne
import fr.mcgcorp.game.Game;
import fr.mcgcorp.game.move.Move;
import fr.mcgcorp.game.move.MoveNotes;
import fr.mcgcorp.game.move.MoveValue;
//package externe
import java.util.HashSet;
import java.util.Set;

/**
 * Cellule blanche, que le joueur doit modifier pour résoudre le puzzle.
 *
 * @see Cell
 */
public class WhiteCell implements Cell {
  /** Valeur de résultat de la case. */
  private int correctValue;
  /** Valeur entrée par le joueur. */
  private int value;
  /** Liste des notes de la case. */
  private Set<Integer> notes;

  /**
   * Constructeur d'une cellule blanche.
   *
   * @param correctValue La valeur correcte de la cellule.
   */
  public WhiteCell(int correctValue) {
    this.correctValue = correctValue;
    this.notes = new HashSet<Integer>();
    this.value = Game.getNullValue();
  }

  /**
   * Renvoie la valeur correcte de la case.
   *
   * @return La valeur que le joueur doit saisir pour avoir bon.
   */
  int getCorrectValue() {
    return this.correctValue;
  }

  /**
   * Renvoie la valeur entrée par le joueur.
   *
   * @return La valeur saisie par le joueur.
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Vérifie si la cellule est bien remplie.
   *
   * @return résultat
   */
  boolean isCorrect() {
    return (this.value == this.correctValue);
  }

  /**
   * Vérifie si une mauvaise valeur à était saisit par l'utilisateur.
   *
   * @return résultat
   */
  public boolean badValue() {
    if (this.value == Game.getNullValue()) {
      return false;
    }
    return ! this.isCorrect();
  }

  /**
   * Saisit la valeur entrée par le joueur.
   *
   * @param value La valeur donnée par le joueur.
   * @return Le movement qui a été réalisé sur la cellule.
   */
  public Move setValue(int value) {
    if (((Game.getMinValue() <= value) && (value <= Game.getMaxValue())) || (value == Game.getNullValue())) {
      if (this.value != value) {
        Move move = new MoveValue(this.value, value);
        this.value = value;
        return move;
      }
    }
    return null;
  }

  /**
   * Redéfinit le set de notes du joueur.
   *
   * @param notes Le nouveau set de notes.
   * @return Le movement qui a été réalisé sur la cellule.
   */
  public Move setNotes(Set<Integer> notes) {
    // Vérifier si il va y avoir une modification.
    if (notes == null) {
      if (this.notes.isEmpty()) {
        return null;
      }
    } else {
      if (this.notes.equals(notes)) {
        return null;
      }
    }
    // Sauvegarder le mouvement.
    // Utilisé this.getNotes afin de copier l'ancien set.
    Set<Integer> safeNotes = new HashSet<Integer>(notes);
    Move move = new MoveNotes(this.getNotes(), safeNotes);
    this.notes.clear();
    if (notes != null) {
      boolean modified = false;
      for (Integer note : notes) {
        if ((Game.getMinValue() <= note) || (note <= Game.getMaxValue())) {
          this.notes.add(note);
          modified = true;
        } else {
          safeNotes.remove(note);
        }
      }
      if (! modified) {
        return null;
      }
    }
    return move;
  }

  /**
   * Envoi le set de notes du joueur.
   *
   * @return La liste des notes sélectionnées.
   */
  public Set<Integer> getNotes() {
    return (new HashSet<Integer>(this.notes));
  }

  /**
   * Traite un mouvement.
   * Soit refait l'ancien mouvement.
   * Soit fait le nouveau.
   *
   * @param move Le mouvement à traiter.
   * @param useOld Doit-on utiliser l'ancienne valeur ou la nouvelle ?
   * @return Est-ce que la cellule à était modifié ?
   */
  public boolean traiteMove(Move move, boolean useOld) {
    if (move instanceof MoveValue) {
      int value = 0;
      if (useOld) {
        value = (int) move.getOld();
      } else {
        value = (int) move.getNew();
      }
      return (this.setValue(value) != null);
    }
    if (move instanceof MoveNotes) {
      Set<Integer> notes = null;
      if (useOld) {
        notes = (Set<Integer>) move.getOld();
      } else {
        notes = (Set<Integer>) move.getNew();
      }
      return (this.setNotes(notes) != null);
    }
    return false;
  }

  /**
   * Prépare la cellule pour transmission.
   *
   * @return La cellule formater pour transmission.
   */
  @Override
  public String serialize() {
    if (this.value != Game.getNullValue()) {
      return "" + value;
    }
    if ((this.notes == null) || this.notes.isEmpty()) {
      return "n";
    }
    String aff = "";
    boolean first = true;
    for (Integer note : notes) {
      if (first) {
        first = false;
      } else {
        aff += ",";
      }
      aff += note.toString();
    }
    return aff;
  }

  /**
   * Prépare la cellule pour transmission.
   *
   * @return La cellule formater pour transmission.
   */
  @Override
  public String toString() {
    if (this.value != Game.getNullValue()) {
      return "" + value;
    }
    if ((this.notes == null) || this.notes.isEmpty()) {
      return "";
    }
    return "?";
  }
}
