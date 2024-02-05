package fr.mcgcorp;

/** Cellule blanche, que le joueur doit modifier pour résoudre le puzzle.
	@inheritDoc
	*/
class WhiteCell extends Cell {
	//
	// Attributs de classe
	//

	/** Valeur maximal qu'une cellule peut atteindre. */
	static private int VALUE_MAX = 9;
	/** Valeur minimal qu'une cellule peut atteindre. */
	static private int VALUE_MIN = 1;

	//
	// Attributs d'instance
	//

	/** Valeur attendu pour que cette case soit correct. */
	private int correctValue;
	/** Valeur saisie par l'utilisateur. */
	private int value;
	/** Notes de l'utilisateur. */
	private boolean[] notes;

	//
	// Constructeur
	//

	/** Constructeur d'une cellule blance
		@param correctValue La valeur attendu pour cette cellule.
		@since V0.1
		@author Julien
		*/
	WhiteCell(int correctValue){
		super();
		this.correctValue = correctValue;
		this.notes = new boolean[WhiteCell.VALUE_MAX-WhiteCell.VALUE_MIN+1];
		this.clean();
	}

	//
	// Accesseur sur la valeur correct
	//

	/** Renvoit la valeur attendu pour la cellule.
		@return La valeur attendu.
		@since V0.1
		@author Julien
		*/
	int getCorrectValue(){
		return this.correctValue;
	}
	/** Test si le joueur à insérer la bonne valeur.
		@return `true` si la valeur est la bonne valeur, `false` autrement.
		@since V0.1
		@author Erwan
		*/
	boolean isCorrect(){
		return this.correctValue == this.value;
	}

	//
	// Gestion de la valeur saisie
	//

	/** Renvoit la valeur saisie par le joueur.
		@return La valeur saisie.
		@since V0.1
		@author Julien
		*/
	int getValue(){
		return this.value;
	}
	/** Saisie la valeur de la cellule.
		@param value La valeur à saisire.
		@throws IllegalArgumentException Si le paramètre donnée est incorrect, renvoit une erreur.
		@since V0.1
		@author Julien , Erwan
		*/
	void setValue(int value) throws IllegalArgumentException {
		if( value > WhiteCell.VALUE_MAX ){
			throw new IllegalArgumentException("La valeur ne peut pas être supérieur à "+WhiteCell.VALUE_MAX+" : " + valeur);
		}
		if( value < WhiteCell.VALUE_MIN ){
			throw new IllegalArgumentException("La valeur ne peut pas être inférieur à "+WhiteCell.VALUE_MIN+" : " + valeur);
		}
		this.value = value;
	}
	void clean_value(){
		this.value = WhiteCell.VALUE_MIN - 1;
	}

	//
	// Gestion des notes
	//

	/** Compte le nombre de notes de la cellule
		@return Le nombre de notes de la cellule.
		@since V0.1
		@author Erwan
		*/
	private int count_notes(){
		int count = 0;
		for( int i=0 ; i<this.notes.length ; i++ ){
			if( this.notes[i] == true ){
				count++;
			}
		}
		return count;
	}
	/** Renvoit les notes enregistrer par la cellule.
		@return La liste des notes saisie par le joueur.
		@since V0.1
		@author Julien , Erwan
		*/
	int[] getNotes(){
		int[] notes = new int[this.count_notes()];
		for( int i=0,j=0 ; (i<this.notes.length) && (j<notes.length) ; i++ ){
			if( this.notes[i] == true ){
				notes[j] = i + WhiteCell.VALUE_MIN;
			}
		}
		return notes;
	}

	/** Saisit les notes dans la cellule
		@param notes La liste des notes à saisire.
		@return La liste des notes n'ayant pas pût être sauvegardé, car incorrect.
		@since V0.1
		@author Julien , Erwan
		*/
	int[] setNotes(int[] notes){
		java.util.ArrayList<Integer> erreurs = new ArrayList<>();
		java.util.Arrays.sort( notes );
		for( int i=0,j=0 ; (i<notes.length) && ; i++ ){
			int note = notes[i] - WhiteCell.VALUE_MIN;
			if( (note>WhiteCell.VALUE_MAX) || (note<WhiteCell.VALUE_MIN) ){
				erreurs.add( note );
			} else {
				while( j<note ){
					this.notes[j] = false;
					j++;
				}
				notes[j] = true;
			}
		}
		return erreurs.stream().mapToInt(Integer::intValue).toArray();
	}
	int[] addNotes(int[] valeurNote) {
		java.util.ArrayList<Integer> erreurs = new ArrayList<>();
		java.util.Arrays.sort( notes );
		for( int i=0,j=0 ; (i<notes.length) && ; i++ ){
			int note = notes[i] - WhiteCell.VALUE_MIN;
			if( (note>WhiteCell.VALUE_MAX) || (note<WhiteCell.VALUE_MIN) ){
				erreurs.add( note );
			} else {
				notes[note] = true;
			}
		}
		return erreurs.stream().mapToInt(Integer::intValue).toArray();
	}
	int[] removeNotes(int[] valeurNote) {
		java.util.ArrayList<Integer> erreurs = new ArrayList<>();
		java.util.Arrays.sort( notes );
		for( int i=0,j=0 ; (i<notes.length) && ; i++ ){
			int note = notes[i] - WhiteCell.VALUE_MIN;
			if( (note>WhiteCell.VALUE_MAX) || (note<WhiteCell.VALUE_MIN) ){
				erreurs.add( note );
			} else {
				notes[note] = false;
			}
		}
		return erreurs.stream().mapToInt(Integer::intValue).toArray();
	}
	/** Supprime toutes les notes de la cellule.
		@since V0.1
		@author Julien
		*/
	void cleanNotes(){
		for( int i=0 ; i<this.notes.length ; i++ ){
			this.notes[i] = false;
		}
	}

	//
	// Autres méthodes
	//

	/** Nettoie la cellule.
		@since V0.1
		@author Julien , Erwan
		Supprime toutes les notes du joueur et la valeur saisit.
		*/
	void clean(){
		this.clean_value();
		this.clean_notes();
	}

	//
	// Affichage
	//

	/** Affiche une cellule blanche avec la valeur saisit par le joueur
		@return La cellule formater pour affichage
		@since V0.1
		@author Erwan
		*/
	@Override
	String toString(){
		if( this.getValue() ){
			return " "+this.getValue()+" ";
		} else {
			return " X ";
		}
	}
}

