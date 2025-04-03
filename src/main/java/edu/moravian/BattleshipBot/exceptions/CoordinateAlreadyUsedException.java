package edu.moravian.BattleshipBot.exceptions;

public class CoordinateAlreadyUsedException extends RuntimeException {
    public CoordinateAlreadyUsedException(String coordinate) {
        super("Coordinate has already been used.");
    }
}
