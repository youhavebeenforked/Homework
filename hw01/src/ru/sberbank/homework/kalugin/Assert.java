package ru.sberbank.homework.kalugin;

public class Assert {
    private static final double EPSILON = 1e-6;

    public static void assertEqual(String message, int expected, int actual) {
        if (actual == expected) {
            System.out.println(message + " test passed");
        } else {
            throw new TestFailedException(message + "Test failed: " + actual + " is not equal " + expected);
        }
    }

    public static void assertEqual(String message, double expected, double actual) {
        if (Math.abs(expected - actual) <= EPSILON) {
            System.out.println(message + " test passed");
        } else {
            throw new TestFailedException(message + " test failed: " + actual + " is not equal " + expected);
        }
    }
}