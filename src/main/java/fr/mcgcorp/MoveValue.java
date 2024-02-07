package fr.mcgcorp;

/**
 * Représentation d'un mouvement modifiant la valeur d'une cellule blance.
 * @see Move
 * @author PECHON Erwan
 */
class MoveValue extends Move {
	/** Valeur modifié par le joueur. */
	protected int value_old;
	/** Nouvelle valeur saisie par le joueur. */
	protected int value_new;

	/**
	 * Constructeur d'un mouvement modifiant une valeur.
	 *
	 * @param value_old La valeur de la cellule, avant ce mouvement.
	 * @param value_new La valeur de la cellule, après ce mouvement.
	 */
	MoveValue(int value_old, int value_new){
		super();
		this.value_old = value_old;
		this.value_new = value_new;
	}

	/**
	 * Renvoie l'ancienne valeur de la case.
	 * @return int
	 */
	public int getValue_old(){
		return this.value_old;
	}
	/**
	 * Renvoie la nouvelle valeur de la case.
	 * @return int
	 */
	public int getValue_new(){
		return this.value_new;
	}
}

