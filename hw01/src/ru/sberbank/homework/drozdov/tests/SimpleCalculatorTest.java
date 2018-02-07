package ru.sberbank.homework.drozdov.tests;

import static ru.sberbank.homework.drozdov.SimpleCalculator.*;

public class SimpleCalculatorTest {
    public void sumTest(int first, int second, int expected) {
        Assert.assertEquals("Integer sum test was passed successfully", sum(first, second), expected);
    }

    public void subtractTest(int first, int second, int expected) {
        Assert.assertEquals("Integer subtract test was passed successfully", subtract(first, second), expected);
    }

    public void multiplyTest(int first, int second, int expected) {
        Assert.assertEquals("Integer multiply test was passed successfully", multiply(first, second), expected);
    }

    public void divideTest(int first, int second, int expected) {
        Assert.assertEquals("Integer divide test was passed successfully", divide(first, second), expected);
    }

    public void sumTest(double first, double second, double expected) {
        Assert.assertEquals("Double sum test was passed successfully", sum(first, second), expected);
    }

    public void subtractTest(double first, double second, double expected) {
        Assert.assertEquals("Double subtract test was passed successfully", subtract(first, second), expected);
    }

    public void multiplyTest(double first, double second, double expected) {
        Assert.assertEquals("Double multiply test was passed successfully", multiply(first, second), expected);
    }

    public void divideTest(double first, double second, double expected) {
        Assert.assertEquals("Double divide test was passed successfully", divide(first, second), expected);
    }
}
