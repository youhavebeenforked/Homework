package ru.sberbank.homework.kuznecov;

public class Assert {
    public static void assertEquals(String message, int expected, int actual) throws TestNotPassedException{
        if (actual != expected) throw new TestNotPassedException(message);
        else System.out.println("Test is passed");
    }
    public static void assertEquals(String message, double expected, double actual, double delta) throws TestNotPassedException{
        if (Math.abs(expected - actual) > delta) throw new TestNotPassedException(message);
        else System.out.println("Test is passed");
    }
}
