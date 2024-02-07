package fr.mcgcorp;

/** Représentation d'une coordonnée dans un milieu matriciel et/ou graphique.
 * @author PECHON Erwan
 * 
 * Cette classe à pour but de rassemblé les coordonnées transmise de méthodes en méthodes, afin de les rendres plus compréhensible.
 * L'objectif secondaire de cette classe est de servire de traducteur entre les coordonnée de l'interface utilisateur et les coordonnée de la matrice de jeu.
 *
 */
class Coord {
	/** Le numéro de ligne dans la matrice de jeu. */
	protected int posL;
	/** Le numéro de colonne dans la matrice de jeu. */
	protected int posC;

	/** Le constructeur des coordonnée est privé afin d'obligé les programmeur à utilisé des constructeurs avec des noms plus parlant à leurs besoins.
	 * @author PECHON Erwan
	 */
	private Coord(){
		this.posL = -1;
		this.posC = -1;
	}



	/** Le constructeur des coordonnée dans le millieu de l'interface graphique.
	 * @author PECHON Erwan
	 * @param posX Une coordonnée graphique
	 * @param posY L'autre coordonnée graphique
	 * @return Les coordonnées du point désirer.
	 */
	public static Coord createCoord_graphical(int posX, int posY){
		Coord coord = new Coord();
		coord.setPosX(posX);
		coord.setPosY(posY);
		return coord;
	}

	/** Un accesseur sur une coordonnée graphique
	 * @author PECHON Erwan
	 * @return Une coordonnée graphique
	 */
	public int getPosX(){
		return posL;
	}
	/** Un accesseur sur une coordonnée graphique
	 * @author PECHON Erwan
	 * @return L'autre coordonnée graphique
	 */
	public int getPosY(){
		return posC;
	}
	/** Un accesseur sur une coordonnée graphique
	 * @author PECHON Erwan
	 * @param posX Une coordonnée graphique
	 */
	protected void setPosX(int posX){
		this.posL = posX;
	}
	/** Un accesseur sur une coordonnée graphique
	 * @author PECHON Erwan
	 * @param posY L'autre coordonnée graphique
	 */
	protected void setPosY(int posY){
		this.posC = posY;
	}



	/** Le constructeur des coordonnée pour une matrice
	 * @author PECHON Erwan
	 * @param posL L'indice de la ligne où se trouve la case recherché.
	 * @param posC L'indice de la colonne où se trouve la case recherché.
	 * @return Les coordonnées du point désirer.
	 */
	public static Coord createCoord_matriciel(int posL, int posC){
		Coord coord = new Coord();
		coord.setPosL(posL);
		coord.setPosC(posC);
		return coord;
	}

	/** Un accesseur sur une coordonnée matriciel
	 * @author PECHON Erwan
	 * @return L'indice de la ligne où se trouve la case.
	 */
	public int getPosL(){
		return posL;
	}
	/** Un accesseur sur une coordonnée matriciel
	 * @author PECHON Erwan
	 * @return L'indice de la colonne où se trouve la case.
	 */
	public int getPosC(){
		return posC;
	}
	/** Un accesseur sur une coordonnée matriciel
	 * @author PECHON Erwan
	 * @param posL L'indice de la ligne où se trouve la case.
	 */
	protected void setPosL(int posL){
		this.posL = posL;
	}
	/** Un accesseur sur une coordonnée matriciel
	 * @author PECHON Erwan
	 * @param posC L'indice de la colonne où se trouve la case.
	 */
	protected void setPosC(int posC){
		this.posC = posC;
	}
}
