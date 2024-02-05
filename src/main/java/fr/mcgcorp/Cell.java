package fr.mcgcorp;

abstract class Cell {
    public boolean isModifiable() {
        return false;
    }

    public boolean setValue(int value) {
        return false;
    }
}
