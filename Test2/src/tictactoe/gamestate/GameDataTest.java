package tictactoe.gamestate;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import tictactoe.Player;

class GameDataTest {

    @Nested
    class AddMoveTest {
        @Test
        void test_should_add_move() {
            final var gameData = new GameData("_________");
            final var expected = "X___O____";

            gameData.addMove(0, 0, Player.X);
            gameData.addMove(1, 1, Player.O);
            final var actual = gameData.getGameStateLine();                    // internally we use order of 'row, column'
            System.out.println("actual: " + actual);
            assertEquals(expected, actual);
        }
    }
}