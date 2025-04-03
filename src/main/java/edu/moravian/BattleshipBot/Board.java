package edu.moravian.BattleshipBot;

import java.util.*;


public class Board {
    public static Cell[][] gameBoard;
    public Map<Ship, List<Cell>> shipsCoordinates;

    public Board() {
        initializeBoard();
        initializeShips();
        placeShips();
        System.out.println("model.Board initialized:\n " + printBoard());
    }

    private void initializeBoard() {
        this.gameBoard = new Cell[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                gameBoard[i][j] = new Cell((char) ('A' + i), j + 1);
            }
        }
    }

    private void initializeShips() {
        this.shipsCoordinates = new HashMap<>();
        Ship ShipLength2 = new Ship(2);
        Ship ShipLength3 = new Ship(3);
        Ship ShipLength4 = new Ship(4);
        shipsCoordinates.put(ShipLength2, new ArrayList<>());
        shipsCoordinates.put(ShipLength3, new ArrayList<>());
        shipsCoordinates.put(ShipLength4, new ArrayList<>());
    }


    private void placeShips() {
        for (Map.Entry<Ship, List<Cell>> entry : shipsCoordinates.entrySet()) {
            Ship ship = entry.getKey();
            List<Cell> coordinates = entry.getValue();
            int length = ship.getLength();
            boolean isHorizontal = new Random().nextBoolean();
            int row = new Random().nextInt(7);
            int column = new Random().nextInt(7);
            if (isHorizontal) {
                if (column + length > 7) {
                    column -= length;
                }
                for (int i = 0; i < length; i++) {
                    Cell cell = gameBoard[row][column + i];
                    coordinates.add(cell);
                }
            } else {
                if (row + length > 7) {
                    row -= length;
                }
                for (int i = 0; i < length; i++) {
                    Cell cell = gameBoard[row + i][column];
                    coordinates.add(cell);
                }
            }
            ship.setCoordinates(coordinates);
        }
    }


    // Checks if a specific ship of a given length is sunk
    public boolean isShipSunk(int length) {
        for (Map.Entry<Ship, List<Cell>> entry : shipsCoordinates.entrySet()) {
            if (entry.getKey().getLength() == length) {
                Ship ship = entry.getKey();
                return ship.isSunk();
            }
        }
        return false;
    }

    public Map<Ship, List<Cell>> getShips() {
        return shipsCoordinates;
    }

    public static Cell getCell(String coordinate) {
        char column = coordinate.charAt(0);
        int row = Integer.parseInt(coordinate.substring(1));
        return gameBoard[row - 1][column - 'A'];
    }

    public Ship getShipAtCoordinate(String coordinate) {
        for (Map.Entry<Ship, List<Cell>> entry : shipsCoordinates.entrySet()) {
            for (Cell cell : entry.getValue()) {
                if (cell.equals(getCell(coordinate))) {
                    return entry.getKey();
                }
            }
        }
        return null; // No ship found at the coordinate
    }



    public static String printBoard() {
        StringBuilder board = new StringBuilder();
        board.append("A  B  C   D   E   F  G\n");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                String coordinate = "" + (char) ('A' + j) + (i + 1);
                if (gameBoard[i][j].hasShip()) {
                    board.append("S ");
                } else {
                    board.append(coordinate).append(" ");
                }
            }
            board.append("\n");
        }
        return board.toString();
    }


    public void resetBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                gameBoard[i][j].resetCell();
            }
        }
    }

    public boolean hasShip(String coordinate) {
        return getCell(coordinate).hasShip();
    }

    public String getFinalBoard() {
        return printBoard();
    }
}
