package ru.sberbank.homework.solovyov;

public class SimpleCalculator {

    public static int Sum(int a, int b){
        long result = ((long) a) + b;
        if (result > Integer.MAX_VALUE) {
            throw new RuntimeException("Overflow has occurred.");
        } else
            if (result < Integer.MIN_VALUE) {
                throw new RuntimeException("Underflow has occurred.");
            }
        return (int) result;
    }
    public static int Subtract(int a, int b){
        return Sum(a, -b);
    }
    public static int Multiply(int a, int b){
        long result = ((long) a) * b;
        if (result > Integer.MAX_VALUE) {
            throw new RuntimeException("Overflow has occurred.");
        } else
        if (result < Integer.MIN_VALUE) {
            throw new RuntimeException("Underflow has occurred.");
        }
        return (int) result;
    }
    public static int Divide(int a, int b){
        if (b != 0)
            return a / b;
        else throw new RuntimeException("Division by zero!");
    }

    public static double Sum(double a, double b){
        return a + b;
    }
    public static double Subtract(double a, double b){
        return a - b;
    }
    public static double Multiply(double a, double b){
        return a * b;
    }
    public static double Divide(double a, double b){
        if (b != 0)
            return a / b;
        else throw new RuntimeException("Division by zero!");
    }
}