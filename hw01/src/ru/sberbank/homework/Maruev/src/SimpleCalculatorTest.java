/**
 * Created by Иван on 07.02.2018.
 */
public class SimpleCalculatorTest {
    public static void main(String[] args) throws ArithmeticException {
        SimpleCalculator calculator = new SimpleCalculator();

        Assert.assertIntEquals("2-2=0", 0, calculator.sub(2, 2));
        Assert.assertIntEquals("2+2=4", 4, calculator.sum(2, 2));
        Assert.assertIntEquals("3*3=9", 9, calculator.mult(3, 3));
        Assert.assertDivIntZero("Except not fixed", "Exception zero");

        Assert.assertDoubleEquals("2.1+2.2", 4.3, calculator.sum(2.1, 2.2));
        Assert.assertDoubleEquals("3.1-0.1", 3.0, calculator.sub(3.1, 0.1));
        Assert.assertDoubleEquals("1.5*1.5", 2.25, calculator.mult(1.5, 1.5));
        Assert.assertDivDoubleZero("Exception not fixed", "Exception zero");

        System.out.println("Good test");

    }
}
