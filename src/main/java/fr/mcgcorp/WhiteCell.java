package fr.mcgcorp;

class WhiteCell extends Cell {
	/* Attributs de classe */
	static private int VALUE_MAX = 9;
	static private int VALUE_MIN = 1;

	/* Attributs d'instance */
	private int correctValue;
	private int value;
	private boolean[] notes;

	/* Constructeur */
	WhiteCell(int correctValue){
		super();
		this.correctValue = correctValue;
		this.notes = new boolean[WhiteCell.VALUE_MAX-WhiteCell.VALUE_MIN+1];
		this.clean();
	}

	/* Accesseur sur la valeur correct */
	int getCorrectValue(){
		return this.correctValue;
	}
	boolean isCorrect(){
		return this.correctValue == this.value;
	}

	/* Gestion de la valeur saisie */
	int getValue(){
		return this.value;
	}
	void setValue(int value) throws IllegalArgumentException {
		if( value > WhiteCell.VALUE_MAX ){
			throw new IllegalArgumentException("La valeur ne peut pas être supérieur à "+WhiteCell.VALUE_MAX+" : " + valeur);
		}
		if( value < WhiteCell.VALUE_MIN ){
			throw new IllegalArgumentException("La valeur ne peut pas être inférieur à "+WhiteCell.VALUE_MIN+" : " + valeur);
		}
		this.value = value;
	}

	/* Gestion des notes */
	private int[] count_notes(){
		int count = 0;
		for( int i=0 ; i<this.notes.length ; i++ ){
			if( this.notes[i] == true ){
				count++;
			}
		}
		return count;
	}
	int[] getNotes(){
		int[] notes = new int[this.count_notes()];
		for( int i=0,j=0 ; (i<this.notes.length) && (j<notes.length) ; i++ ){
			if( this.notes[i] == true ){
				notes[j] = i;
			}
		}
		return notes;
	}
	void cleanNotes(){
		for( int i=0 ; i<this.notes.length ; i++ ){
			this.notes[i] = false;
		}
	}

	/* Autres méthodes */
	void clean(){
		this.value = WhiteCell.VALUE_MIN - 1;
		this.clean_notes();
	}

	/* Affichage */
	@Override
	String toString(){
		return " "+this.getValue()+" ";
	}
}

