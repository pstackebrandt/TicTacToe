package tictactoe.gamestate;

/**
 * Describes error of a game state.
 */
public class GameStateError implements IGameStateError {
    private GameStateErrorType type = null;
    private String errorMessage = null;

    public GameStateError(GameStateErrorType type, String errorMessage) {
        this.type = type;
        this.errorMessage = errorMessage;
    }
}
