package edu.moravian.BattleshipBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BotResponderTest {
    private BotResponder responder;
    private BattleshipGame game;
    private MemoryStorage storage;

    @BeforeEach
    void setUp() {
        storage = new MemoryStorage();
        game = new BattleshipGame(storage);
        responder = new BotResponder(game);
    }

    @Test
    void testStartGame() {
        String response = responder.response("testUser", "!playbattleship");
        assertEquals(BotResponses.gameStarted(), response);
    }

    @Test
    void testStartGameWhileInProgress() {
        responder.response("testUser", "!playbattleship"); // Start the game
        String response = responder.response("testUser", "!playbattleship");
        assertEquals(BotResponses.gameAlreadyInProgress(), response);
    }


    @Test
    void testMoveMiss() {
        responder.response("testUser", "!playbattleship");
        String response = responder.response("testUser", "B2"); // Simulate no ship at "B2"
        assertTrue(response.contains("🌊 Miss!")); // Check if the miss response is returned
    }

    @Test
    void testInvalidCoordinate() {
        String response = responder.response("testUser", "Z9");
        assertTrue(response.contains("😕 Sorry!")); // Invalid coordinate response
    }

    @Test
    void testCoordinateAlreadyUsed() {
        responder.response("testUser", "!playbattleship");
        responder.response("testUser", "C3");
        String response = responder.response("testUser", "C3"); // Replaying the same coordinate
        assertTrue(response.contains("😕 Sorry!")); // Coordinate already used response
    }

    @Test
    void testStatusBeforeGameStarts() {
        String response = responder.response("testUser", "!status");
        assertEquals(BotResponses.noGameStarted(), response);
    }

    @Test
    void testStatusDuringGame() {
        responder.response("testUser", "!playbattleship");
        String response = responder.response("testUser", "!status");
        assertTrue(response.contains("📊 Game Status:")); // Game status response
    }

    @Test
    void testEndGameBeforeStarting() {
        String response = responder.response("testUser", "!endgame");
        assertEquals(BotResponses.noGameStarted(), response);
    }

    @Test
    void testEndGameDuringGame() {
        responder.response("testUser", "!playbattleship");
        String response = responder.response("testUser", "!endgame");
        assertEquals(BotResponses.gameEnded(), response);
    }

    @Test
    void testHelpCommand() {
        String response = responder.response("testUser", "!help");
        assertEquals(BotResponses.help(), response);
    }
}