package ru.sberbank.homework.Abzaltdinov;

/**
 * Simple calculator with +,-,*,/ operation with int firstnd double
 */
public class SimpleCalculator {
    public int sum(int first, int second) {
        return first+second;
    }

    public int sub(int first, int second) {
        return first-second;
    }

    public int mul(int first, int second) {
        return first*second;
    }

    public int div(int first, int second) {
        if (second == 0)
            throw new DivideByZeroException();
        return first/second;
    }

    public double sum(double first, double second) {
        return first+second;
    }

    public double sub(double first, double second) {
        return first-second;
    }

    public double mul(double first, double second) {
        return first*second;
    }

    public double div(double first, double second) {
        if (second == 0)
            throw new DivideByZeroException();
        return first/second;
    }
    
    class DivideByZeroException extends RuntimeException {
        DivideByZeroException() {
            super("Divided by zero!");
        }
    }
}
