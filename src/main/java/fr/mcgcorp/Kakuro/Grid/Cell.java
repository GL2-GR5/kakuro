package fr.mcgcorp.Kakuro.Grid;

/**
 * Définit les message qu'une cellule peut comprendre.
 *
 * @author HOUGET Julien, PUREN Mewen, PECHON Erwan
 */
interface Cell {
  /**
   * Méthode qui retourne la cellule, formater pour l'affichage.
   *
   * @return la cellule formater.
   */
  String serialize();
}
