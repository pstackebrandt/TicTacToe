package tictactoe.gamestate;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import tictactoe.Player;

class GameDataTest {
    final String stateLineOfNewGame = "_________";

    @Nested
    class AddMoveTest {
        @Test
        void test_should_add_move() {

            final var gameData = new GameData(stateLineOfNewGame);
            final var expected = "X___O____";

            gameData.addMove(0, 0, Player.X);
            gameData.addMove(1, 1, Player.O);
            final var actual = gameData.getGameStateLine();
            System.out.println("actual: " + actual);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetPlayer {
        @Test
        void test_should_get_player() {
            final var gameData = new GameData(stateLineOfNewGame, Player.O);
            final var expected = Player.O;

            final var actual = gameData.getCurrentPlayer();
            System.out.println("actual player: Player " + actual);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class SetPlayer {
        @Test
        void test_should_set_player() {
            final var gameData = new GameData(stateLineOfNewGame, Player.O);
            final var expected = Player.X;

            gameData.setCurrentPlayer(Player.X);
            final var actual = gameData.getCurrentPlayer();
            System.out.println("actual player: Player " + actual);
            assertEquals(expected, actual);
        }
    }
}