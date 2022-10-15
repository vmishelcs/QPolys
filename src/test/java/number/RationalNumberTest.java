package number;

import exceptions.RationalNumberDivideByZeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RationalNumberTest {
    @Test
    @DisplayName("Initialize")
    void testInitialize() {
        int numerator = 1;
        int denominator = 2;
        RationalNumber r = new RationalNumber(numerator, denominator);
        assertAll(() -> assertEquals(numerator, r.getNumerator()),
                () -> assertEquals(denominator, r.getDenominator()));
    }

    @Test
    @DisplayName("Initialize and reduce")
    void testInitializeAndReduce() {
        int numerator = 1173;
        int denominator = 345;
        RationalNumber r = new RationalNumber(numerator, denominator);
        int gcd = greatestCommonDivisor(numerator, denominator);
        int numeratorReduced = numerator / gcd;
        int denominatorReduced = denominator / gcd;
        assertAll(() -> assertEquals(numeratorReduced, r.getNumerator()),
                () -> assertEquals(denominatorReduced, r.getDenominator()));
    }

    @Test
    @DisplayName("Initialize with negative integers")
    void testInitializeWithNegativeIntegers() {
        int numerator = 1173;
        int denominator = 345;

        RationalNumber r1 = new RationalNumber(numerator, denominator);
        RationalNumber r2 = new RationalNumber(-numerator, -denominator);
        assertEquals(r1, r2);

        RationalNumber r3 = new RationalNumber(-numerator, denominator);
        RationalNumber r4 = new RationalNumber(numerator, -denominator);
        assertEquals(r3, r4);

        assertNotEquals(r1, r3);
        assertNotEquals(r2, r4);
    }

    @Test
    @DisplayName("Zero denominator")
    void testZeroDenominator() {
        int numerator = 1;
        int zeroDenominator = 0;
        assertThrows(
            RationalNumberDivideByZeroException.class,
            () -> new RationalNumber(numerator, zeroDenominator)
        );
    }

    @Test
    @DisplayName("Equals")
    void testEquals() {
        RationalNumber r1 = new RationalNumber(1, 2);
        RationalNumber r2 = new RationalNumber(2, 4);
        assertTrue(r1.equals(r2));
    }

    @Test
    @DisplayName("Not equals")
    void testNotEquals() {
        RationalNumber r1 = new RationalNumber(1, 2);
        RationalNumber r2 = new RationalNumber(1, 3);
        assertFalse(r1.equals(r2));
    }

    @Test
    @DisplayName("Non-negative")
    void testNonNegative() {
        RationalNumber r1 = new RationalNumber(1, 1);
        RationalNumber r2 = new RationalNumber(-1, 1);
        RationalNumber r3 = new RationalNumber(1, -1);
        RationalNumber r4 = new RationalNumber(-1, -1);
        assertAll(() -> assertTrue(r1.isNonNegative()),
                () -> assertFalse(r2.isNonNegative()),
                () -> assertFalse(r3.isNonNegative()),
                () -> assertTrue(r4. isNonNegative()));
    }

    @Test
    @DisplayName("Compare simple")
    void testCompareSimple() {
        RationalNumber r1 = new RationalNumber(1, 1);
        assertEquals(1, r1.compareTo(RationalNumber.zero));

        RationalNumber r2 = new RationalNumber(-1, 1);
        assertEquals(-1, r2.compareTo(RationalNumber.zero));

        RationalNumber r3 = new RationalNumber(0, 1);
        assertEquals(0, r3.compareTo(RationalNumber.zero));
    }

    @Test
    @DisplayName("Compare positive")
    void testComparePositive() {
        RationalNumber r1 = new RationalNumber(55, 103);
        RationalNumber r2 = new RationalNumber(56, 103);
        assertEquals(-1, r1.compareTo(r2));

        RationalNumber r3 = new RationalNumber(1, 55);
        RationalNumber r4 = new RationalNumber(1, 56);
        assertEquals(1, r3.compareTo(r4));
    }

    @Test
    @DisplayName("Compare negative")
    void testCompareNegative() {
        RationalNumber r1 = new RationalNumber(-55, 103);
        RationalNumber r2 = new RationalNumber(-56, 103);
        assertEquals(1, r1.compareTo(r2));

        RationalNumber r3 = new RationalNumber(-1, 55);
        RationalNumber r4 = new RationalNumber(-1, 56);
        assertEquals(-1, r3.compareTo(r4));
    }

    @Test
    @DisplayName("Add")
    void testAdd() {
        RationalNumber r1 = new RationalNumber(1, 2);
        RationalNumber r2 = new RationalNumber(-1, 2);
        assertEquals(RationalNumber.zero, r1.add(r2));

        // Create arrays for left and right operands, and the correct results.
        int[] leftInts = {
                -2, 5,
                -6, 11,
                -11, 8,
                -7, 3,
                5, 6,
                -17, 15,
                13, 7,
                9, 17
        };
        int[] rightInts = {
                4, 5,
                -4, 11,
                5, 8,
                1, 3,
                -1, 6,
                -1, 15,
                2, 7,
                -5, 17
        };
        int[] resultInts = {
               2, 5,
               -10, 11,
               -3, 4,
               -2, 1,
               2, 3,
               -6, 5,
               15, 7,
               4, 17
        };

        List<RationalNumber> left = generateRationalNumberList(leftInts);
        List<RationalNumber> right = generateRationalNumberList(rightInts);
        List<RationalNumber> result = generateRationalNumberList(resultInts);
        for (int i = 0; i < left.size(); ++i) {
            assertEquals(result.get(i), left.get(i).add(right.get(i)));
        }
    }

    @Test
    @DisplayName("Subtract")
    void testSubtract() {
        RationalNumber r = new RationalNumber(1, 2);
        assertEquals(RationalNumber.zero, r.subtract(r));

        // Create arrays for left and right operands, and the correct results.
        int[] leftInts = {
                3, 8,
                -7, 9,
                -2, 11,
                11, 13,
                1, 4,
                -2, 3,
                -6, 7,
                3, 4
        };
        int[] rightInts = {
                5, 8,
                4, 9,
                -9, 11,
                -4, 13,
                -3, 8,
                5, 6,
                -13, 14,
                5, 6
        };
        int[] resultInts = {
                -1, 4,
                -11, 9,
                7, 11,
                15, 13,
                5, 8,
                -3, 2,
                1, 14,
                -1, 12
        };

        List<RationalNumber> left = generateRationalNumberList(leftInts);
        List<RationalNumber> right = generateRationalNumberList(rightInts);
        List<RationalNumber> result = generateRationalNumberList(resultInts);
        for (int i = 0; i < left.size(); ++i) {
            assertEquals(result.get(i), left.get(i).subtract(right.get(i)));
        }
    }

    @Test
    @DisplayName("Multiply")
    void testMultiply() {
        RationalNumber r = new RationalNumber(1, 2);
        assertEquals(RationalNumber.zero, r.multiply(RationalNumber.zero));

        // Create arrays for left and right operands, and the correct results.
        int[] leftInts = {
                7, 11,
                5, 7,
                -2, 9,
                -3, 17
        };
        int[] rightInts = {
                5, 4,
                -3, 4,
                5, 11,
                -5, -4
        };
        int[] resultInts = {
                35, 44,
                -15, 28,
                -10, 99,
                -15, 68
        };

        List<RationalNumber> left = generateRationalNumberList(leftInts);
        List<RationalNumber> right = generateRationalNumberList(rightInts);
        List<RationalNumber> result = generateRationalNumberList(resultInts);
        for (int i = 0; i < left.size(); ++i) {
            assertEquals(result.get(i), left.get(i).multiply(right.get(i)));
        }
    }

    @Test
    @DisplayName("Divide")
    void testDivide() {
        RationalNumber r = new RationalNumber(1, 2);
        assertThrows(
                RationalNumberDivideByZeroException.class,
                () -> r.divide(RationalNumber.zero)
        );

        // Create arrays for left and right operands, and the correct results.
        int[] leftInts = {
                1, 1,
                5, 1,
                -3, 4,
                -7, 8
        };
        int[] rightInts = {
                1, 2,
                -5, 7,
                9, -16,
                -21, 16
        };
        int[] resultInts = {
                2, 1,
                -7, 1,
                4, 3,
                2, 3
        };

        List<RationalNumber> left = generateRationalNumberList(leftInts);
        List<RationalNumber> right = generateRationalNumberList(rightInts);
        List<RationalNumber> result = generateRationalNumberList(resultInts);
        for (int i = 0; i < left.size(); ++i) {
            assertEquals(result.get(i), left.get(i).divide(right.get(i)));
        }
    }

    // GCD helper method.
    private int greatestCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        }
        return greatestCommonDivisor(b, a % b);
    }

    // Helper method for generating a list of rational numbers from an array.
    private List<RationalNumber> generateRationalNumberList(int[] numbers) {
        List<RationalNumber> rationals = new ArrayList<>();
        for (int i = 0; i < numbers.length; i += 2) {
            rationals.add(new RationalNumber(numbers[i], numbers[i + 1]));
        }
        return rationals;
    }
}