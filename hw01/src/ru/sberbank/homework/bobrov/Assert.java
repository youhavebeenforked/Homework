package ru.sberbank.homework.bobrov;


/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 07.02.2018
 */


public class Assert {
    public static void assertInt(String message, int expected, int actual) {
        if (expected != actual) {
            throw new AssertException("Test Failed");
        } else {
            System.out.println(message);
        }
    }

    public static void assertDouble(String message, double expected, double actual) {
        if (expected != actual) {
            throw new AssertException("Test Failed");
        } else {
            System.out.println(message);
        }
    }


}
