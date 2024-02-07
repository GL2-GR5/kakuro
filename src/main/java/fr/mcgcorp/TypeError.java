package fr.mcgcorp;

/** Énumération représentant les types d'erreurs qu'un joueur peut commettre dans le jeu de Kakuro.
 * @author PECHON Erwan
 */
public enum TypeError {
	/**
	 * Erreur lors de la saisie d'un nombre déjà présent sur la ligne ou la colonne.
	 */
	DOUBLE,

	/**
	 * Erreur lorsqu'une somme dans une ligne dépasse le résultat attendu.
	 */
	OVER_RESULT_ROW,

	/**
	 * Erreur lorsqu'une somme dans une colonne dépasse le résultat attendu.
	 */
	OVER_RESULT_LINE,

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
			case DOUBLE:
				return "Double";
			case OVER_RESULT_ROW:
				return "Over Result Row";
			case OVER_RESULT_LINE:
				return "Over Result Line";
			case VALUE:
				return "Value";
			default:
				return "Unknown";
		}
	}
}

