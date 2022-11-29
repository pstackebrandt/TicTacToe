package tictactoe.gamestate;

import java.util.ArrayList;

/**
 * Describes the current or final result of a game.
 */
public interface IGameResult {
    GameStateSummary getGameStateSummary();

    void SetGameStateSummary(GameStateSummary gameStateSummary);

    ArrayList<IGameStateError> GetErrors();

    void AddError(IGameStateError error);
}

