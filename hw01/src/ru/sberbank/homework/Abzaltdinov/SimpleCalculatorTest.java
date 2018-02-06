package ru.sberbank.homework.Abzaltdinov;

public class SimpleCalculatorTest {

    SimpleCalculator calculator = new SimpleCalculator();

    public void intSummationTest() {
        int first = 32;
        int second = -12;
        int expected = 20;
        Assert.assertEquals(first + " + " + second + " is " + expected,
                expected,
                calculator.sum(first, second));
    }

    public void doubleSummationTest() {
        double first = 12.36;
        double second = -33.6;
        double expected = -21.24;
        Assert.assertEquals(first + " + " + second + " is " + expected,
                expected,
                calculator.sum(first, second));
    }

    public void intSubstractionTest() {
        int first = 32;
        int second = -12;
        int expected = 44;
        Assert.assertEquals(first + " - " + second + " is " + expected,
                expected,
                calculator.sub(first, second));
    }

    public void doubleSubstractionTest() {
        double first = 12.36;
        double second = -33.6;
        double expected = 45.96;
        Assert.assertEquals(first + " - " + second + " is " + expected,
                expected,
                calculator.sub(first, second));
    }

    public void intMultiplicationTest() {
        int first = 32;
        int second = -12;
        int expected = -384;
        Assert.assertEquals(first + " * " + second + " is " + expected,
                expected,
                calculator.mul(first, second));
    }

    public void doubleMultiplicationTest() {
        double first = 12.36;
        double second = -33.6;
        double expected = -415.296;
        Assert.assertEquals(first + " * " + second + " is " + expected,
                expected,
                calculator.mul(first, second));
    }

    public void intDivisionTest() {
        int first = 32;
        int second = -12;
        int expected = -2;
        Assert.assertEquals(first + " / " + second + " is " + expected,
                expected,
                calculator.div(first, second));
    }

    public void doubleDivisionTest() {
        double first = 12.36;
        double second = -1.5;
        double expected = -8.24;
        Assert.assertEquals(first + " / " + second + " is " + expected,
                expected,
                calculator.div(first, second));
    }

    public void intDivisionByZeroTest() {
        int first = 32;
        int second = 0;
        String expected = "Divided by zero!";
        try {
            calculator.div(first, second);
        } catch (DivisionByZeroException ex) {
            Assert.assertEquals("Handled " + DivisionByZeroException.class.getSimpleName() + ": " + first + " / " + second,
                    expected,
                    ex.getMessage());
        }
    }

    public void doubleDivisionByZeroTest() {
        double first = 12.36;
        double second = 0.0;
        String expected = "Divided by zero!";
        try {
            calculator.div(first, second);
        } catch (DivisionByZeroException ex) {
            Assert.assertEquals("Handled " + DivisionByZeroException.class.getSimpleName() + ": " + first + " / " + second,
                    expected,
                    ex.getMessage());
        }
    }
}
