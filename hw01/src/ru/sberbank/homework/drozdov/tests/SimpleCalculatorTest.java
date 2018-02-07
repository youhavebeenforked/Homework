package ru.sberbank.homework.drozdov.tests;

import ru.sberbank.homework.drozdov.SimpleCalculator;

public class SimpleCalculatorTest {
    public void sumTest(int first, int second, int expected) {
        Assert.assertEquals("Integer sum test was passed successfully", SimpleCalculator.sum(first, second), expected);
    }

    public void subtractionTest(int first, int second, int expected) {
        Assert.assertEquals("Integer subtraction test was passed successfully", SimpleCalculator.subtraction(first, second), expected);
    }

    public void multiplyTest(int first, int second, int expected) {
        Assert.assertEquals("Integer multiply test was passed successfully", SimpleCalculator.multiply(first, second), expected);
    }

    public void divisionTest(int first, int second, int expected) {
        Assert.assertEquals("Integer division test was passed successfully", SimpleCalculator.division(first, second), expected);
    }

    public void sumTest(double first, double second, double expected) {
        Assert.assertEquals("Double sum test was passed successfully", SimpleCalculator.sum(first, second), expected);
    }

    public void subtractionTest(double first, double second, double expected) {
        Assert.assertEquals("Double subtraction test was passed successfully", SimpleCalculator.subtraction(first, second), expected);
    }

    public void multiplyTest(double first, double second, double expected) {
        Assert.assertEquals("Double multiply test was passed successfully", SimpleCalculator.multiply(first, second), expected);
    }

    public void divisionTest(double first, double second, double expected) {
        Assert.assertEquals("Double division test was passed successfully", SimpleCalculator.division(first, second), expected);
    }
}
