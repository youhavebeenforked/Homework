package ru.sberbank.homework.kalugin;

// int's and double's only, as requested
public class SimpleCalculator {
    public int add(int one, int two) {
        return one + two;
    }
    public double add(double one, double two) {
        return one + two;
    }

    public int subtract(int one, int two) {
        return one - two;
    }
    public double subtract(double one, double two) {
        return one - two;
    }

    public int multiply(int one, int two) {
        return one * two;
    }
    public double multiply(double one, double two) {
        return one * two;
    }

    public int divide(int one, int two) {
        if (two == 0) {
            throw new ArithmeticException("denominator == 0");
        }
        else {
            return one / two;
        }
    }
    public double divide(double one, double two) {
        if (two == 0) {
            throw new ArithmeticException("denominator == 0");
        }
        else {
            return one / two;
        }
    }
}
