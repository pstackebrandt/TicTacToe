package tictactoe;

import java.util.HashMap;
import java.util.Map;

public class ArgsUtil {
    private ArgsUtil() {
    }

    /** Return all arguments in a map as pairs of key and value.
     * Splits a string which contains a key-value-pair into key and value.
     * Delimiter of key and value is the first "=" character. A string without the
     * delimiter is returned as key with value null. */
    public static Map<String, String> argsToMap(String[] args) {
        final Map<String, String> argsMap = new HashMap<>();
        for (String arg : args) {
            final String[] parts = arg.split("=", 2);
            final String key = parts[0].trim();
            String value;
            if (parts.length > 1 && parts[1] != null) {
                value = parts[1].trim();
            } else {
                value = null;
            }
            argsMap.put(key, value);
        }
        return argsMap;
    }
}
