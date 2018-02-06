package test;

import calculator.SimpleCalculator;
import util.Assert;

public class SimpleCalculatorTest {
    static SimpleCalculator simpleCalculator = new SimpleCalculator();

    public void additionTest(int one, int two, int expected) {
        int actual = simpleCalculator.addition(one, two);
        Assert.assertEqual("Addition int test passed", expected, actual);
    }

    public void additionTest(double one, double two, double expected) {
        double actual = simpleCalculator.addition(one, two);
        Assert.assertEqual("Addition double test passed", expected, actual);
    }

    public void subtractionTest(int one, int two, int expected) {
        int actual = simpleCalculator.subtraction(one, two);
        Assert.assertEqual("Subtraction int test passed", expected, actual);
    }

    public void subtractionTest(double one, double two, double expected) {
        double actual = simpleCalculator.subtraction(one, two);
        Assert.assertEqual("Subtraction double test passed", expected, actual);
    }

    public void multiplicationTest(int one, int two, int expected) {
        int actual = simpleCalculator.multiplication(one, two);
        Assert.assertEqual("Multiplication int test passed", expected, actual);
    }

    public void multiplicationTest(double one, double two, double expected) {
        double actual = simpleCalculator.multiplication(one, two);
        Assert.assertEqual("Multiplication double test passed", expected, actual);
    }

    public void divisionTest(int one, int two, int expected) {
        int actual = simpleCalculator.division(one, two);
        Assert.assertEqual("Multiplication int test passed", expected, actual);
    }

    public void divisionTest(double one, double two, double expected) {
        double actual = simpleCalculator.division(one, two);
        Assert.assertEqual("Multiplication double test passed", expected, actual);
    }

}
