package tictactoe;

/**
 * PlayGround creates a visual representation of the play ground.
 * It can print the play ground.
 */
public class PlayGroundPrinter {
    private static final char EMPTY_FIELD_ICON = ' ';
    private static final char HORIZONTAL_BORDER_ICON = '-';
    private static final char VERTICAL_BORDER_ICON = '|';
    private static final char FIELD_SEPARATOR_ICON = ' ';
    private static final char BORDER_SEPARATOR_ICON = FIELD_SEPARATOR_ICON;

    /**
     * Count of characters required in each line for border. Includes spaces.
     */
    private static final int COUNT_OF_DECORATIVE_CHARS_AT_VERTICAL_BORDERS = 4;
    private final char emptyFieldCharacterOfGameState;

    private final char[][] gameState;

    /**
     * Constructor, save game state.
     *
     * @param gameState                      Describes the game state.
     * @param emptyFieldCharacterOfGameState Character which is used within game state to mark empty field.
     */
    public PlayGroundPrinter(char[][] gameState, char emptyFieldCharacterOfGameState) {
        this.gameState = gameState;
        this.emptyFieldCharacterOfGameState = emptyFieldCharacterOfGameState;
    }

    /**
     * Print play ground of current state.
     */
    public void printPlayGround() {
        System.out.println(getPlayGround());
    }

    /**
     * Get formatted play ground.
     *
     * @return formatted play ground, includes line separators
     */
    private String getPlayGround() {
        var result = new StringBuilder();

        result.append(getHorizontalBorderIcon())
                .append(System.lineSeparator());

        final int firstCol = 0;
        final int firstRow = 0;

        for (int row = firstRow; row < getRowsCount(); row++) {
            result.append("" + VERTICAL_BORDER_ICON + BORDER_SEPARATOR_ICON);
            for (int col = firstCol; col < getColsCount(); col++) {

                // append space as delimiter between field icons
                if (col != firstCol) {
                    result.append(FIELD_SEPARATOR_ICON);
                }

                result.append(replaceByStateCharacterForPrint(gameState[row][col]));
            }
            result.append("" + BORDER_SEPARATOR_ICON + VERTICAL_BORDER_ICON)
                    .append(System.lineSeparator());
        }

        result.append(getHorizontalBorderIcon());

        return result.toString();
    }

    /**
     * Replace state character from game state by corresponding character for print.
     */
    private char replaceByStateCharacterForPrint(char state) {
        if (state == emptyFieldCharacterOfGameState) {
            state = EMPTY_FIELD_ICON;
        }
        return state;
    }

    private String getHorizontalBorderIcon() {
        return ("" + HORIZONTAL_BORDER_ICON).repeat(getHorizontalBorderLength());
    }

    /**
     * A game with 3 horizontal fields requires 9 characters incl. decorative chars.
     * ---------, 4 f: 11
     */
    protected int getHorizontalBorderLength() {
        return COUNT_OF_DECORATIVE_CHARS_AT_VERTICAL_BORDERS + 2 * getColsCount() - 1;
    }

    /**
     * Count of game columns.
     */
    public int getColsCount() {
        return gameState != null && gameState[0] != null
                ? gameState[0].length
                : 0;
    }

    /**
     * Count of game rows.
     */
    public int getRowsCount() {
        return gameState != null ? gameState.length : 0;
    }
}
