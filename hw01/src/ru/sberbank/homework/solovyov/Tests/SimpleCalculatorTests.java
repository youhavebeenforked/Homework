package ru.sberbank.homework.solovyov.Tests;

import ru.sberbank.homework.solovyov.SimpleCalculator;

public class SimpleCalculatorTests {

    public static void startTests() {
        // int sum tests
        fivePlusTenIsFifteen();
        intSumOverflowTest();
        intSumUnderflowTest();

        // int subtract tests
        fiveMinusTenIsMinusFive();
        intSubtractOverflowTest();
        intSubtractUnderflowTest();

        // int multiply tests
        fiveTimesTenIsFifty();
        intMultiplyOverflowTest();
        intMultiplyUnderflowTest();

        // int divide tests
        eightDividedByFourIsTwo();
        intDividedByGreaterInt();
        intDividedByZero();

        // double sum tests
        doubleSumTest();

        // double subtract tests
        doubleSubtractTest();

        // double multiply tests
        doubleMultiplyTest();

        // double divide tests
        doubleDivideTest();
        doubleDividedByZero();
    }

    // int
    static void fivePlusTenIsFifteen() {
        int a = 5;
        int b = 10;
        int expected = 15;
        Assert.assertEquals("5 + 10 = 15",
                SimpleCalculator.sum(a, b), expected);
    }

    static void intSumOverflowTest() {
        int a = Integer.MAX_VALUE;
        int b = 1;
        String expected = "Overflow has occurred.";
        try {
            SimpleCalculator.sum(a, b);
            throw new AssertException("Uncatched Runtime Exception when sum of integers causes int overflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int overflow at sum operation",
                    ex.getMessage(),
                    expected);
        }

    }

    static void intSumUnderflowTest() {
        int a = Integer.MIN_VALUE;
        int b = -1;
        String expected = "Underflow has occurred.";
        try {
            SimpleCalculator.sum(a, b);
            throw new AssertException("Uncatched Runtime Exception when sum of integers causes int underflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int underflow at sum operation",
                    ex.getMessage(),
                    expected);
        }

    }

    static void fiveMinusTenIsMinusFive() {
        int a = 5;
        int b = 10;
        int expected = -5;
        Assert.assertEquals("5 - 10 = -5",
                SimpleCalculator.subtract(a, b), expected);
    }

    static void intSubtractOverflowTest() {
        int a = Integer.MAX_VALUE;
        int b = -1;
        String expected = "Overflow has occurred.";
        try {
            SimpleCalculator.subtract(a, b);
            throw new AssertException("Uncatched Runtime Exception when difference of integers causes int overflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int overflow at division operation",
                    ex.getMessage(),
                    expected);
        }

    }

    static void intSubtractUnderflowTest() {
        int a = Integer.MIN_VALUE;
        int b = 1;
        String expected = "Underflow has occurred.";
        try {
            SimpleCalculator.subtract(a, b);
            throw new AssertException("Uncatched Runtime Exception when difference of integers causes int underflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int underflow at division operation",
                    ex.getMessage(),
                    expected);
        }

    }

    static void fiveTimesTenIsFifty() {
        int a = 5;
        int b = 10;
        int expected = 50;
        Assert.assertEquals("5 * 10 = 50",
                SimpleCalculator.multiply(a, b), expected);
    }

    static void intMultiplyOverflowTest() {
        int a = Integer.MAX_VALUE;
        int b = 2;
        String expected = "Overflow has occurred.";
        try {
            SimpleCalculator.multiply(a, b);
            throw new AssertException("Uncatched Runtime Exception when product of integers causes int overflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int overflow at multiplication operation",
                    ex.getMessage(),
                    expected);
        }

    }

    static void intMultiplyUnderflowTest() {
        int a = Integer.MIN_VALUE;
        int b = 2;
        String expected = "Underflow has occurred.";
        try {
            SimpleCalculator.multiply(a, b);
            throw new AssertException("Uncatched Runtime Exception when product of integers causes int underflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int underflow at multiplication operation",
                    ex.getMessage(),
                    expected);
        }

    }


    static void eightDividedByFourIsTwo() {
        int a = 8;
        int b = 4;
        int expected = 2;
        Assert.assertEquals("8 / 4 = 2",
                SimpleCalculator.divide(a, b), expected);
    }

    static void intDividedByGreaterInt() {
        int a = 10;
        int b = 20;
        int expected = 0;
        Assert.assertEquals("10 / 20 = 0",
                SimpleCalculator.divide(a, b), expected);
    }

    static void intDividedByZero() {
        int a = 25;
        int b = 0;
        String expected = "Division by zero!";
        try {
            SimpleCalculator.divide(a, b);
            throw new AssertException("Uncatched Runtime Exception for division int by zero");
        } catch (ArithmeticException ex) {
            Assert.assertEquals("Handle exception for division int by zero",
                    ex.getMessage(),
                    expected);
        }

    }

    // double
    static void doubleSumTest() {
        double a = 7.12;
        double b = 2.8;
        double expected = 9.92;
        Assert.assertEquals("7.12 + 2.8 = 9.92",
                SimpleCalculator.sum(a, b), expected);
    }

    static void doubleSubtractTest() {
        double a = 11.22;
        double b = 14.35;
        double expected = -3.13;
        Assert.assertEquals("11.22 - 14.35 = -3.13",
                SimpleCalculator.subtract(a, b), expected);
    }

    static void doubleMultiplyTest() {
        double a = 8.125;
        double b = 4.8;
        double expected = 39.0;
        Assert.assertEquals("8.125 * 4.8 = 39.0",
                SimpleCalculator.multiply(a, b), expected);
    }

    static void doubleDivideTest() {
        double a = 39.0;
        double b = 8.125;
        double expected = 4.8;
        Assert.assertEquals("39.0 / 8.125 = 4.8",
                SimpleCalculator.divide(a, b), expected);
    }

    static void doubleDividedByZero() {
        double a = 12.345;
        double b = 0.0;
        String expected = "Division by zero!";
        try {
            SimpleCalculator.divide(a, b);
            throw new AssertException("Uncatched Runtime Exception for division double by zero");
        } catch (ArithmeticException ex) {
            Assert.assertEquals("Handle exception for division double by zero",
                    ex.getMessage(),
                    expected);
        }

    }
}
