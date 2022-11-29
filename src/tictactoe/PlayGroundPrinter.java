package tictactoe;

/**
 * PlayGround creates a visual representation of the play ground.
 * It can print the play ground.
 */
public class PlayGroundPrinter {
    private final char playerXIcon = 'X'; // todo unused
    private final char playerOIcon = 'O'; // todo unused
    private final char emptyFieldIcon = '_';  // todo unused
    private final char horizontalBorderIcon = '-';
    private final char verticalBorderIcon = '|';
    private final char fieldSeparatorIcon = ' ';
    private final char borderSeparatorIcon = fieldSeparatorIcon;

    /**
     * Count of characters required in each line for border. Includes spaces.
     */
    private final int countOfDecorativeCharsAtVerticalBorders = 4;

    private char[][] gameState;

    /**
     * Constructor, save game state.
     */
    public PlayGroundPrinter(char[][] gameState) {
        this.gameState = gameState;
    }

    /**
     * Update game state of play ground.
     */
    // todo unused
    public void updateGameState(char[][] gameState) {
        this.gameState = gameState;
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

        for (int row = 0; row < getRowsCount(); row++) {
            result.append("" + verticalBorderIcon + borderSeparatorIcon);
            for (int col = 0; col < getColsCount(); col++) {

                // append space as delimiter between field icons
                if (col != 0) {
                    result.append(fieldSeparatorIcon);
                }

                result.append(gameState[row][col]);
            }
            result.append("" + borderSeparatorIcon + verticalBorderIcon)
                    .append(System.lineSeparator());
        }

        result.append(getHorizontalBorderIcon());

        return result.toString();
    }

    private String getHorizontalBorderIcon() {
        return ("" + horizontalBorderIcon).repeat(getHorizontalBorderLength());
    }

    /**
     * A game with 3 horizontal fields requires 9 characters incl. decorative chars.
     * ---------, 4 f: 11
     */
    protected int getHorizontalBorderLength() {
        return countOfDecorativeCharsAtVerticalBorders + 2 * getColsCount() - 1;
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
