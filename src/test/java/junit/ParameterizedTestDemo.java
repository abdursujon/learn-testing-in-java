package junit;

import com.learn_testing_in_java.ArrayManipulation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

/**
 * Demo of JUnit 5 @ParameterizedTest annotation attributes.
 * <p>
 * 1. name               - custom display name template for each run
 * 2. {index}            - placeholder for the run number (1-based)
 * 3. {0}, {1}, ...      - placeholders for the arguments passed in that run
 * 4. {arguments}        - placeholder for ALL arguments joined as a string
 * 5. {displayName}      - placeholder for the original method display name
 * 6. autoCloseArguments - whether AutoCloseable args are closed after each run (default true)
 * <p>
 * Must be paired with a source annotation:
 *
 * @ValueSource, @CsvSource, @CsvFileSource, @MethodSource,
 * @EnumSource, @NullSource, @EmptySource, @NullAndEmptySource, @ArgumentsSource
 */
public class ParameterizedTestDemo {

    private ArrayManipulation arrayManipulation = new ArrayManipulation();

    static Stream<Arguments> provideSearchCases() {
        return Stream.of(
                // nums, target, expected
                Arguments.of(new int[]{1, 3, 5, 6}, 7, 4),
                Arguments.of(new int[]{1, 6, 7, 9}, 2, 1),
                Arguments.of(new int[]{1, 6, 7, 9, 89}, 199, 5)
        );
    }

    @ParameterizedTest(name = "[{index}] searchInsert(nums={0}, target={1}): expected insertion index = {2}")
    @MethodSource("provideSearchCases")
    void shouldReturnExpectedIndexForValidTarget(int[] nums, int target, int expectedIndex) {
        Assertions.assertEquals(expectedIndex, arrayManipulation.searchInsert(nums, target));
    }

    static Stream<Arguments> provideProfitArray() {
        return Stream.of(
                // profit array
                Arguments.of(new int[]{1, 4, 56, 6}, 55),
                Arguments.of(new int[]{4, 4, 56, 89, 6}, 85),
                Arguments.of(new int[]{-5, -5, -6, -8, 3}, 11)
        );
    }

    @ParameterizedTest(name = "[{index}] maxProfit(prices={0}): expected max profit = {1}")
    @MethodSource("provideProfitArray")
    void shouldReturnExpectedMaxProfit(int[] prices, int expectedProfit) {
        Assertions.assertEquals(expectedProfit, arrayManipulation.maxProfit(prices));
    }

    static Stream<Arguments> moveZeroesArrayProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 4, 0, 0, 56, 6}, new int[]{1, 4, 56, 6, 0, 0}),
                Arguments.of(new int[]{0, 0, 0, 4, 4, 56, 89, 6}, new int[]{4, 4, 56, 89, 6, 0, 0, 0}),
                Arguments.of(new int[]{0, -5, -5, -6, 0, -8, 3, 0}, new int[]{-5, -5, -6, -8, 3, 0, 0, 0})
        );
    }

    @ParameterizedTest(name = "[{index}] moveZeroes(nums={0}): expected new array = {1}")
    @MethodSource("moveZeroesArrayProvider")
    void shouldReturnNewArrayItemsWithMoveZeroesAtTheEnd(int[] nums, int[] expectedArray) {
        arrayManipulation.moveZeroes(nums);
        Assertions.assertArrayEquals(expectedArray, nums);
    }

    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    public double squareNumber(double n) {
        return n * n;
    }


    public String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }


    // 1. name, 2. {index}, 3.{0}, {1}
    @ParameterizedTest(name = "[{index}] isEven({0}) == true")
    @ValueSource(ints = {2, 888, 34, 66})
    void isEvenShouldReturnTrueForEvenNumbers(int n) {
        Assertions.assertTrue(isEven(n));
    }


    // Uses: 1. name, 2. {index}, 4. {arguments}
    @ParameterizedTest(name = "Run {index} with args = {arguments}")
    @ValueSource(doubles = {1, 3, 5, -1, -2, -5})
    void isSquareNumberShouldNeverReturnNegativeNumber(double n) {
        Assertions.assertTrue(squareNumber(n) >= 0);
    }


    @ParameterizedTest(name = "[{index}] squareNumber({0}) == {0} * {0}")
    @CsvSource({
            "4.4, 19.36",
            "2.2, 4.84"
    })
    void squareNumberShouldReturnExpectedValue(double input, double expected) {
        // assertEquals(expected, actual, delta) — the 0.0001 is a tolerance because comparing double exactly is unsafe (floating-point rounding)
        Assertions.assertEquals(expected, squareNumber(input), 0.0001);
    }


    // Uses: 1. name, 3. {0} and {1} (two args from @CsvSource), 5. {displayName}
    @ParameterizedTest(name = "[{index}] reverseString({0} reverse = {1})")
    @CsvSource({
            "Java , avaJ",
            "madam, madam",
            "cute, etuc"
    })
    void reverseStringShouldReturnValidReverseStrings(String input, String expected) {
        Assertions.assertEquals(expected, reverseString(input));
    }

    // Uses: 6. autoCloseArguments (set to false so AutoCloseable args won't be auto-closed),
    //       7. quoteTextArguments (set to false so the String "{0}" prints WITHOUT surrounding quotes)
    @ParameterizedTest(
            name = "[{index}] raw arg without quotes -> {0}",
            autoCloseArguments = false // only matters when using stream to read files
    )
    @ValueSource(strings = {"apple", "orange", "Junit"})
    void reverseStringPreservedLength(String input) {
        Assertions.assertEquals(input.length(), reverseString(input).length());
    }
}
