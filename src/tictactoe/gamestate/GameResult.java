package tictactoe.gamestate;

/**
 * Describes result of a game (e.g. winner or no win possible because data error).
 */
public class GameResult extends GameResultAbstract {
    public GameResult(GameStateSummary category) {
        this.summary = category;
    }

    public GameResult(GameStateSummary category, IGameStateError error) {
        this.summary = category;
        this.errors.add(error);
    }
}
