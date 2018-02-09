package ru.sberbank.homework.kochkova;

/**
 * Created by sofya on 08.02.2018.
 */
class SimpleCalculatorTest {

    private SimpleCalculator calculator = new SimpleCalculator();

    void testOverflowByAdd() throws AssertionException {
        int result = calculator.add(Integer.MAX_VALUE, 1);
        Assert.assertEquals(String.format("maxValue + 1 should cause overflow, but was %d", result), Integer.MIN_VALUE, result);
    }

    void testOverflowByMul() throws AssertionException {
        int result = calculator.multiply(Integer.MAX_VALUE, 2);
        Assert.assertEquals(String.format("maxValue * 2 should cause overflow, but was %d", result), -2, result);
    }

    void testDivisionByZeroInts() throws AssertionException {
        try {
            calculator.divide(1, 0);
        } catch (RuntimeException e) {
            Assert.assertIsArithmeticException("1 / 0 should cause arithmetic exception", e);
        }
    }

    void testDivisionByZeroDoubles() throws AssertionException {
        double result = calculator.divide(1., 0);
        Assert.assertEquals(String.format("1. / 0 should be inf, but was %f", result), Double.POSITIVE_INFINITY, result);
    }


    void testAddOfDoubles() throws AssertionException {
        double result = calculator.add(1e-3, 1e-2);
        Assert.assertEquals(String.format("1e-3 + 1e-2 should be 11e-3, but was %f", result), 11e-3, result);
    }

    void testSubOfDoubles() throws AssertionException {
        double result = calculator.subtract(123e-4, 1e-6);
        double actual = 123e-4 - 1e-6;
        Assert.assertEquals(String.format("123e-4 + 1e-6 should be %f, but was %f", actual, result), actual, result);
    }

    void testMulOfDoubles() throws AssertionException {
        double result = calculator.multiply(234.5, 111.);
        double actual = 234.5 * 111.;
        Assert.assertEquals(String.format("234.5 + 111. should be %f, but was %f", actual, result), actual, result);
    }

    void testDivOfDoubles() throws AssertionException {
        double result = calculator.divide(897.99, 34.23);
        double actual = 897.99 / 34.23;
        Assert.assertEquals(String.format("897.99 / 34.23 should be %f, but was %f", actual, result), actual, result);
    }

    void testAddOfInts() throws AssertionException {
        int result = calculator.add(Integer.MAX_VALUE / 2, 1);
        int actual = Integer.MAX_VALUE / 2 + 1;
        Assert.assertEquals(String.format("maxValue / 2 + 1 should be %d, but was %d", actual, result), actual, result);
    }

    void testSubOfInts() throws AssertionException {
        int result = calculator.subtract(Integer.MAX_VALUE / 2 + 1, 1);
        Assert.assertEquals(String.format("(maxValue / 2 - 1) + 1 should be (maxValue / 2), but was %d", result),
                                Integer.MAX_VALUE / 2, result);
    }

    void testMulOfInts() throws AssertionException {
        int result = calculator.multiply(123, 890);
        int actual = 123 * 890;
        Assert.assertEquals(String.format("123 * 890 should be %d, but was %d", actual, result), actual, result);
    }

    void testDivOfInts() throws AssertionException {
        int result = calculator.divide(98873, 5678);
        int actual = 98873 / 5678;
        Assert.assertEquals(String.format("98873 / 5678 should be %d, but was %d", actual, result), actual, result);
    }

}
