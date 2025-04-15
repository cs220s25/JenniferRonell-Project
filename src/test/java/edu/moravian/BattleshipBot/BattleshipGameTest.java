package edu.moravian.BattleshipBot;

import edu.moravian.BattleshipBot.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BattleshipGameTest {
    private BattleshipGame game;
    private MemoryStorage storage;

    @BeforeEach
    public void setup() {
        storage = new MemoryStorage();
        game = new BattleshipGame(storage);
    }

    @Test
    public void testStartGame() throws StorageException {
        assertDoesNotThrow(() -> game.startGame());
        assertEquals(24, storage.getMovesRemaining());
        assertTrue(storage.getPastMoves().isEmpty());
    }


    @Test
    public void testProcessInvalidCoordinate() {
        String invalidCoordinate = "Z9";
        assertThrows(InvalidCoordinateException.class, () -> game.processMove(invalidCoordinate));
    }

    @Test
    public void testProcessMoveAlreadyUsed() throws Exception {
        String coordinate = "B2";
        game.processMove(coordinate);
        assertThrows(CoordinateAlreadyUsedException.class, () -> game.processMove(coordinate));
    }

    @Test
    public void testEndGame() throws StorageException {
        assertDoesNotThrow(() -> game.endGame());
        assertEquals(24, storage.getMovesRemaining());
        assertTrue(storage.getPastMoves().isEmpty());
    }

    @Test
    public void testIsValidCoordinate() {
        assertTrue(game.isValidCoordinate("A1"));
        assertTrue(game.isValidCoordinate("G7"));
        assertFalse(game.isValidCoordinate("H1"));
        assertFalse(game.isValidCoordinate("A8"));
        assertFalse(game.isValidCoordinate("AB"));
    }


    @Test
    public void testGetRemainingShips() throws StorageException {
        int remainingShips = game.getRemainingShips();
        assertEquals(3, remainingShips);
    }

    @Test
    public void testGetMovesRemaining() throws Exception {
        assertEquals(24, game.getMovesRemaining());
        game.processMove("A1");
        assertEquals(23, game.getMovesRemaining());
    }


    @Test
    public void testGetBoard() {
        String board = game.getBoard();
        assertNotNull(board);
    }
}