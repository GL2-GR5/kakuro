package fr.mcgcorp.game.grid.cell;

/**
 * Définit les messages qu'une cellule peut comprendre.
 *
 * @author HOUGET Julien, PUREN Mewen, PECHON Erwan
 */
public interface Cell {
  /**
   * Méthode qui retourne la cellule, formatée pour l'affichage.
   *
   * @return la cellule formater.
   */
  String serialize();
}
