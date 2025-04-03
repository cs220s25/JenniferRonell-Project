package edu.moravian.BattleshipBot;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;
import edu.moravian.BattleshipBot.exceptions.*;

import java.util.*;

public class RedisStorage implements BattleshipGameStorage {
    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;
    private final Jedis jedis;
    private int movesRemaining = 24;



    public RedisStorage(String hostname, int port) throws StorageException {
        try {
            jedis = new Jedis(hostname, port);
        } catch (JedisException e) {
            throw new StorageException("Could not connect to Redis server.");
        }
    }

    public void testConnection() throws StorageException {
        try {
            jedis.ping();
        } catch (JedisException e) {
            throw new StorageException("Could not connect to Redis server.");
        }
    }


    @Override
    public void resetGameState() throws StorageException {
        try {
            jedis.flushAll();
        } catch (JedisException e) {
            throw new StorageException("Could not connect to redis server.");
        }
    }

    @Override
    public void decrementMoves() throws StorageException {
        try {
            System.out.println("Decrementing moves");
            movesRemaining--;
            jedis.set("movesRemaining", Integer.toString(movesRemaining));
        } catch (JedisException e) {
            throw new StorageException("Could not connect to redis server.");
        }
    }

    @Override
    public int getMovesRemaining() throws StorageException {
        try {
            String movesRemainingString = jedis.get("movesRemaining");
            if (movesRemainingString == null) {
                return 24;
            }
            return Integer.parseInt(movesRemainingString);
        } catch (JedisException e) {
            throw new StorageException("Could not connect to Redis server.");
        }
    }

    @Override
    public void addMove(String coordinate) throws StorageException {
        try {
            jedis.sadd("moves", coordinate); // Add coordinate to Redis set
        } catch (JedisException e) {
            throw new StorageException("Could not connect to Redis server.");
        }
    }

    @Override
    public Set<String> getPastMoves() throws StorageException {
        try {
            return jedis.smembers("moves");
        } catch (JedisException e) {
            throw new StorageException("Could not connect to Redis server.");
        }
    }

    @Override
    public void addSunkShip(String ship) {
        jedis.sadd("sunkShips", ship);
    }

    @Override
    public Set<String> getSunkShips() throws StorageException {
        try {
            return jedis.smembers("sunkShips");
        } catch (JedisException e) {
            throw new StorageException("Could not connect to Redis server.");
        }
    }
}