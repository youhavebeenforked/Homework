public class SimpleCalculatorTests {

        Calculator calculator = new Calculator();

        public void runAllTests(){
            testIntAddition();
            testIntSubtraction();
            testIntMultiplication();
            testIntDivision();
            testDoubleAddition();
            testDoubleSubtraction();
            testDoubleMultiplication();
            testDoubleDivision();
        }

        public void testIntAddition(){
            Assert.assertIntEquals("simple addition",2 + 2, (Integer) calculator.add(2, 2));
            Assert.assertIntEquals("integer overflow",Integer.MIN_VALUE, (Integer) calculator.add(Integer.MAX_VALUE, 1));
    }

        public void testDoubleAddition(){
            Assert.assertDoubleEquals("simple double addition", 2.1 + 3.1,(Double) calculator.add(2.1, 3.1));
            Assert.assertDoubleEquals("addition with trailing zeros ", 2.1 +2.0000001, (Double) calculator.add(2.1, 2.0000001));
        }

        public void testIntSubtraction(){
            Assert.assertIntEquals("simple subtraction",20 - 2, (Integer) calculator.subtract(20, 2));
            Assert.assertIntEquals("integer overflow",Integer.MIN_VALUE - Integer.MAX_VALUE,
                                  (Integer) calculator.subtract(Integer.MIN_VALUE, Integer.MAX_VALUE));
        }

        public void testDoubleSubtraction(){
            Assert.assertDoubleEquals("simple double subtraction", 20.1 - 1., (Double) calculator.subtract(20.1, 1));
            Assert.assertDoubleEquals("subtraction with trailing zeros ", 2. -2.0000001, (Double) calculator.subtract(2., 2.0000001));
        }

        public void testIntMultiplication(){
            Assert.assertIntEquals("simple  multiplication", 17 * 81, (Integer) calculator.multiply(17, 81));
            Assert.assertIntEquals("integer overflow", Integer.MAX_VALUE / 2 * Integer.MAX_VALUE,
                    (Integer) calculator.multiply(Integer.MAX_VALUE / 2, Integer.MAX_VALUE));
        }

        public void testDoubleMultiplication(){
            Assert.assertDoubleEquals("multiplication with trailing zeros", 15.0002 * 17.0003,
                    (Double) calculator.multiply(15.0002, 17.0003));
        }

        public void testIntDivision(){
            Assert.assertIntEquals("simple division", 20 / 4, (Integer) calculator.divide(20, 4));
            try{
                calculator.divide(10, 0);
            }
            catch (Exception e){
                System.out.println("success");
                return;
            }
            throw new RuntimeException("successful division by zero");
        }

        public void testDoubleDivision(){
            Assert.assertDoubleEquals("simple double division", 123.2 / 25.4, (Double) calculator.divide(123.2, 25.4));
        }

}

class Assert{

    public static void assertIntEquals(String msg, int a, int b){
        if(a != b){
            System.out.println(msg);
            throw new RuntimeException("integers " + a + " and " + b + " are not equal");
        }
        else
            System.out.println("assert success");
    }

    public static void assertDoubleEquals(String msg, double a, double b){
        if(Double.compare(a, b) != 0) {
            System.out.println(msg);
            throw new RuntimeException("integers are not equal");
        }
        else
            System.out.println("assert success");
    }
}