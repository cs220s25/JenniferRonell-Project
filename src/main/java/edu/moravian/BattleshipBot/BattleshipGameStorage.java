package edu.moravian.BattleshipBot;
import edu.moravian.BattleshipBot.exceptions.*;
import java.util.Set;

public interface BattleshipGameStorage {


    /**
     * Reset the game state.
     * @throws StorageException
     */
    void resetGameState() throws StorageException;

    void decrementMoves() throws StorageException;

    /**
     * Get the number of moves remaining.
     * @return the number of moves remaining
     * @throws StorageException
     */
    int getMovesRemaining() throws StorageException;


    /**
     * Add a move to pastMoves in storage.
     * @param coordinate the coordinate of the move
     * @throws StorageException
     */
    void addMove(String coordinate) throws StorageException;

    /**
     * Get the past moves.
     * @return the past moves as a set of strings
     * @throws StorageException
     */
    Set<String> getPastMoves() throws StorageException;


    /**
     * Add a sunk ship to storage.
     * @param s the name of the sunk ship
     * @throws StorageException
     */
    void addSunkShip(String s) throws StorageException;

    /**
     * Gets the sunk ships.
     * @return the sunk ships as a set of strings
     * @throws StorageException
     */
    Set<String> getSunkShips() throws StorageException;
}