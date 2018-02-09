package ru.sberbank.homework.kuznecov;

public class SimpleCalculator {

    public int sum(int firstInt, int secondInt){
        return firstInt + secondInt;
    }
    public double sum(double firstDouble, double secondDouble){
        return firstDouble + secondDouble;
    }

    public int subtract(int firstInt, int secondInt){
        return firstInt - secondInt;
    }
    public double subtract(double firstDouble, double secondDouble){
        return firstDouble - secondDouble;
    }

    public int multiply(int firstInt, int secondInt){
        return firstInt * secondInt;
    }
    public double multiply(double firstDouble, double secondDouble){
        return firstDouble * secondDouble;
    }

    public int divide(int firstInt, int secondInt){
        if (secondInt == 0) {
            throw new ArithmeticException();
        }
        else {
            return firstInt / secondInt;
        }
    }
    public double divide(double firstDouble, double secondDouble){
        if (secondDouble == 0) {
            throw new ArithmeticException();
        }
        else {
            return firstDouble / secondDouble;
        }
    }

}
