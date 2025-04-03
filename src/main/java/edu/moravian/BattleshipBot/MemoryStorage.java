package edu.moravian.BattleshipBot;

import edu.moravian.BattleshipBot.exceptions.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemoryStorage implements BattleshipGameStorage {
    private int movesRemaining;
    private Set<String> pastMoves;
    private List<String> sunkShips;


    public MemoryStorage() {
        movesRemaining = 24;
        pastMoves = new HashSet<>();
        sunkShips = new ArrayList<>();
    }

    @Override
    public void resetGameState() throws StorageException {
        movesRemaining = 24;
        pastMoves.clear();
        sunkShips.clear();
    }

    @Override
    public void decrementMoves() throws StorageException {
        movesRemaining--;
    }

    @Override
    public int getMovesRemaining() throws StorageException {
        return movesRemaining;
    }


    @Override
    public void addMove(String coordinate) throws StorageException {
        pastMoves.add(coordinate);
    }

    @Override
    public Set<String> getPastMoves() throws StorageException {
        return pastMoves;
    }

    @Override
    public void addSunkShip(String ship) throws StorageException {
        sunkShips.add(ship);
    }

    @Override
    public Set<String> getSunkShips() throws StorageException {
        return new HashSet<>(sunkShips);
    }
}