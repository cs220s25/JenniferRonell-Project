package edu.moravian.BattleshipBot.exceptions;

public class NoGameInProgress extends RuntimeException {

    public NoGameInProgress() {
        super("Sorry! There isn't a game in progress. Type !playbattleship to start a game!");
    }
}
