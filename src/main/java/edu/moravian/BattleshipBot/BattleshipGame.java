/*
 * model.BattleshipGame.java
 * This class is the main class for the game. It contains the game logic and interacts with the storage class.
 * It also contains the methods to start, end, and process the game.
 */
package edu.moravian.BattleshipBot;
import edu.moravian.BattleshipBot.exceptions.*;
import java.util.List;
import java.util.Map;


public class BattleshipGame {
    private final BattleshipGameStorage storage;
    private final Board board;


    public BattleshipGame(BattleshipGameStorage storage) {
        this.storage = storage;
        this.board = new Board();
    }

    public void startGame() throws InternalServerException {
        try {
            storage.resetGameState();
        } catch (StorageException e) {
            throw new InternalServerException("Error while starting game.");
        }
    }


    public boolean processMove(String coordinate) throws StorageException, InternalServerException {
        if (!isValidCoordinate(coordinate)) {
            throw new InvalidCoordinateException(coordinate);
        }

        if (storage.getPastMoves().contains(coordinate)) {
            throw new CoordinateAlreadyUsedException(coordinate);
        }

        storage.addMove(coordinate);
        storage.decrementMoves();

        return isHit(coordinate);
    }


    public void endGame() throws InternalServerException {
        try {
            storage.resetGameState();
            board.resetBoard();
        } catch (StorageException e) {
            throw new InternalServerException("Error while ending game.");
        }
    }


    public boolean isValidCoordinate(String coordinate) {
        if (coordinate.length() != 2) return false;

        char column = coordinate.charAt(0);
        char row = coordinate.charAt(1);

        return (column >= 'A' && column <= 'G') && (row >= '1' && row <= '7');
    }

    private boolean isHit(String coordinate) {
        Cell cell = Board.getCell(coordinate);

        if (cell.hasShip()) {
            cell.markHit();

            Ship hitShip = board.getShipAtCoordinate(coordinate);
            if (hitShip != null) {
                hitShip.registerHit();
            }

            return true;
        }

        return false;
    }

    // Check if all ships are sunk
    public boolean isAllSunk() {
        for (Ship ship : board.getShips().keySet()) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }


    public int getRemainingShips() throws StorageException {
        int remainingShips = 0;
        for (Ship ship : board.getShips().keySet()) {
            if (!ship.isSunk()) {
                remainingShips++;
            }
        }
        return remainingShips;
    }


    public int getMovesRemaining() throws InternalServerException {
        try {
            return storage.getMovesRemaining();
        } catch (StorageException e) {
            throw new InternalServerException("Error while getting moves remaining.");
        }
    }

    public String getBoard() {
        return board.getFinalBoard();
    }

    public Ship getShipTypeAtCoordinate(String coordinate) {
        for (Map.Entry<Ship, List<Cell>> entry : board.shipsCoordinates.entrySet()) {
            for (Cell cell : entry.getValue()) {
                if (cell.equals(Board.getCell(coordinate))) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }
}
