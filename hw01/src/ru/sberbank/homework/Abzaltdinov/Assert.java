package ru.sberbank.homework.Abzaltdinov;

public class Assert {
    public static void assertEquals(String message, int expected, int actual) throws AssertException{
        if (expected != actual) {
            throw new AssertException(message);
        } else {
            System.out.println(message + " - test passed!");
        }
    }

    public static void assertEquals(String message, double expected, double actual) throws AssertException {
        if (Math.abs(expected - actual) > 1e-6) {
            throw new AssertException(message);
        } else {
            System.out.println(message + " - test passed!");
        }
    }

    public static void assertEquals(String message, String expected, String actual) throws AssertException {
        if (!expected.equals(actual)) {
            throw new AssertException(message);
        } else {
            System.out.println(message + " - test passed!");
        }
    }
}
