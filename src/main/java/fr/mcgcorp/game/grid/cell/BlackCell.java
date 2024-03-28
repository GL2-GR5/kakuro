package fr.mcgcorp.game.grid.cell;

/**
 * Cellule de base, qui peut être soit une case vide(noir), soit une case de résultat, soit une case à remplir.
 *
 * @author HOUGET Julien, PUREN Mewen, PECHON Erwan
 */
public class BlackCell implements Cell {
  /**
   * Méthode qui retourne la cellule, formatée pour la transmission.
   *
   * @return la cellule formater.
   */
  @Override
  public String serialize() {
    return "";
  }

  @Override
  public String toString() {
    return "X";
  }
}
