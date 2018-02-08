package ru.sberbank.homework.bobrov;


/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 07.02.2018
 */


public class SimpleCalculatorTest {
    private SimpleCalculator calc = new SimpleCalculator();
    private static final int INT_SIX = 6;
    private static final int INT_THREE = 3;
    private static final double DOUBLE_SIX = 6.0;
    private static final double DOUBLE_THREE = 3.0;


    public void addInteger() {
        int expected = 9;
        int result = calc.add(INT_SIX, INT_THREE);
        Assert.assertInt("Test addInteger passed", expected, result);
    }

    public void subInteger() {
        int expected = 3;
        int result = calc.subtract(INT_SIX, INT_THREE);
        Assert.assertInt("Test subInteger passed", expected, result);
    }

    public void multiplInt() {
        int expected = 18;
        int result = calc.multiply(INT_SIX, INT_THREE);
        Assert.assertInt("Test multipleInteger passed", expected, result);
    }

    public void divInt() {
        int expected = 2;
        int result = calc.divide(INT_SIX, INT_THREE);
        Assert.assertInt("Test divInteger passed", expected, result);
    }

    public void addDouble() {
        double expected = 9.0;
        double result = calc.add(DOUBLE_SIX, DOUBLE_THREE);
        Assert.assertDouble("Test addDouble passed", expected, result);
    }

    public void subDouble() {
        double expected = 3.0;
        double result = calc.subtract(DOUBLE_SIX, DOUBLE_THREE);
        Assert.assertDouble("Test subDouble passed", expected, result);
    }

    public void multipleDouble() {
        double expected = 18.0;
        double result = calc.multiply(DOUBLE_SIX, DOUBLE_THREE);
        Assert.assertDouble("Test multipleDouble passed", expected, result);
    }

    public void divDouble() {
        double expected = 2.0;
        double result = calc.divide(DOUBLE_SIX, DOUBLE_THREE);
        Assert.assertDouble("Test divDouble passed", expected, result);
    }


    public static void main(String[] args) {
        SimpleCalculatorTest test = new SimpleCalculatorTest();
        test.addInteger();
        test.subInteger();
        test.multiplInt();
        test.divInt();
        test.addDouble();
        test.subDouble();
        test.multipleDouble();
        test.divDouble();

    }
}
