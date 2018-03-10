package ru.sberbank.homework.solovyov;

public class SimpleCalculator {

    public static int sum(int a, int b) {
        long result = ((long) a) + b;
        if (result > Integer.MAX_VALUE) {
            throw new RuntimeException("Overflow has occurred.");
        } else if (result < Integer.MIN_VALUE) {
            throw new RuntimeException("Underflow has occurred.");
        }
        return (int) result;
    }

    public static int subtract(int a, int b) {
        return sum(a, -b);
    }

    public static int multiply(int a, int b) {
        long result = ((long) a) * b;
        if (result > Integer.MAX_VALUE) {
            throw new RuntimeException("Overflow has occurred.");
        } else if (result < Integer.MIN_VALUE) {
            throw new RuntimeException("Underflow has occurred.");
        }
        return (int) result;
    }

    public static int divide(int a, int b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new ArithmeticException("Division by zero!");
        }
    }

    public static double sum(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new ArithmeticException("Division by zero!");
        }
    }
}