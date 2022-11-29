package tictactoe;

import tictactoe.gamestate.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Optional.empty;

/**
 * Tic-tac-toe game.
 * Rows and columns may later be used dynamically to allow a game with
 * different playground dimensions.
 */
public class Game {
    private final int playGroundRows = 3;
    private final int playGroundColumns = 3;
    private final Scanner scanner = new Scanner(System.in);

    private final static String ValidStateCharsString = "OX_";
    private IGameData gameData;

    /**
     * Instantiate Game
     */
    public Game() {
    }

    /**
     * For Test only. Instantiate Game with prepared GameData.
     *
     * @param gameData prepared GameData
     */
    public Game(GameData gameData) {
        this.gameData = gameData;
    }

    /**
     * Manages the game.
     */
    public void run(String mode) {
        //System.out.println("Begin run()");

        // get initial game state
        String gameStateLine;
        if (mode.equals("test")) {
            gameStateLine = "_________";
        } else {
            gameStateLine = cleanGameStateLine(getInitialGameState(scanner));
        }

        this.gameData = new GameData(gameStateLine);

        // print game state
        var printer = new PlayGroundPrinter(this.gameData.getGameStateSquare());
        printer.printPlayGround();

        // get first move of player x
        this.makeMove(Player.X);

        // print changed game state
        printer = new PlayGroundPrinter(this.gameData.getGameStateSquare());
        printer.printPlayGround();
        //System.out.println("End run()");
    }

    /**
     * A player makes a move.
     *
     * @param player of this move.
     */
    private void makeMove(Player player) {
        Point move = askMove(player, scanner);

        // add move data to game data
        this.gameData.addMove(move.x, move.y, player);
    }

    /**
     * Returns coordinates of a valid move.
     * Coordinates are 0 based.
     */
    protected Point askMove(Player player, Scanner scanner) {
        //System.out.println("Begin askMove()");
        int row;
        int col;
        boolean isValidMove = false;

        // loop until got correct move
        do {
            // set back values
            row = 0;
            col = 0;

            // ask user for move
            //System.out.println("Please enter move eg. 1 1 (row column)");

            Integer number = getNumberFromConsole(scanner);

            if (number != null) {
                row = number - 1; // input of user is 1..3, row will be 0..2
            } else {
                continue;
            }

            number = getNumberFromConsole(scanner);

            if (number != null) {
                col = number - 1; // input of user is 1..3, col will be 0..2
            } else {
                continue;
            }

            // check move
            isValidMove = isCoordinateWithinBounds(new Point(row, col));
            if (!isValidMove) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            isValidMove = isCellFree(row, col, this.gameData.getGameStateLine());
            if (!isValidMove) {
                System.out.println("This cell is occupied! Choose another one!");
            }
        } while (!isValidMove);

        //System.out.println("End askMove()");
        return new Point(row, col);
    }

    protected Integer getNumberFromConsole(Scanner scanner) {
        int number;
        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
        } else {
            System.out.println("You should enter numbers!");
            scanner.nextLine();
            return null;
        }
        return number;
    }

    /**
     * Returns whether cell is free.
     * Doesn't check currently whether position is out of bounds.
     *
     * @param row first row has number 0
     * @param col first col has number 0
     */
    protected boolean isCellFree(int row, int col, String gameStateLine) {
        final int position = row * playGroundRows + col;

        return gameStateLine.charAt(position) == '_';
    }

    /**
     * Returns whether move is valid
     * @param coordinate Must be 0 based.
     */
    protected boolean isCoordinateWithinBounds(Point coordinate) {
        boolean isWithin = true;

        if (coordinate.x < 0 ||
                coordinate.y < 0 ||
                coordinate.x >= playGroundRows ||
                coordinate.y >= playGroundRows) {
            isWithin = false;
        }

        return isWithin;
    }

    protected String getInitialGameState(Scanner scanner) {
        // loop until got valid game state
        String gameState;
        boolean gameStateIsValid;
        //System.out.println("Please enter an initial game state like ___XOO___");

        do {
            // ask user for game state
            if (scanner.hasNext()) {
                gameState = scanner.nextLine();
            } else {
                gameState = "_________"; // We will get such case only within tests.
            }

            // optimize input
            gameState = cleanGameStateLine(gameState);

            // check game state
            //    must contain 9 chars
            gameStateIsValid = isGameStateLineLengthValid(gameState);
            if (!gameStateIsValid) {
                System.out.println("Please enter game state with " + getCellsCount() + " characters!");
                gameState = null;
                continue;
            }

            //    must contain only valid chars
            gameStateIsValid = isGameStateConsistsOfValidChars(gameState);
            if (!gameStateIsValid) {
                System.out.println("Please enter game state which contains characters  " + ValidStateCharsString + " only!");
                gameState = null;
            }
        } while (gameState == null);

        return gameState;
    }

    protected boolean isGameStateConsistsOfValidChars(String gameState) {
        int correctChars = 0;
        for (char currentStateChar : gameState.toCharArray()) {
            for (char currentAllowedChar : ValidStateCharsString.toCharArray()) {
                if (currentStateChar == currentAllowedChar) {
                    correctChars++;
                }
            }
        }

        return correctChars == gameState.length();
    }

    /**
     * Returns whether game state has correct number of characters.
     */
    protected boolean isGameStateLineLengthValid(String gameState) {
        return gameState.length() == getCellsCount();
    }

    /**
     * Get count of all cells of this game. Calculates the value from
     * expected rows and columns.
     *
     * @return cells count
     */
    private int getCellsCount() {
        return playGroundRows * playGroundColumns;
    }

    /**
     * Get game state as a 2 dimensional representation.
     *
     * @param state may be invalid.
     * @return game state
     */
    public static char[][] getGameStateSquare(String state, int fieldCount) {
        var resultState = new char[3][3];

        boolean stateValid = Game.isStateLineRepresentableAsSquare(state, fieldCount);

        if (!stateValid) {
            throw new IllegalArgumentException(String.format("Game state '%s' is invalid.", state));
        }

        var stateChars = state.toCharArray();

        for (int row = 0; row < 3; row++) {
            System.arraycopy(stateChars, row * 3, resultState[row], 0, 3);
        }

        return resultState;
    }

    /**
     * Returns whether state is valid for the game.
     * (Currently we don't check whether count of state chars of both players is valid.)
     *
     * @param state      to be checked.
     * @param fieldCount is expected count of fields of the play ground.
     */
    protected static boolean isStateLineRepresentableAsSquare(String state, int fieldCount) {
        if (state == null) return false;
        if (state.length() != fieldCount) return false;   // must contain 9 valid chars for a 3x3 play field

        // must contain only _, X, O (letter)
        for (String character : state.split("")) {
            if (!ValidStateCharsString.contains(character)) {
                return false;
            }
        }

        // todo ?? check whether count of state chars of both players is valid.

        return true;
    }

    /**
     * Prepare state line for validity check.
     *
     * @return state which contains supported characters in uppercase only
     */
    protected static String cleanGameStateLine(final String stateLine) {
        return stateLine.trim()
                .toUpperCase();
    }

    public String stateToString(char[][] state) {
        var result = new StringBuilder();

        for (int row = 0; row < playGroundRows; row++) {
            for (int col = 0; col < playGroundColumns; col++) {
                result.append(state[row][col]);
            }
        }

        return result.toString();
    }

    public void printGameStateSquare() {
        System.out.println(this.gameData.getGameStateLine());
    }

    /**
     * Print result of the game, e.g. winner, stalemate, erroneous state
     */
    public void printGameResult() {
        IGameResult gameResult = getGameResult(this.gameData);

        // check game results
        var stateCategory = gameResult.getGameStateSummary();
        switch (stateCategory) {
            case XWins -> System.out.println("X wins");
            case OWins -> System.out.println("O wins");
            case Draw -> System.out.println("Draw");
            case NotFinished -> System.out.println("Game not finished");
            case Impossible -> System.out.println("Impossible");
            case Unknown -> System.out.println("Error: Game state shouldn't be Unknown");
            default -> System.out.println("Error: Unexpected game state category");
        }
    }

    /**
     * Get result of the game, e.g. Who wins, stalemate, error of current state.
     */
    private IGameResult getGameResult(final IGameState gameState) {
        Optional<IGameResult> result;
        // checkCountOfCells // todo later?

        // find invalid states of cell count
        result = invalidatePlayerCellsCount(gameState.getGameStateLine());
        if (result.isPresent()) return result.get();

        // Check win state of game
        result = getWinState(gameState); //x, o, draw, GameNotFinished, erroneous state
        if (result.isPresent()) return result.get();

        return new GameResult(GameStateSummary.Unknown); // is error
    }

    /**
     * Return information about winner, stalemate, game end without winner, erroneous state.
     */
    // todo We don't seem to need an Optional. Remove!
    private Optional<IGameResult> getWinState(IGameState gameState) {
        var gameStateError = checkPlayerCellsCountDifference(gameState);
        if (gameStateError.isPresent()) {
            return Optional.of(new GameResult(GameStateSummary.Impossible,
                    gameStateError.get()
            ));
        }

        // calc winners
        int xWinsCount = getWinLinesCount(gameState, Player.X);
        int oWinsCount = getWinLinesCount(gameState, Player.O);

        if (xWinsCount + oWinsCount > 1) {
            return Optional.of(new GameResult(GameStateSummary.Impossible,
                    new GameStateError(GameStateErrorType.TooManyWinLines,
                            "Too many win lines"))
            );
        }

        if (xWinsCount == 1) {
            return Optional.of(new GameResult(GameStateSummary.XWins));
        }

        if (oWinsCount == 1) {
            return Optional.of(new GameResult(GameStateSummary.OWins));
        }

        final var stateLine = gameState.getGameStateLine();

        // calc ongoing game
        if (!stateLine.contains("_")) {
            return Optional.of(new GameResult(GameStateSummary.Draw));
        } else {
            return Optional.of(new GameResult(GameStateSummary.NotFinished));
        }
    }

    /**
     * Tells how many win lines player got.
     *
     * @return Count of win lines.
     */
    private int getWinLinesCount(IGameState gameState, Player player) {
        var linesCount = new AtomicInteger();

        final char cellChar =  player.equals(Player.X) ? gameState.getPlayerXStateCharacter()
                : gameState.getPlayerOStateCharacter();

        final var workLine = gameState.getGameStateLine().replaceAll(String.valueOf(cellChar), "#");

        var winPatterns = new ArrayList<String>();
        winPatterns.add("...###..."); // horizontal lines
        winPatterns.add("###......");
        winPatterns.add("......###");
        winPatterns.add("#..#..#.."); // vertical lines
        winPatterns.add(".#..#..#.");
        winPatterns.add("..#..#..#");
        winPatterns.add("#...#...#"); // diagonal lines
        winPatterns.add("..#.#.#..");

        winPatterns.forEach(pattern -> {
            if (workLine.matches(pattern)) {
                linesCount.set(linesCount.get() + 1);
            }
        });

        return linesCount.get();
    }

    /**
     * Tell whether cells count of the player is wrong.
     *
     * @return empty if no error found
     */
    private Optional<IGameStateError> checkPlayerCellsCountDifference(IGameState gameState) {
        int xCells = countPlayerCells(Player.X, gameState.getGameStateLine());
        int oCells = countPlayerCells(Player.O, gameState.getGameStateLine());

        if (Math.abs(xCells - oCells) > 1) {
            GameStateError error;
            if (xCells > oCells) {
                error = new GameStateError(GameStateErrorType.TOO_MANY_X_CELLS,
                        "Too many x cells");
            } else {
                error = new GameStateError(GameStateErrorType.TOO_MANY_O_CELLS,
                        "Too many o cells");
            }
            return Optional.of(error);
        }

        return Optional.empty();
    }

    /**
     * Checks whether cells of each player have incorrect count.
     *
     * @return Optional.Empty: no error in relation to cells found, GameResult: Cell count is wrong.
     * ({@link GameStateSummary} is additionally set to GameStateCategory.Impossible.)
     */
    private Optional<IGameResult> invalidatePlayerCellsCount(String stateLine) {
        int xCellCount = Game.countPlayerCells(Player.X, stateLine);
        int oCellCount = Game.countPlayerCells(Player.O, stateLine);

        // Wrong player cells difference
        if (Math.abs(xCellCount - oCellCount) > 1) {
            return Optional.of(new GameResult(GameStateSummary.Impossible));
        }
        return empty();
    }

    /**
     * Get count of all cells of a player.
     */
    protected static int countPlayerCells(Player player, String gameStateLine) {
        final var characters = Arrays.stream(gameStateLine.split(""));
        return (int) characters.filter(c -> c.equalsIgnoreCase(player.name())).count();
    }
}
