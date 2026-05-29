package junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/**
 * Demo of JUnit 5 @ValueSource attributes.
 *
 * @ValueSource supplies one argument per test run, of different one single simple type.
 * We must use exactly one of these attributes per annotation.
 * <p>
 * 1. shorts    - short[]
 * 2. bytes     - byte[]
 * 3. ints      - int[]
 * 4. longs     - long[]
 * 5. floats    - float[]
 * 6. doubles   - double[]
 * 7. chars     - char[]
 * 8. booleans  - boolean[]
 * 9. strings   - String[]
 * 10. classes   - Class<?>[]
 */
public class ValueSourceDemo {

    // 1. shorts
    short spendAsEarned(short n) {
        return n -= n;
    }

    @ParameterizedTest(name = "[{index}] spendAsEarned({0}) always == 0")
    @ValueSource(shorts = {33, 55, 66, 66})
    void spendAsEarnedShouldAlwaysReturnZero(short n) {
        Assertions.assertEquals(0, spendAsEarned(n));
    }


    // 2. bytes
    byte turnIntoNegativeByte(int n) {
        return (byte) -n;
    }

    @ParameterizedTest(name = "[{index}] turnIntoNegativeByte({0}) always == -n")
    @ValueSource(ints = {33, 55, 66, 66})
    void turnIntoNegativeByteShouldAlwaysReturnNegativeBytes(int n) {
        Assertions.assertEquals(-n, turnIntoNegativeByte(n));
    }


    // 3. ints
    String checkAge(int age) {
        if (age >= 18) return "Go ahead have some drinks";
        else return "I think you should go home";
    }

    @ParameterizedTest(name = "[{index}] checkAge({0}) should return correct string")
    @ValueSource(ints = {2, 7, 9, 33, 55, 66, 66})
    void checkAgeShouldReturnExpectedResponse(int age) {
        checkAge(age);
        if (age >= 18) {
            Assertions.assertEquals("Go ahead have some drinks", checkAge(age));
        } else Assertions.assertEquals("I think you should go home", checkAge(age));
    }

    // 4. longs
    long differenceBetweenTwoTopPopulatedCountry(long india) {
        long china = 1_404_890_000L;
        return india - china;
    }

    @ParameterizedTest(name = "[{index}] differenceBetweenTwoTopPopulatedCountry({0}) should return correct difference")
    @ValueSource(longs = {1_417_492_000L})
    void differenceBetweenTwoTopPopulatedCountryShouldReturnExpectedDifference(long india) {
        long diff = differenceBetweenTwoTopPopulatedCountry(india);
        Assertions.assertEquals(12602000L, diff);
    }


    // 5. floats
    int totalCost(float cost) {
        int items = 10;
        return (int) (items * cost);
    }

    @ParameterizedTest(name = "[{index}] totalCost({0}) should return correct price == true")
    @ValueSource(floats = {300, 22, 44, 55, 13, 5})
    void totalCostShouldReturnExpectedCost(float cost) {
        Assertions.assertEquals(cost * 10, totalCost(cost));
    }


    // 6. doubles
    int bestDayToSell(double stockPrice) {
        int[] days = {(int) stockPrice, (int) stockPrice * (int) .2, (int) stockPrice * 3, (int) stockPrice * (int) 2.2};
        int index = 0;
        int max = days[0];
        for (int i = 1; i < days.length; i++) {
            if (days[i] > max) {
                max = days[i];
                index = i;
            }
        }
        return index;
    }

    @ParameterizedTest(name = "[{index}]  bestDayToSell({0}) should return correct day")
    @ValueSource(doubles = {300.5d, 22, 44, 55, 13, 5})
    void bestDayToSellShouldReturnExpectedDay(double price) {
        Assertions.assertEquals(2, bestDayToSell(price));
    }


    // 7. chars
    char toUpperChar(char c) {
        return Character.toUpperCase(c);
    }

    @ParameterizedTest(name = "[{index}] toUpperChar('{0}') is uppercase")
    @ValueSource(chars = {'a', 'b', 'z'})
    void toUpperCharReturnsUppercase(char c) {
        Assertions.assertEquals(Character.toUpperCase(c), toUpperChar(c));
    }


    // 8. booleans
    boolean negateBoolean(boolean b) {
        return !b;
    }

    @ParameterizedTest(name = "[{index}] negateBoolean({0}) == !{0}")
    @ValueSource(booleans = {true, false})
    void negateBooleanFlipsValue(boolean b) {
        Assertions.assertEquals(!b, negateBoolean(b));
    }


    // 9. strings
    String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    @ParameterizedTest(name = "[{index}] reverseString(\"{0}\") same length, different order")
    @ValueSource(strings = {"Sujon", "Java", "JUnit"})
    void reverseStringPreservesLength(String s) {
        Assertions.assertEquals(s.length(), reverseString(s).length());
    }


    // 10. classes
    boolean isNumberSubtype(Class<?> clazz) {
        return Number.class.isAssignableFrom(clazz);
    }

    @ParameterizedTest(name = "[{index}] isNumberSubtype({0}) == true")
    @ValueSource(classes = {Integer.class, Double.class, Long.class, Float.class, Byte.class})
    void numericWrapperIsNumberSubtype(Class<?> clazz) {
        Assertions.assertTrue(isNumberSubtype(clazz));
    }
}
