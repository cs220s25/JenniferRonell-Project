package edu.moravian.BattleshipBot;
import edu.moravian.BattleshipBot.exceptions.*;

public class BotResponder {
    private final BattleshipGame game;
    private BotState state;


    public BotResponder(BattleshipGame game) {
        this.game = game;
        state = BotState.NO_GAME;
    }


    public String response(String username, String message) {
        try {
            if (message.equals("!playbattleship")) {
                return handleStart();
            } else if (message.matches("[A-G][1-7]")) {
                return handleMove(username, message);
            } else if (message.matches("!endgame")) {
                return handleEnd();
            } else if (message.matches("!status")) {
                return handleStatus();
            } else if (message.equals("!help")) {
                return BotResponses.help();
            } else {
                return BotResponses.invalidCoordinate(message);
            }
        } catch (InternalServerException e) {
            return BotResponses.serverError();
        }
    }

    /**
     * Begins a new game of battleship
     *
     * @return
     * @throws InternalServerException
     */
    private String handleStart() throws InternalServerException {
        if (state != BotState.NO_GAME) {
            return BotResponses.gameAlreadyInProgress();
        }

        game.startGame();
        state = BotState.IN_PROGRESS;
        return BotResponses.gameStarted();
    }


    private String handleMove(String username, String coordinate) throws InternalServerException {
        if (game.getMovesRemaining() <= 0) {
            return handleLoss();
        }
        if (game.isAllSunk()) {
            return handleWin();
        }
        try {
            boolean coordinateHit = game.processMove(coordinate);

            if (coordinateHit) {
                System.out.println("Hit at " + coordinate);
                Ship hitShip = game.getShipTypeAtCoordinate(coordinate);
                if (hitShip != null && hitShip.isSunk()) {
                    return BotResponses.shipSunk(hitShip.getLength(), game.getMovesRemaining(), game.getRemainingShips());
                }
                return BotResponses.hit(username, coordinate, game.getMovesRemaining());
            } else {
                return BotResponses.miss(username, coordinate, game.getMovesRemaining());
            }
        } catch (CoordinateAlreadyUsedException e) {
            return BotResponses.coordinateAlreadyUsed(coordinate);
        } catch (StorageException e) {
            return BotResponses.serverError();
        }
    }

    /** The gameStatus response should give the player their moves remaining, the ships they've sunk,
     * and the ships they have left to sink
     */
    private String handleStatus() throws InternalServerException {
        if (state == BotState.NO_GAME) {
            return BotResponses.noGameStarted();
        }
        try {
            return BotResponses.gameStatus(game.getMovesRemaining(), game.getRemainingShips());
        } catch (StorageException e) {
            return BotResponses.serverError();
        }
    }


    private String handleEnd() throws InternalServerException {
        System.out.println("Game ended early");
        if (state == BotState.NO_GAME) {
            return BotResponses.noGameStarted();
        }

        game.endGame();
        state = BotState.NO_GAME;
        return BotResponses.gameEnded();
    }


    private String handleLoss() throws InternalServerException {
        System.out.println("Game lost, ran out of moves");
        game.endGame();
        state = BotState.NO_GAME;
        return BotResponses.gameOverOutOfMoves(game.getBoard());
    }



    private String handleWin() throws InternalServerException {
        System.out.println("Game won");
        game.endGame();
        state = BotState.NO_GAME;
        return BotResponses.gameWon(game.getMovesRemaining());
    }

}


