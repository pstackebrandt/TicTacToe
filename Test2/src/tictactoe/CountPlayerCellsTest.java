package tictactoe;

import junit.framework.TestCase;

public class CountPlayerCellsTest extends TestCase {

    public void test_should_detect_3_cells_of_player_x() {
        final var stateLine = "_O_X__X_X";
        final var player = Player.X;

        final int result = Game.countPlayerCells(player, stateLine);

        assertEquals(3, result);
    }

    public void test_should_detect_1_cell_of_player_o() {
        final var stateLine = "_O_X__X_X";
        final var player = Player.O;

        final int result = Game.countPlayerCells(player, stateLine);

        assertEquals(1, result);
    }
}