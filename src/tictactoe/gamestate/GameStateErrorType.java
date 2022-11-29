package tictactoe.gamestate;

/**
 * Types of errors which can be found in a game state.
 */
public enum GameStateErrorType {
    TOO_MANY_X_CELLS,
    TOO_MANY_O_CELLS,
    TooManyXWinLines, // todo unused
    TooManyWinLines,
    TooManyYWinLines, // todo unused
    WrongCellCount, // todo unused
    WrongCharacter // todo unused
}
