package edu.moravian.BattleshipBot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryStorageTests {

    @Test
    public void testNewInstance() throws Exception
    {
        MemoryStorage memoryStorage = new MemoryStorage();
        assertEquals(24, memoryStorage.getMovesRemaining());
        assertEquals(0, memoryStorage.getPastMoves().size());
        assertEquals(0, memoryStorage.getSunkShips().size());
    }

    @Test
    public void testResetGameState() throws Exception
    {
        MemoryStorage memoryStorage = new MemoryStorage();
        memoryStorage.addMove("A1");
        memoryStorage.addSunkShip("ship");
        memoryStorage.resetGameState();
        assertEquals(24, memoryStorage.getMovesRemaining());
        assertEquals(0, memoryStorage.getPastMoves().size());
        assertEquals(0, memoryStorage.getSunkShips().size());
    }

    @Test
    public void testDecrementMoves() throws Exception
    {
        MemoryStorage memoryStorage = new MemoryStorage();
        memoryStorage.decrementMoves();
        assertEquals(23, memoryStorage.getMovesRemaining());
    }

    @Test
    public void testAddMove() throws Exception
    {
        MemoryStorage memoryStorage = new MemoryStorage();
        memoryStorage.addMove("A1");
        assertEquals(1, memoryStorage.getPastMoves().size());
    }

    @Test
    public void testAddSunkShip() throws Exception
    {
        MemoryStorage memoryStorage = new MemoryStorage();
        memoryStorage.addSunkShip("ship");
        assertEquals(1, memoryStorage.getSunkShips().size());
    }

    @Test
    public void testGetPastMoves() throws Exception
    {
        MemoryStorage memoryStorage = new MemoryStorage();
        memoryStorage.addMove("A1");
        assertEquals(1, memoryStorage.getPastMoves().size());
    }

    @Test
    public void testGetSunkShips() throws Exception
    {
        MemoryStorage memoryStorage = new MemoryStorage();
        memoryStorage.addSunkShip("ship");
        assertEquals(1, memoryStorage.getSunkShips().size());
    }

    @Test
    public void testGetMovesRemaining() throws Exception
    {
        MemoryStorage memoryStorage = new MemoryStorage();
        assertEquals(24, memoryStorage.getMovesRemaining());
    }
}
