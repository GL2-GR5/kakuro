package fr.mcgcorp.game.grid;

/**
 * Définit les message qu'une cellule peut comprendre.
 *
 * @author HOUGET Julien, PUREN Mewen, PECHON Erwan
 */
public interface Cell {
  /**
   * Méthode qui retourne la cellule, formater pour l'affichage.
   *
   * @return la cellule formater.
   */
  String serialize();
}
