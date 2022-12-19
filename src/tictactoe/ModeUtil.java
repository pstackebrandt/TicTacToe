package tictactoe;

import java.util.Map;

public class ModeUtil {

    private ModeUtil() {
    }

    /**
     * Get mode from args or default mode.
     */
    public static String getModeOrDefault(Map<String, String> argsMap) {
        String mode = "lesson";
        if (argsMap.containsKey("mode")) {
            mode = argsMap.get("mode");
        }
        return mode;
    }
}
