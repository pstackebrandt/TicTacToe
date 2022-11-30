package tictactoe;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import tictactoe.gamestate.GameData;

import java.awt.*;
import java.util.Scanner;

class GameTest {
    @Nested
    class AskMoveTest {
        @Test
        void test_should_accept_correct_values_and_return_point() {
            final var gameData = new GameData("_________");
            final var game = new Game(gameData);

            final var scanner = new Scanner("3 2"); // (row col)
            final var actual = game.askMove(Player.X, scanner);
            // internally we use order of 'row, column'
            Assertions.assertEquals(2, actual.x, "x (row) should be as expected."); // row
            Assertions.assertEquals(1, actual.y, "y (column) should be as expected."); // col
        }
    }

    @Nested
    class GetInitialGameStateTest {
        @Test
        void test_should_accept_correct_values_and_return_game_state() {
            final var expected = "__XO_____";
            final var scanner = new Scanner("__XO_____");

            final var actual = new Game().getInitialGameState(scanner);

            Assertions.assertEquals(actual, expected); // row
        }

        @Test
        void test_should_reject_line_with_wrong_char_count_and_return_game_state_of_correct_line() {
            final var expected = "__XO_____";
            final var scanner = new Scanner("__XO__\n__XO_____"); //first line is wrong, second ok

            final var actual = new Game().getInitialGameState(scanner);

            Assertions.assertEquals(expected, actual); // row
        }

        @Test
        void test_should_reject_line_with_wrong_chars_and_return_game_state_of_correct_line() {
            final var expected = "__XO_____";
            final var scanner = new Scanner("__XO_1___\n__XO_____"); //first line is wrong, second ok

            final var actual = new Game().getInitialGameState(scanner);

            Assertions.assertEquals(actual, expected); // row
        }
    }

    @Nested
    class IsCoordinateWithinBoundsTest {

        @Test
        void testShouldValidateCorrectPoint() {
            var actual = new Game().isCoordinateWithinBounds(new Point(1, 2));
            Assertions.assertTrue(actual);
        }

        @Test
        void testShouldInvalidateXValueAsTooSmall() {
            var actual = new Game().isCoordinateWithinBounds(new Point(1, 3));
            Assertions.assertFalse(actual);
        }

        @Test
        void testShouldInvalidateYValueAsTooSmall() {
            var actual = new Game().isCoordinateWithinBounds(new Point(0, -1));
            Assertions.assertFalse(actual);
        }
    }

    @Nested
    class IsCellFreeTest {
        @Test
        void testShouldValidateFreeCell() {
            var actual = new Game().isCellFree(2, 1, "XO____X_O");
            Assertions.assertTrue(actual);
        }

        @Test
        void testShouldTellThatCellIsTaken1() {
            var actual = new Game().isCellFree(2, 1, "XO____XOO");
            Assertions.assertFalse(actual);
        }

        @Test
        void testShouldTellThatCellIsTaken2() {
            var actual = new Game().isCellFree(2, 1, "_XXOO_OX_");
            Assertions.assertFalse(actual);
        }

    }

    @Nested
    class GetNumberFromConsoleTest {

        @Test
        void testShouldExtractNumber() {
            final var scanner = new Scanner("2 3");
            final var actual = new Game().getNumberFromConsole(scanner);
            Assertions.assertEquals(2, (int) actual, "Should return first number of input.");
        }

        @Test
        void testShouldInvalidateStringInput() {
            final var scanner = new Scanner("ab cd");
            final var actual = new Game().getNumberFromConsole(scanner);
            Assertions.assertNull(actual, "Should return null because input was string instead of number.");
        }
    }

    @Nested
    class IsGameStateLineLengthValidTest {
        @Test
        void testShouldValidateGameStateWithCorrectLength() {
            final var actual = new Game().isGameStateLineLengthValid("_________");
            Assertions.assertTrue(actual, "Should return true because length of game state is ok.");
        }

        @Test
        void testShouldInvalidateGameStateWhichIsTooShort() {
            final var actual = new Game().isGameStateLineLengthValid("________"); // 8 chars
            Assertions.assertFalse(actual, "Should return false because game state is too short.");
        }
    }

    @Nested
    class IsGameStateConsistsOfValidCharsTest {
        @Test
        void testShouldValidateGameStateWithCorrectChars() {
            final var actual = new Game().isGameStateConsistsOfValidChars("_X__O______");
            Assertions.assertTrue(actual, "Should return true because all chars allowed.");
        }

        @Test
        void testShouldInvalidateGameStateUnexpectedChars() {
            final var actual = new Game().isGameStateConsistsOfValidChars("_y__10_XO_");
            Assertions.assertFalse(actual, "Should return false because game state contains unexpected chars.");
        }
    }

    @Test
    void testDummyTestForClassWithoutFurtherTests() {
        Assertions.assertTrue(true);
    }
}