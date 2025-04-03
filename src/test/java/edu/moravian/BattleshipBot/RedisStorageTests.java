package edu.moravian.BattleshipBot;

import edu.moravian.BattleshipBot.exceptions.*;
import com.github.fppt.jedismock.RedisServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class RedisStorageTests {

    private RedisServer server;

    @BeforeEach
    public void setUp() throws IOException {
        server = RedisServer.newRedisServer();
        server.start();
    }

    @Test
    public void testNewInstance() throws StorageException {
        RedisStorage storage = new RedisStorage(server.getHost(), server.getBindPort());
        assertEquals(Set.of(), storage.getPastMoves());
        assertEquals(24, storage.getMovesRemaining());
    }

    @Test
    public void testResetGameState() throws StorageException {
        RedisStorage storage = new RedisStorage(server.getHost(), server.getBindPort());
        storage.addMove("A1");
        storage.addSunkShip("ship");
        storage.resetGameState();
        assertEquals(24, storage.getMovesRemaining());
        assertEquals(Set.of(), storage.getPastMoves());
        assertEquals(Set.of(), storage.getSunkShips());
    }

    @Test
    public void testDecrementMoves() throws StorageException {
        RedisStorage storage = new RedisStorage(server.getHost(), server.getBindPort());
        storage.decrementMoves();
        assertEquals(23, storage.getMovesRemaining());
    }

    @Test
    public void testAddMove() throws StorageException {
        RedisStorage storage = new RedisStorage(server.getHost(), server.getBindPort());
        storage.addMove("A1");
        assertEquals(Set.of("A1"), storage.getPastMoves());
    }

    @Test
    public void testAddSunkShip() throws StorageException {
        RedisStorage storage = new RedisStorage(server.getHost(), server.getBindPort());
        storage.addSunkShip("ship");
        assertEquals(Set.of("ship"), storage.getSunkShips());
    }

    @Test
    public void testGetPastMoves() throws StorageException {
        RedisStorage storage = new RedisStorage(server.getHost(), server.getBindPort());
        storage.addMove("A1");
        assertEquals(Set.of("A1"), storage.getPastMoves());
    }

    @Test
    public void testGetSunkShips() throws StorageException {
        RedisStorage storage = new RedisStorage(server.getHost(), server.getBindPort());
        storage.addSunkShip("ship");
        assertEquals(Set.of("ship"), storage.getSunkShips());
    }
}
