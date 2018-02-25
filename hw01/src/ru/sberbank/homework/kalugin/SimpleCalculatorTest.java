package ru.sberbank.homework.kalugin;

import static ru.sberbank.homework.kalugin.Assert.assertEqual;

public class SimpleCalculatorTest {
    private SimpleCalculator simpleCalculator = new SimpleCalculator();

    public void additionTest(int one, int two, int expected) {
        int actual = simpleCalculator.add(one, two);
        assertEqual("Addition int", expected, actual);
    }

    public void additionTest(double one, double two, double expected) {
        double actual = simpleCalculator.add(one, two);
        assertEqual("Addition double", expected, actual);
    }

    public void subtractionTest(int one, int two, int expected) {
        int actual = simpleCalculator.subtract(one, two);
        assertEqual("Subtraction int", expected, actual);
    }

    public void subtractionTest(double one, double two, double expected) {
        double actual = simpleCalculator.subtract(one, two);
        assertEqual("Subtraction double", expected, actual);
    }

    public void multiplicationTest(int one, int two, int expected) {
        int actual = simpleCalculator.multiply(one, two);
        assertEqual("Multiplication int", expected, actual);
    }

    public void multiplicationTest(double one, double two, double expected) {
        double actual = simpleCalculator.multiply(one, two);
        assertEqual("Multiplication double", expected, actual);
    }

    public void divisionTest(int one, int two, int expected) {
        try {
            int actual = simpleCalculator.divide(one, two);
            assertEqual("Division int", expected, actual);
        }
        catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    public void divisionTest(double one, double two, double expected) {
        try {
            double actual = simpleCalculator.divide(one, two);
            assertEqual("Division double", expected, actual);
        }
        catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }
}