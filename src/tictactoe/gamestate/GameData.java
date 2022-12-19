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
        this.currentPlayer = Player.X;
    }

    public GameData(String stateLine, Player startPlayer) {
        this(stateLine);
        this.currentPlayer = startPlayer;
    }

    /**
     * Describes each cell of the play ground. Contains no formatting.
     */
    private String stateLine;

    private Player currentPlayer;

    public int getCellsCount() {
        return stateLine.length();
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Set player who plays his turn next.
     */
    @Override
    public void setCurrentPlayer(Player nextPlayer) {
        if (this.currentPlayer != nextPlayer){
            this.currentPlayer = nextPlayer;
        }
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
        if (player == Player.X) {
            return getPlayerXStateCharacter();
        }
        return getPlayerOStateCharacter();
    }
}
