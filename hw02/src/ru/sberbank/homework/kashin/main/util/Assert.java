package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.exception.TestFailedException;
import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.your_lastname.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Assert {

    public static void assertEquals(int expected, int actual) {
        if (actual == expected) {
            System.out.println("Test passed");
        } else {
            throw new TestFailedException(actual + " not equal " + expected);
        }
    }

    public static void assertEquals(double expected, double actual) {
        if (new BigDecimal(actual).setScale(3, RoundingMode.UP).doubleValue() == new BigDecimal(expected).setScale(3, RoundingMode.UP).doubleValue()) {
            System.out.println("Test passed");
        } else {
            throw new TestFailedException(actual + " not equal " + expected);
        }
    }

    public static void assertEquals(String expected, String actual) {
        assertEquals(Double.valueOf(expected), Double.valueOf(actual));
    }

    public static void assertEquals(Expression expected, Expression actual) {
        assertEquals(expected.getFirst(), actual.getFirst());
        assertEquals(expected.getSecond(), actual.getSecond());
    }

    public static void assertEquals(Operation expected, Operation actual) {
        if (actual == expected) {
            System.out.println("Test passed");
        } else {
            throw new TestFailedException(actual + " not equal " + expected);
        }
    }
}