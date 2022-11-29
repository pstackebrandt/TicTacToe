package tictactoe.gamestate;

import tictactoe.Player;

/**
 * All data which describes the game.
 */
public interface IGameData extends IGameResult, IGameState, IGameSetup {
    /** Add a valid move.
     * Throws an error if the move isn't valid. */
    void addMove(int row, int col, Player player);
}
