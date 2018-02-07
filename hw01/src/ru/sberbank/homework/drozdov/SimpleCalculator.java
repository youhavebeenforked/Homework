package ru.sberbank.homework.drozdov;

public class SimpleCalculator {
    public static int sum(int first, int second) {
        return first + second;
    }

    public static double sum(double first, double second) {
        return first + second;
    }

    public static int subtraction(int first, int second) {
        return first - second;
    }

    public static double subtraction(double first, double second) {
        return first - second;
    }

    public static int multiply(int first, int second) {
        return first * second;
    }

    public static double multiply(double first, double second) {
        return first * second;
    }

    public static int division(int first, int second) {
        if (second == 0) {
            throw new RuntimeException("Division by zero");
        }
        return first / second;
    }

    public static double division(double first, double second) {
        return first / second;
    }
}
