package fr.mcgcorp;

import java.util.HashSet

/**
 * Représentation d'un mouvement modifiant les notes d'une case.
 * @see Move
 * @author PECHON Erwan
 */
class MoveNote extends Move {
	/** Notes modifié par le joueur. */
	protected HashSet<Integer> notes_old;
	/** Nouvelle notes saisie par le joueur. */
	protected HashSet<Integer> notes_new;

	/**
	 * Constructeur d'un mouvement modifiant les notes.
	 *
	 * @param notes_old Les notes de la cellule, avant ce mouvement.
	 * @param notes_new Les notes de la cellule, après ce mouvement.
	 */
	MoveValue(HashSet<Integer> notes_old, HashSet<Integer> notes_new){
		super();
		this.notes_old = notes_old;
		this.notes_new = notes_new;
	}

	/**
	 * Renvoie les anciennes notes de la case.
	 * @return Le vieux set de notes
	 */
	public HashSet<Integer> getNotes_old(){
		return this.notes_old;
	}
	/**
	 * Renvoie les nouvelles notes de la case.
	 * @return Le nouveaux set de notes
	 */
	public HashSet<Integer> getNotes_new(){
		return this.notes_new;
	}
}

