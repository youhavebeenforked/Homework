

/**
 * Created by Иван on 07.02.2018.
 */
public class Assert {
    private static final String RESULT_MESSAGE = "Expected result is: ";
    private static final String EQUAL_EX = "Invalid result. ";
    private static final String ZERO_EX = "Division by zero. ";
    public static SimpleCalculator calculator = new SimpleCalculator();

    public static void assertIntEquals(String message, int expected, int actual) {
        if (expected != actual) {
            throw new AssertExpectedError(EQUAL_EX + RESULT_MESSAGE + message);
        }
    }

    public static void assertDoubleEquals(String message, double expected, double actual) {
        if (Math.abs(expected - actual) > 0.000001) {
            throw new AssertExpectedError(EQUAL_EX + RESULT_MESSAGE + message);
        }
    }

    public static void assertDivIntZero(String message, String expected) {

        try {
            int actual = calculator.div(1, 0);
        } catch (AssertExpectedError e) {
            if (!e.getMessage().equals(expected)) {
                throw new AssertExpectedError(ZERO_EX + message);
            }
        }
    }

    public static void assertDivDoubleZero(String message, String expected) {

        try {
            double actual = calculator.div(1.1, 0.0);
        } catch (AssertExpectedError e) {
            if (!e.getMessage().equals(expected)) {
                throw new AssertExpectedError(ZERO_EX + message);
            }
        }
    }

}
