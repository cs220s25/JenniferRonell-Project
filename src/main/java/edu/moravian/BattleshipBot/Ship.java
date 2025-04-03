package edu.moravian.BattleshipBot;

import java.util.List;

public class Ship {
    private final int length; // Length of the ship
    private int hits;         // Number of hits the ship has taken
    private List<Cell> coordinates; // List of cells the ship occupies

    // Constructor
    public Ship(int length) {
        this.length = length;
        this.hits = 0;
    }

    // Sets the coordinates occupied by the ship
    public void setCoordinates(List<Cell> coordinates) {
        this.coordinates = coordinates;
        for (Cell cell : coordinates) {
            cell.setOccupied();
        }
    }

    // Returns the coordinates occupied by the ship
    public List<Cell> getCoordinates() {
        return coordinates;
    }

    // Registers a hit on the ship
    public void registerHit() {
        hits++;
    }

    // Checks if the ship is sunk
    public boolean isSunk() {
        return hits == length;
    }

    // Returns the length of the ship
    public int getLength() {
        return this.length;
    }

    // Returns the number of hits taken
    public int getHits() {
        return hits;
    }
}