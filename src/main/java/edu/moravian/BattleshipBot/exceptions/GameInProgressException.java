package edu.moravian.BattleshipBot.exceptions;

public class GameInProgressException extends RuntimeException {

    public GameInProgressException() {
        super("A game is already in progress!");
    }
}
