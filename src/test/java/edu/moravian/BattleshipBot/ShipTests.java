package edu.moravian.BattleshipBot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class ShipTests {

    @Test
    public void testNewShipHasCorrectLength() {
        Ship ship = new Ship(2);
        assertEquals(2, ship.getLength());

        ship = new Ship(3);
        assertEquals(3, ship.getLength());

        ship = new Ship(4);
        assertEquals(4, ship.getLength());
    }

    @Test
    public void testNewShipHasNoHits() {
        Ship ship = new Ship(2);
        assertEquals(0, ship.getHits());

        ship = new Ship(3);
        assertEquals(0, ship.getHits());

        ship = new Ship(4);
        assertEquals(0, ship.getHits());
    }

    @Test
    public void testRegisterHit() {
        Ship ship = new Ship(2);
        ship.setCoordinates(Arrays.asList(new Cell('A', 1), new Cell('A', 2)));

        ship.registerHit();
        assertEquals(1, ship.getHits());

        ship.registerHit();
        assertEquals(2, ship.getHits());
    }

    @Test
    public void testIsSunk() {
        Ship ship = new Ship(2);
        ship.setCoordinates(Arrays.asList(new Cell('A', 1), new Cell('A', 2)));

        ship.registerHit();
        assertFalse(ship.isSunk());

        ship.registerHit();
        assertTrue(ship.isSunk());
    }

    @Test
    public void testAllShipsSunk() {
        Ship ship2 = new Ship(2);
        Ship ship3 = new Ship(3);
        Ship ship4 = new Ship(4);

        ship2.setCoordinates(Arrays.asList(new Cell('A', 1), new Cell('A', 2)));
        ship3.setCoordinates(Arrays.asList(new Cell('B', 1), new Cell('B', 2), new Cell('B', 3)));
        ship4.setCoordinates(Arrays.asList(new Cell('C', 1), new Cell('C', 2), new Cell('C', 3), new Cell('C', 4)));

        ship2.registerHit();
        ship2.registerHit();
        ship3.registerHit();
        ship3.registerHit();
        ship3.registerHit();
        ship4.registerHit();
        ship4.registerHit();
        ship4.registerHit();
        ship4.registerHit();
        assertTrue(ship2.isSunk() && ship3.isSunk() && ship4.isSunk());
    }
}