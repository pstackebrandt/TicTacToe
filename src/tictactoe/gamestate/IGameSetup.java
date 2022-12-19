package tictactoe.gamestate;

/**
 * Makes setup data of a game available, especially size of playground.
 * This data describes a game AND must be available at game start.
 */
public interface IGameSetup {
    int getCellsCount();
}
