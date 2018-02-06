package ru.sberbank.homework.Abzaltdinov;

public class Assert {
    public static void assertEquals(String message, int expected, int actual) throws AssertException{
        if (expected != actual) {
            throw new AssertException(message);
        }
    }

    public static void assertEquals(String message, double expected, double actual) throws AssertException {
        if (expected != actual) {
            throw new AssertException(message);
        }
    }

    public static void assertEquals(String message, String expected, String actual) throws AssertException {
        if (!expected.equals(actual)) {
            throw new AssertException(message);
        }
    }
}
