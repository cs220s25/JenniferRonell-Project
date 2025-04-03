package edu.moravian.BattleshipBot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTests {


    @Test
    public void testToStringForEmptyCell() {
        Cell cell = new Cell('A', 1);
        assertEquals("A1~", cell.toString());
    }

    @Test
    public void testToStringForOccupiedCell() {
        Cell cell = new Cell('A', 1);
        cell.setOccupied();
        assertEquals("A1S", cell.toString());
    }

    @Test
    public void testToStringForHitEmptyCell() {
        Cell cell = new Cell('A', 1);
        cell.markHit();
        assertEquals("A1O", cell.toString());
    }

    @Test
    public void testToStringForHitOccupiedCell() {
        Cell cell = new Cell('A', 1);
        cell.setOccupied();
        cell.markHit();
        assertEquals("A1X", cell.toString());
    }
}