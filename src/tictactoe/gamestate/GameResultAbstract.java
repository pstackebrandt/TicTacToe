package tictactoe.gamestate;

import java.util.ArrayList;

abstract class GameResultAbstract implements IGameResult {
    public final ArrayList<IGameStateError> errors = new ArrayList<>();
    public GameStateSummary summary = GameStateSummary.Unknown;

    public GameStateSummary getGameStateSummary() {
        return this.summary;
    }

    /**
     * It's a summary
     * of the current game state. Someone who changes this, doesn't change
     * the state of the game but the result of the evaluation of the game state.
     *
     * @param gameStateSummary New game state category.
     */
    // Should we better always get the GameStateCategory on the fly? Without to save it?
    public void SetGameStateSummary(GameStateSummary gameStateSummary) {
        if (gameStateSummary != null) {
            this.summary = gameStateSummary;
        }
    }

    public ArrayList<IGameStateError> GetErrors() {
        return errors;
    }

    public void AddError(IGameStateError error) {
        if (error != null) {
            this.errors.add(error);
        }
    }
}
