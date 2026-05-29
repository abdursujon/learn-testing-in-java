package junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo of JUnit 5 Assertions methods.
 * <p>
 * 1. assertEquals(expected, actual) - check two values are equal
 * 2. assertNotEquals(unexpected, actual) - check two values are NOT equal
 * 3. assertTrue(condition) - check condition is true
 * 4. assertFalse(condition) - check condition is false
 * 5. assertNull(object) - check object is null
 * 6. assertNotNull(object) - check object is NOT null
 * 7. assertArrayEquals(expectedArray, actualArray) - compare arrays
 * 8. assertIterableEquals(expected, actual) - compare collections
 * 9. assertSame(expected, actual) - check both references point to SAME object
 * 10. assertNotSame(unexpected, actual) - check references are DIFFERENT objects
 * 11. assertThrows(exceptionType, executable) - check code throws expected exception
 * 12. assertDoesNotThrow(executable) - check code does NOT throw any exception
 * 13. assertTimeout(duration, executable) - check code finishes within time
 * 14. assertAll(executables...) - group multiple assertions, runs ALL even if one fails
 * 15. fail(message) - immediately fail the test with a message
 */
public class AssertionsDemo {
    @Test
    void assertionsDemo() {
        int sum = 3 + 7;
        int mul = 5 * 2;
        String s = null;
        String s2 = "Sujon";
        int[] nums1 = {1, 3, 5, 7};
        int[] nums2 = {1, 3, 5, 7};
        List<Integer> list1 = new ArrayList<>(List.of(1, 45, 89));
        List<Integer> list2 = new ArrayList<>(List.of(1, 45, 89));
        List<Integer> list3 = list2;

        // 1. assertEquals(expected, actual) - check two values are equal
        Assertions.assertEquals(10, sum);

        // 2. assertNotEquals(unexpected, actual) - check two values are NOT equal
        Assertions.assertNotEquals(10.5, sum);

        // 3. assertTrue(condition) - check condition is true
        Assertions.assertTrue(sum == mul);

        // 4. assertFalse(condition) - check condition is false
        Assertions.assertFalse(sum > mul);

        // 5. assertNull(object) - check object is null
        Assertions.assertNull(s);

        // 6. assertNotNull(object) - check object is NOT null
        Assertions.assertNotNull(sum);

        // 7. assertArrayEquals(expectedArray, actualArray) - compare arrays
        Assertions.assertArrayEquals(nums1, nums2);

        // 8. assertIterableEquals(expected, actual) - compare collections
        Assertions.assertIterableEquals(list1, list2);

        // 9. assertSame(expected, actual) - check both references point to SAME object
        Assertions.assertSame(list2, list3);

        // 10. assertNotSame(unexpected, actual) - check references are DIFFERENT objects
        Assertions.assertNotSame(list1, list2);

        // 11. assertThrows(exceptionType, executable) - check code throws expected exception
        Assertions.assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        });

        // 12. assertDoesNotThrow(executable) - check code does NOT throw any exception
        Assertions.assertDoesNotThrow(() -> {
            int result = 10 / 2;
        });

        // 13. assertTimeout(duration, executable) - check code finishes within time
        Assertions.assertTimeout(java.time.Duration.ofSeconds(1), () -> {
            Thread.sleep(500);
        });
        // 14. assertAll(executables...) - group multiple assertions, runs ALL even if one fails
        Assertions.assertAll(s2,
                () -> Assertions.assertEquals("Sujon", "Sujon"),
                () -> Assertions.assertTrue(mul == sum)
        );
        // 15. fail(message) - immediately fail the test with a message
        if (sum > 10) Assertions.fail("Max Sum Allowed is 10");
    }
}
