package tictactoe;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgsUtilTest {
    @Nested
    class argsToMap {

        @Test
        void should_return_key_and_value() {
            final String[] args = {"key0", "key1=value1"};
            final String expectedValue = "value1";

            var argsMap = ArgsUtil.argsToMap(args);
            final String actualValue = argsMap.get("key1");

            assertEquals(expectedValue, actualValue);
        }

        @Test
        void should_return_value_null_if_no_value_found() {
            final String[] args = {"key0", "key1=value1"};

            var argsMap = ArgsUtil.argsToMap(args);
            final String actualValue = argsMap.get("key0");

            assertNull(actualValue);
        }

        @Test
        void should_use_only_fist_occurrence_of_equal_sign_as_delimiter_of_key_value_pair() {
            final String[] args = {"key0=value0=10"};
            final String expectedValue = "value0=10";

            var argsMap = ArgsUtil.argsToMap(args);
            final String actualValue = argsMap.get("key0");

            assertEquals(expectedValue, actualValue);
        }
    }
}
