package fr.mcgcorp.Kakuro.EntryError;

/** Énumération représentant les types d'erreurs qu'un joueur peut commettre dans le jeu de Kakuro.
 * @author PECHON Erwan
 */
public enum TypeEntryError {
  /**
   * Il n'y à pas d'erreur sur cette cellule.
   * (si il y en avais une, elle peut-être désactivé)
   */
  NO_ERR,

  /**
   * Erreur lors de la saisie d'un nombre déjà présent sur la ligne ou la colonne.
   */
  DOUBLE,

  /**
   * Erreur lorsqu'une somme dans une ligne dépasse le résultat attendu.
   */
  OVER_RESULT_LINE,

  /**
   * Erreur lorsqu'une somme dans une colonne dépasse le résultat attendu.
   */
  OVER_RESULT_COLUMN,

  /**
   * La valeur saisie n'est pas la valeur voulut.
   */
  VALUE;

  /**
   * Retourne une chaîne de caractères représentant le type d'erreur.
   * @author PECHON Erwan
   *
   * @return Une chaîne de caractères représentant le type d'erreur.
   */
  public String toString() {
    switch (this) {
      case NO_ERR:
        return "No Error";
      case DOUBLE:
        return "Double";
      case OVER_RESULT_LINE:
        return "Over Result Line";
      case OVER_RESULT_COLUMN:
        return "Over Result Column";
      case VALUE:
        return "Value";
      default:
        return "Unknown";
    }
  }
}

