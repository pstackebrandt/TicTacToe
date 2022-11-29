package tictactoe.gamestate;

import tictactoe.Game;
import tictactoe.Player;

/**
 * Holds information about the state of a game.
 * Row and column start with 0 in this class.
 */
public class GameData extends GameResultAbstract implements IGameData {

    public GameData(String stateLine) {
        this.stateLine = stateLine;
    }

    /**
     * Describes each cell of the play ground. Contains no formatting.
     */
    private String stateLine;

    public int getCellsCount() {
        return stateLine.length();
    }

    public int getPlayGroundRowsCount() {
        return 3;
    }

    public int getPlayGroundColumnsCount() {
        return 3;
    }

    public String getGameStateLine() {
        return stateLine;
    }

    public char[][] getGameStateSquare() {
        return Game.getGameStateSquare(this.stateLine, getCellsCount());
    }

    public char getPlayerXStateCharacter() {
        return 'X';
    }

    public char getPlayerOStateCharacter() {
        return 'O';
    }


    public char getEmptyCellStateCharacter() {
        return '_';
    }

    /**
     * Add a valid move.
     * Throws an error if the move isn't valid.
     *
     * @param row First row has index 0.
     * @param col First column has index 0.
     */
    @Override
    public void addMove(int row, int col, Player player) {
        final char playerChar = getPlayerCharacter(player);

        var builder = new StringBuilder(stateLine);
        builder.setCharAt(row * 3 + col, playerChar);

        stateLine = builder.toString();
    }

    public char getPlayerCharacter(Player player) {
        // TODO add test
        if (player == Player.X) {
            return getPlayerXStateCharacter();
        }
        return getPlayerOStateCharacter();
    }
}
