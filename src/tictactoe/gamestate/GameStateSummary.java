package tictactoe.gamestate;

/**
 * Possible states of a game.
 */
public enum GameStateSummary {
    XWins,
    OWins,
    Draw,
    /** The game goes on. */
    NotFinished,
    /** There is something wrong with the state of the game. */
    Impossible,
    /** Current state is not known.*/
    Unknown
}
