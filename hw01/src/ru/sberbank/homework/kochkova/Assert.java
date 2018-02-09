package ru.sberbank.homework.kochkova;

/**
 * Created by sofya on 08.02.2018.
 */
class Assert {

    private static final double EPS = 1e-9;

    static void assertEquals(String message, int expected, int actual) throws AssertionException {
        if (expected != actual) {
            throw new AssertionException(message);
        } else {
            System.out.println("Test passed!");
        }
    }

    static void assertEquals(String message, double expected, double actual) throws AssertionException {
        if (Math.abs(expected - actual) >= EPS) {
            throw new AssertionException(message);
        } else {
            System.out.println("Test passed!");
        }
    }

    static void assertIsArithmeticException(String message, RuntimeException e) throws AssertionException {
        if (e instanceof ArithmeticException) {
            System.out.println("Test passed!");
        } else {
            throw new AssertionException(message);
        }
    }

}
