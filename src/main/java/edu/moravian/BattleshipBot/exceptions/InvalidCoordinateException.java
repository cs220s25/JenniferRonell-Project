package edu.moravian.BattleshipBot.exceptions;
public class InvalidCoordinateException extends RuntimeException {

    public InvalidCoordinateException(String coordinate) {
        super("Sorry!" + coordinate + "isn’t a valid input. Remember, the board " +
                "goes from A1 to G7. Try a coordinate within those boundaries.");
    }
}
