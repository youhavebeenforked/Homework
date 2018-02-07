public class SimpleCalculatorTests {

    private Calculator calculator = new Calculator();


    public void runAllTests() {
        testIntAddition();
        testIntSubtraction();
        testIntMultiplication();
        testIntDivision();
        testDoubleAddition();
        testDoubleSubtraction();
        testDoubleMultiplication();
        testDoubleDivision();
    }

    public void testIntAddition() {
        Assert.assertIntEquals("simple addition", 4, calculator.add(2, 2));
        Assert.assertIntEquals("integer overflow", Integer.MIN_VALUE, calculator.add(Integer.MAX_VALUE, 1));
    }

    public void testDoubleAddition() {
        Assert.assertDoubleEquals("simple double addition", 5.2, calculator.add(2.1, 3.1));
        Assert.assertDoubleEquals("addition with trailing zeros ", 2.1 + 2.0000001, calculator.add(2.1, 2.0000001));
    }

    public void testIntSubtraction() {
        Assert.assertIntEquals("simple subtraction", 18, calculator.subtract(20, 2));
        Assert.assertIntEquals("integer overflow", 1,
                calculator.subtract(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    public void testDoubleSubtraction() {
        Assert.assertDoubleEquals("simple double subtraction", 19.1, calculator.subtract(20.1, 1));
        Assert.assertDoubleEquals("subtraction with trailing zeros ", -1e-7, calculator.subtract(2., 2.0000001));
    }

    public void testIntMultiplication() {
        Assert.assertIntEquals("simple  multiplication", 17 * 81, calculator.multiply(17, 81));
        Assert.assertIntEquals("integer overflow", Integer.MAX_VALUE / 2 * Integer.MAX_VALUE,
                calculator.multiply(Integer.MAX_VALUE / 2, Integer.MAX_VALUE));
    }

    public void testDoubleMultiplication() {
        Assert.assertDoubleEquals("multiplication with trailing zeros", 15.0002 * 17.0003,
                calculator.multiply(15.0002, 17.0003));
        Assert.assertDoubleEquals("what if", 99.8, calculator.multiply(998.0, 0.1));
    }

    public void testIntDivision() {
        Assert.assertIntEquals("simple division", 5, calculator.divide(20, 4));
        try {
            calculator.divide(10, 0);
        } catch (Exception e) {
            System.out.println("success");
            return;
        }
        throw new RuntimeException("successful division by zero");
    }

    public void testDoubleDivision() {
        Assert.assertDoubleEquals("simple double division", 123.2 / 25.4, calculator.divide(123.2, 25.4));
    }
}

class Assert {
    private static final double eps = 1e-6;

    public static void assertIntEquals(String msg, int a, int b) {
        if (a != b) {
            System.out.println(msg);
            throw new RuntimeException("integers " + a + " and " + b + " are not equal");
        } else {
            System.out.println("assert success");
        }
    }

    public static void assertDoubleEquals(String msg, double a, double b) {
        if (Math.abs(a - b) > eps) {
            System.out.println(msg);
            throw new RuntimeException("doubles " + a + " and " + b + " are not equal");
        } else {
            System.out.println("assert success");
        }
    }
}