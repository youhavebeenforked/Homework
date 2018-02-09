package ru.sberbank.homework.solovyov.Tests;

public class Assert {

    private static final double EPSILON = 1e-6;

    public static void assertEquals(String testDescription, int actual, int expected) {
        if (expected != actual) {
            throw new AssertException(testDescription);
        } else {
            System.out.println(testDescription + " - OK");
        }
    }

    public static void assertEquals(String testDescription, double actual, double expected) {
        if (Math.abs(expected - actual) > EPSILON) {
            throw new AssertException(testDescription);
        } else {
            System.out.println(testDescription + " - OK");
        }
    }

    public static void assertEquals(String testDescription, String actual, String expected) {
        if (expected == null) {
            System.out.println("No string to expect in test");
            return;
        }
        if (!expected.equals(actual)) {
            throw new AssertException(testDescription);
        } else {
            System.out.println(testDescription + " - OK");
        }
    }
}

class AssertException extends RuntimeException {
    AssertException(String message) {
        super(message);
    }
}