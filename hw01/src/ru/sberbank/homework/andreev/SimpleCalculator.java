package ru.sberbank.homework.andreev;

public class SimpleCalculator {
    public static int sum(int x, int y) {
        return Math.addExact(x, y);
    }

    public static double sum(double x, double y) {
        double result = x + y;
        checkOnDoubleOverflow(result);
        return result;
    }

    public static int substract(int x, int y) {
        return Math.subtractExact(x, y);
    }

    public static double substract(double x, double y) {
        double result = x - y;
        checkOnDoubleOverflow(result);
        return result;
    }

    public static int multiply(int x, int y) {
        return Math.multiplyExact(x, y);
    }

    public static double multiply(double x, double y) {
        double result = x * y;
        checkOnDoubleOverflow(result);
        return result;
    }

    public static int divide(int x, int y) {
        return x / y;
    }

    public static double divide(double x, double y) {
        return x / y;
    }

    private static void checkOnDoubleOverflow(double x) {
        if (x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY) {
            throw new ArithmeticException("double overflow");
        }
    }


}
