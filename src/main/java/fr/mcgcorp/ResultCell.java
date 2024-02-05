package fr.mcgcorp;

class ResultCell {
	/* Attributs d'instance */
	private int row;
	private int column;

	/* Constructeur */
	ResultCell(int row, int column){
		super();
		this.row = row;
		this.column = column;
	}

	/* Accesseur */
	int getRow(){
		return this.row;
	}
	int getColumn(){
		return this.column;
	}

	/* Affichage */
	@Override
	String toString(){
		return this.getRow()+'/'+this.getColumn();
	}
}

