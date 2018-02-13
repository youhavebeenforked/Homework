package ru.sberbank.homework.andreev;

import static ru.sberbank.homework.andreev.Assert.*;

public class SimpleCalculatorTest {
    public static void main(String[] args) {
        startAllTests();
    }


    public static void startAllTests() {
        happyPathIntSum();
        happyPathDoubleSum();
        happyPathIntMult();
        happyPathDoubleMult();
        happyPathIntSubstraction();
        happyPathDoubleSubstraction();
        happyPathIntDivide();
        happyPathDoubleDivide();
        exceptionIntOverflow();
        exceptionDoubleOverflow();
    }

    private static void happyPathIntSum() {
        int expected = 18;
        int actual = SimpleCalculator.sum(8, 10);
        assertEquals(expected, actual);
    }

    private static void happyPathDoubleSum() {
        double expected = 42.42;
        double actual = SimpleCalculator.sum(42, 0.42);
        assertEquals(expected, actual);
    }

    private static void happyPathIntMult() {
        int expected = 144;
        int actual = SimpleCalculator.multiply(12, 12);
        assertEquals(expected, actual);
    }

    private static void happyPathDoubleMult() {
        double expected = 99.8;
        double actual = SimpleCalculator.multiply(998.0, 0.1);
        assertEquals(expected, actual);
    }

    private static void happyPathIntSubstraction() {
        int expected = 75;
        int actual = SimpleCalculator.substract(100, 25);
        assertEquals(expected, actual);
    }

    private static void happyPathDoubleSubstraction() {
        double expected = 150.123;
        double actual = SimpleCalculator.substract(300, 149.877);
        assertEquals(expected, actual);
    }

    private static void happyPathIntDivide() {
        int expected = 111;
        int actual = SimpleCalculator.divide(555, 5);
        assertEquals(expected, actual);
    }

    private static void happyPathDoubleDivide() {
        double expected = 0.03;
        double actual = SimpleCalculator.divide(0.3, 10);
        assertEquals(expected, actual);
    }

    private static void exceptionIntOverflow() {
        assertException(() -> SimpleCalculator.multiply(2_000_000, 10000), new ArithmeticException("integer overflow"));
    }

    private static void exceptionDoubleOverflow() {
        assertException(() -> SimpleCalculator.multiply(Double.MAX_VALUE, 9999999999.1111), new ArithmeticException("double overflow"));
    }
}
