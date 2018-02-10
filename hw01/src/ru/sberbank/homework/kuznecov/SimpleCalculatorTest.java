package ru.sberbank.homework.kuznecov;

public class SimpleCalculatorTest {
    public static void main(String[] args) {
        SimpleCalculator simpleCalculator = new SimpleCalculator();

        try {
            Assert.assertEquals("2 + 2", 4, simpleCalculator.sum(2, 2));
            Assert.assertEquals("2.5 + 3.4", 5.9, simpleCalculator.sum(2.5, 3.4), 0.000001);

            Assert.assertEquals("3 - 2", 1, simpleCalculator.subtract(3, 2));
            Assert.assertEquals("3.4 - 2.1", 1.3, simpleCalculator.subtract(3.4, 2.1), 0.000001);

            Assert.assertEquals("5 * 4", 20, simpleCalculator.multiply(5, 4));
            Assert.assertEquals("4.2 * 2.0", 8.4, simpleCalculator.multiply(4.2, 2.0), 0.000001);

            Assert.assertEquals("4 / 3", 1, simpleCalculator.divide(4, 3));
            Assert.assertEquals("7 / 2", 3.5, simpleCalculator.divide(7.0, 2.0), 0.000001);

            //Division by zero.
            //Assert.assertEquals("5 / 0", 0, simpleCalculator.div(5, 0));
            //Assert.assertEquals("5.0 / 0", 0, simpleCalculator.div(5.0, 0.0), 0.000001);
        } catch (TestNotPassedException e) {
            System.out.println(e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println("Unexpected exception " + e.toString());
        }
    }
}
