package edu.moravian.BattleshipBot;

public class Cell {
    public final char column;
    public final int row;
    public boolean cellHasShip;
    public boolean cellHit;

    public Cell(char column, int row) {
        this.column = column;
        this.row = row;
        this.cellHasShip = false;
        this.cellHit = false;
    }

    public void markHit() {
        this.cellHit = true;
    }

    public void setOccupied() {
        this.cellHasShip = true;
    }

    public boolean hasShip() {
        return this.cellHasShip;
    }

    public void resetCell() {
        this.cellHit = false;
        this.cellHasShip = false;
    }

    @Override
    public String toString() {
        return "" + column + row + (cellHit ? (cellHasShip ? "X" : "O") : (cellHasShip ? "S" : "~"));
    }

    public int getRow() {
        return row;
    }

    public char getColumn() {
        return column;
    }
}
