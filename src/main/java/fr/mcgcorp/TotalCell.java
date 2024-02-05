package fr.mcgcorp;

public class TotalCell extends Cell {
    // Somme des chiffres en colonne
    private int column;
    // Somme des chiffres en ligne
    private int row;

    public TotalCell(int column, int row) {
        super();
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }


}
