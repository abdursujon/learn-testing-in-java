package junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MethodSourceDemo {

    static Stream<String> provideBlankStrings(){
        return Stream.of(null, "", "  ", "\t", "\n");
    }

    @ParameterizedTest(name = "[{index}] provideBlankStrings(input =\"{0}\") is blank")
    @MethodSource("provideBlankStrings")
    void returnsTrueForBlankStrings(String input){
        assertTrue(input == null || input.trim().isEmpty());
    }

    static Stream<Arguments> provideDivisionCases(){
        return Stream.of(
                Arguments.of(4, 3, 1),
                Arguments.of(8, 2, 4),
                Arguments.of(2, 3, 0)
        );
    }

    public int divide(int a, int b){
        return  a / b;
    }

    @ParameterizedTest(name ="[{index}] divide(input =\"{0} / {1} returns {2}\")")
    @MethodSource("provideDivisionCases")
    void returnsExpectedDivision(int a, int b, int expectedValue){
        assertEquals(expectedValue, divide(a, b));
    }

}
