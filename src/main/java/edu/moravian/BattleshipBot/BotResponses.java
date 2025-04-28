package edu.moravian.BattleshipBot;

public class BotResponses {

    public static String serverError() {
        return "Internal server error. Please try again later.";
    }

    public static String gameStarted() {
        return "🎉 Welcome to Battleship! A 7x7 game board has been created. 🎉 \n" +
                " I’ve hidden three ships of lengths 2, 3, and 4 across it.\n " +
                "You have 24 moves to sink all the ships. To make a move, enter a coordinate from A1-G7 (Columns are A-G and rows are 1-7)\n" +
                " You can end the game early at any point using `!endgame`, and can check the current game stats using `!status`.\n" +
                " Good luck! Make your first move!";
    }

    public static String hit(String username, String coordinate, int movesLeft) {
        return "💥 Hit! " + username + " struck part of a ship at " + coordinate + "! Keep going to sink it! You have "
                + movesLeft + (movesLeft > 1 ? " moves left." : " move left.");
    }


    public static String miss(String username, String coordinate, int movesLeft) {
        return "🌊 Miss! No ship at " + coordinate + ". " + username + " has " + movesLeft + (movesLeft > 1 ? " moves left." : " move left.") + " Try again!";
    }


    public static String coordinateAlreadyUsed(String coordinate) {
        return "😕 Sorry! " + coordinate + " has already been played. Try a different coordinate!";
    }


    public static String invalidCoordinate(String coordinate) {
        return "😕 Sorry! “" + coordinate + "” isn’t a valid input. Remember, the board goes from A1 to G7. Try a coordinate within those boundaries.";
    }


    public static String shipSunk(int shipLength, int movesLeft, int shipsRemaining) {
        if (shipsRemaining > 0) {
            return "🚢 Sunk! You sunk a " + shipLength + "-length ship! " + shipsRemaining + " ship" + (shipsRemaining > 1 ? "s remain." : " remains." +
                    " You have " + movesLeft + " moves left.");
        }
        return BotResponses.gameWon(movesLeft);
    }


    public static String gameWon(int movesRemaining) {
        return "🎉 Congratulations! You’ve sunk all the ships with " + movesRemaining + " moves remaining! " +
                "You’ve won the game! Would you like to play again? Just type `!playbattleship` to start a new game.";
    }


    public static String gameOverOutOfMoves(String finalBoard) {
        return "❌ Game Over! You ran out of moves before sinking all the ships, better luck next time! Here’s the final board:\n\n" +
                finalBoard + "\n\nWant to try again? Type `!playbattleship` to start a new game.";
    }


    public static String gameAlreadyInProgress() {
        return "A game is already in progress! Type '!endGame' to end the current game.";
    }


    public static String noGameStarted() {
        return "No game has been started. Use `!playbattleship` to start a new game.";
    }


    public static String gameStatus(int movesRemaining, int shipsRemaining) {
        return "📊 Game Status:\n " + movesRemaining + " moves remaining. \n" +
                "There are " + shipsRemaining + " ships left.";

    }

    public static String gameEnded() {
        return "Game has been ended early. Use `!playbattleship` to start a new game!";
    }

    public static String help() {
        return "🚢 Battleship Help 🚢\n" +
                "To start a new game, type `!playbattleship`.\n" +
                "To make a move, type a coordinate from A1 to G7 (e.g., `A1`).\n" +
                "To check the current game status, type `!status`.\n" +
                "To end the current game, type `!endgame`.\n" +
                "To view this help message, type `!help`.";
    }
}