package ru.sberbank.homework.solovyov.Tests;

import ru.sberbank.homework.solovyov.SimpleCalculator;

public class SimpleCalculatorTests {

    public static void StartTests() {
        // int sum tests
        FivePlusTenIsFifteen();
        IntSumOverflowTest();
        IntSumUnderflowTest();

        // int subtract tests
        FiveMinusTenIsMinusFive();
        IntSubtractOverflowTest();
        IntSubtractUnderflowTest();

        // int multiply tests
        FiveTimesTenIsFifty();
        IntMultiplyOverflowTest();
        IntMultiplyUnderflowTest();

        // int divide tests
        EightDividedByFourIsTwo();
        IntDividedByGreaterInt();
        IntDividedByZero();

        // double sum tests
        DoubleSumTest();
        // double subtract tests
        DoubleSubtractTest();
        // double multiply tests
        DoubleMultiplyTest();
        // double divide tests
        DoubleDivideTest();
        DoubleDividedByZero();

    }

    // int
    static void FivePlusTenIsFifteen() {
        int a = 5, b = 10, expected = 15;
        Assert.assertEquals("5 + 10 = 15",
                SimpleCalculator.Sum(a, b), expected);
    }
    static void IntSumOverflowTest(){
        int a = Integer.MAX_VALUE, b = 1;
        String expected = "Overflow has occurred.";
         try {
             SimpleCalculator.Sum(a, b);
             throw new AssertException("Uncatched Runtime Exception when sum of integers causes int overflow");
         } catch (RuntimeException ex) {
             Assert.assertEquals("Handle exception for int overflow at sum operation",
                     expected,
                     ex.getMessage());
         }

    }
    static void IntSumUnderflowTest(){
        int a = Integer.MIN_VALUE, b = -1;
        String expected = "Underflow has occurred.";
        try {
            SimpleCalculator.Sum(a, b);
            throw new AssertException("Uncatched Runtime Exception when sum of integers causes int underflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int underflow at sum operation",
                    expected,
                    ex.getMessage());
        }

    }

    static void FiveMinusTenIsMinusFive() {
        int a = 5, b = 10, expected = -5;
        Assert.assertEquals("5 - 10 = -5",
                SimpleCalculator.Subtract(a, b), expected);
    }
    static void IntSubtractOverflowTest(){
        int a = Integer.MAX_VALUE, b = -1;
        String expected = "Overflow has occurred.";
        try {
            SimpleCalculator.Subtract(a, b);
            throw new AssertException("Uncatched Runtime Exception when difference of integers causes int overflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int overflow at division operation",
                    expected,
                    ex.getMessage());
        }

    }
    static void IntSubtractUnderflowTest(){
        int a = Integer.MIN_VALUE, b = 1;
        String expected = "Underflow has occurred.";
        try {
            SimpleCalculator.Subtract(a, b);
            throw new AssertException("Uncatched Runtime Exception when difference of integers causes int underflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int underflow at division operation",
                    expected,
                    ex.getMessage());
        }

    }

    static void FiveTimesTenIsFifty() {
        int a = 5, b = 10, expected = 50;
        Assert.assertEquals("5 * 10 = 50",
                SimpleCalculator.Multiply(a, b), expected);
    }
    static void IntMultiplyOverflowTest(){
        int a = Integer.MAX_VALUE, b = 2;
        String expected = "Overflow has occurred.";
        try {
            SimpleCalculator.Multiply(a, b);
            throw new AssertException("Uncatched Runtime Exception when product of integers causes int overflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int overflow at multiplication operation",
                    expected,
                    ex.getMessage());
        }

    }
    static void IntMultiplyUnderflowTest(){
        int a = Integer.MIN_VALUE, b = 2;
        String expected = "Underflow has occurred.";
        try {
            SimpleCalculator.Multiply(a, b);
            throw new AssertException("Uncatched Runtime Exception when product of integers causes int underflow");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for int underflow at multiplication operation",
                    expected,
                    ex.getMessage());
        }

    }


    static void EightDividedByFourIsTwo() {
        int a = 8, b = 4, expected = 2;
        Assert.assertEquals("8 / 4 = 2",
                SimpleCalculator.Divide(a, b), expected);
    }
    static void IntDividedByGreaterInt(){
        int a = 10, b = 20, expected = 0;
        Assert.assertEquals("10 / 20 = 0",
                SimpleCalculator.Divide(a, b), expected);
    }
    static void IntDividedByZero(){
        int a = 25, b = 0;
        String expected = "Division by zero!";
        try {
            SimpleCalculator.Divide(a, b);
            throw new AssertException("Uncatched Runtime Exception for division int by zero");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for division int by zero",
                    expected,
                    ex.getMessage());
        }

    }

    // double
    static void DoubleSumTest() {
        double a = 7.12, b = 2.8, expected = 9.92;
        Assert.assertEquals("7.12 + 2.8 = 9.92",
                SimpleCalculator.Sum(a, b), expected);
    }

    static void DoubleSubtractTest() {
        double a = 11.22, b = 14.35, expected = -3.13;
        Assert.assertEquals("11.22 - 14.35 = -3.13",
                SimpleCalculator.Subtract(a, b), expected);
    }

    static void DoubleMultiplyTest() {
        double a = 8.125, b = 4.8, expected = 39.0;
        Assert.assertEquals("8.125 * 4.8 = 39.0",
                SimpleCalculator.Multiply(a, b), expected);
    }

    static void DoubleDivideTest() {
        double a = 39.0, b = 8.125, expected = 4.8;
        Assert.assertEquals("39.0 / 8.125 = 4.8",
                SimpleCalculator.Divide(a, b), expected);
    }
    static void DoubleDividedByZero(){
        double a = 12.345, b = 0.0;
        String expected = "Division by zero!";
        try {
            SimpleCalculator.Divide(a, b);
            throw new AssertException("Uncatched Runtime Exception for division double by zero");
        } catch (RuntimeException ex) {
            Assert.assertEquals("Handle exception for division double by zero",
                    expected,
                    ex.getMessage());
        }

    }
}
