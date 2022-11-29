package tictactoe.gamestate;

/**
 * Describes current state of a game. It's a temporary state.
 * It will be changed or extended at every turn of game.
 */
public interface IGameState {
    /** Get game state as a single string. example: __o_x___x */
    String getGameStateLine();

    /** Get game state as an array of arrays.*/
    char[][] getGameStateSquare();

    /**
     * Get the character which marks cells of player X in a game state.
     */
    char getPlayerXStateCharacter();

    /**
     * Get the character which marks cells of player O (Letter) in a game state.
     */
    char getPlayerOStateCharacter();


    /**
     * Get the character which marks empty cells in a game state.
     */
    char getEmptyCellStateCharacter();
}
