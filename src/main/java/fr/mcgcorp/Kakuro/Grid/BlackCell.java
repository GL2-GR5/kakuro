package fr.mcgcorp.Grid;

/**
 * Cellule de base, qui peut être soit une case vide(noir), soit une case de résultat, soit une case a remplir.
 *
 * @author HOUGET Julien, PUREN Mewen, PECHON Erwan
 */
class BlackCell implements Cell {
  /**
   * Méthode qui retourne la cellule, formater pour la transmission.
   *
   * @return la cellule formater.
   */
  @Override
  public String serialize(){
    return "|";
  }

  @Override
  public String toString() {
    return "X";
  }
}
