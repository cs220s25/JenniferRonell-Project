package edu.moravian.BattleshipBot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testInitializeBoard() {
        Cell[][] gameBoard = Board.gameBoard;
        assertNotNull(gameBoard);
        assertEquals(7, gameBoard.length);
        assertEquals(7, gameBoard[0].length);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                assertNotNull(gameBoard[i][j]);
                assertEquals((char) ('A' + i), gameBoard[i][j].getColumn());
                assertEquals(j + 1, gameBoard[i][j].getRow());
            }
        }
    }

    @Test
    void testInitializeShips() {
        Map<Ship, List<Cell>> ships = board.getShips();
        assertNotNull(ships);
        assertEquals(3, ships.size());

        for (Ship ship : ships.keySet()) {
            assertNotNull(ship);
            assertTrue(ship.getLength() >= 2 && ship.getLength() <= 4);
        }
    }

    @Test
    void testPlaceShips() {
        Map<Ship, List<Cell>> ships = board.getShips();
        for (Map.Entry<Ship, List<Cell>> entry : ships.entrySet()) {
            List<Cell> shipCells = entry.getValue();
            assertNotNull(shipCells);
            assertEquals(entry.getKey().getLength(), shipCells.size());
        }
    }


    @Test
    void testResetBoard() {
        board.resetBoard();
        Cell[][] gameBoard = Board.gameBoard;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                assertFalse(gameBoard[i][j].hasShip());
            }
        }
    }

    @Test
    void testPrintBoard() {
        String boardString = Board.printBoard();
        assertNotNull(boardString);
        assertTrue(boardString.contains("A"));
        assertTrue(boardString.contains("1"));
    }
}
