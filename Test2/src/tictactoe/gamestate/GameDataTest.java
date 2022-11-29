package tictactoe.gamestate;

import junit.framework.TestCase;
import tictactoe.Player;

public class GameDataTest extends TestCase {

    public static class AddMoveTest extends TestCase {
        public void test_should_add_move() {
            final var gameData = new GameData("_________");
            final var expected = "X___O____";

            gameData.addMove(0, 0, Player.X);
            gameData.addMove(1, 1, Player.O);
            final var actual = gameData.getGameStateLine();                    // internally we use order of 'row, column'
            System.out.println( "actual: " + actual);
            assertEquals(expected, actual);
        }
    }

    public void testDummyTestForClassWithoutFurtherTests(){
        assertTrue(true);
    }
}