package fr.mcgcorp;

/** Détail l'erreur de saisie sur une cellule.
 * @author PECHON Erwan
 */
class CellError {
	/** Coordonée de la case où l'erreur à eu lieu. */
	protected Coord coord;
	/** L'erreur détecté sur cette cellule. */
	protected TypeError error;

	/** Constructeur de rapport d'erreur
	 * @author PECHON Erwan
	 * @param coord Les coordonnées de la cellule où une erreur à était déteté.
	 * @param error Le type d'erreur détecté.
	 */
	CellError(Coord coord, TypeError error){
		this.coord = coord;
		this.error = error;
	}

	/** Accesseur sur les coordonnée de la case où l'erreur à eu lieu.
	 * @author PECHON Erwan
	 *
	 * Permet au jeu de connaître la case associé à cette erreur.
	 *
	 */
	public Coord getCoord(){
		return this.cell;
	}
	/** Accesseur sur le type d'erreur détécté.
	 * @author PECHON Erwan
	 *
	 * Permet au jeu de connaître le type d'erreur ayant eu lieu.
	 *
	 */
	public TypeError getTypeError(){
		return this.error;
	}
}
