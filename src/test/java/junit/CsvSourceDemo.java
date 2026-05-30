package junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @CsvSource supplies multiple arguments per test run using comma-separated string rows.
 * Each row -> one test run. Each column -> one parameter (left-to-right).
 *
 * 1. value — basic two-column rows
 * 2. delimiter — use ';' instead of ','
 * 3. delimiterString — multi-char delimiter "::"
 * 4. nullValues — treat "NIL" as null
 * 5. emptyValue — replace "" with a placeholder string
 * 6. useHeadersInDisplayName — skip the header row
 * 7. textBlock - multi line csv
 */
public class CsvSourceDemo {
    double addAndDivide(int a, int b, int c) {
        return (double) (a + b) / c;
    }

    int multiply(int a, int b) {
        return a * b;
    }

    String addStringTogether(String a, String b) {
        return a + b;
    }

    boolean isAdult(int age) {
        return age >= 18;
    }

    // 1. value — basic two-column rows
    @ParameterizedTest(name = "[{index}] addAndDivide({0}, {1}, {2} = {3})")
    @CsvSource({
            "2, 2, 2, 2",
            "4, 4, 4, 2",
            "10, 10, 4, 5",
            "19, 8, 3, 9"
    })
    void addAndDivideShouldReturnExpectedValue(int a, int b, int c, int expected) {
        Assertions.assertEquals(expected, addAndDivide(a, b, c));
    }

    // 2. delimiter — use ';' instead of ','
    @ParameterizedTest(name = "[{index}] multiply ({0}, {1} = {2})")
    @CsvSource(delimiter = ';', value = {
            "1000; 1000; 1000000",
            "4455; 4; 17820",
            "4455; 4777; 21281535",
    })
    void multiplyShouldReturnExpectedValue(int a, int b, int expected) {
        Assertions.assertEquals(expected, multiply(a, b));
    }


    // 3. delimiterString — multi-char delimiter "::"
    @ParameterizedTest(name = "[{index}] addStringTogether(\\\"{0}\\\", \\\"{1}\\\") == \\\"{2}\\\"\")")
    @CsvSource(delimiterString = "::", ignoreLeadingAndTrailingWhitespace = false, value = {
            " I Love :: Football :: I Love  Football ",
            " I Love :: Cricket :: I Love  Cricket ",
            " I Love :: Music :: I Love  Music "
    })
    void addStringTogetherShouldReturnExpectedString(String a, String b, String expected) {
        Assertions.assertEquals(expected, addStringTogether(a, b));
    }

    // 4. nullValues — treat "NIL" as null
    @ParameterizedTest(name = "[{index}] input={0} expected={1}")
    @CsvSource(nullValues = "NIL", value = {
            "Sujon, Sujon",
            "NIL, "        // first col becomes null
    })
    void nullTokensAreConvertedToNull(String input, String expected) {
        Assertions.assertEquals(expected, input);
    }

    // 5. emptyValue — replace "" with a placeholder string
    @ParameterizedTest(name = "[{index}] input=\"{0}\"")
    @CsvSource(emptyValue = "empty", value = {
            "Sujon",
            "''"         // empty quoted cell -> becomes "EMPTY"
    })
    void emptyCellsAreReplaced(String value) {
        Assertions.assertNotNull(value);
    }

    // 6. useHeadersInDisplayName — skip the header row
    @ParameterizedTest(name = "[{index}] age = {0}, isAdult({1})")
    @CsvSource(useHeadersInDisplayName = true, value = {
            "age, expected",
            "17, false",
            "19, true"
    })
    void isAdultShouldReturnExpectedBooleanValue(int age, boolean expected) {
        Assertions.assertEquals(expected, isAdult(age));
    }

    // 7. textBlock - multi line csv
    @ParameterizedTest(name = "[{index}] addAndDivide({0}{1}{2}) = {4}")
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            a, b, c, expectedValue
            3, 4, 4, 1.75
            2, 34, 5, 7.2
            77, 34, 3, 37.0
            23, 34, 55, 1.0363636363636364
            """)
    void addAndDivideShouldReturnExpectedValueByUsingTextBlock(int a, int b, int c, double expected) {
        Assertions.assertEquals(expected, addAndDivide(a, b, c));
    }
}
