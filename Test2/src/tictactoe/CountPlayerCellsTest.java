package tictactoe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CountPlayerCellsTest {

    @Test
    public void test_should_detect_3_cells_of_player_x() {
        final var stateLine = "_O_X__X_X";
        final var player = Player.X;

        final int result = Game.countPlayerCells(player, stateLine);

        Assertions.assertEquals(3, result);
    }

    @Test
    public void test_should_detect_1_cell_of_player_o() {
        final var stateLine = "_O_X__X_X";
        final var player = Player.O;

        final int result = Game.countPlayerCells(player, stateLine);

        Assertions.assertEquals(1, result);
    }
}