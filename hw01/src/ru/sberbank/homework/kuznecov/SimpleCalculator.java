package ru.sberbank.homework.kuznecov;

public class SimpleCalculator {

    public int sum(int firstInt, int secondInt){
        return firstInt + secondInt;
    }
    public double sum(double firstDouble, double secondDouble){
        return firstDouble + secondDouble;
    }

    public int dif(int firstInt, int secondInt){
        return firstInt - secondInt;
    }
    public double dif(double firstDouble, double secondDouble){
        return firstDouble - secondDouble;
    }

    public int multi(int firstInt, int secondInt){
        return firstInt * secondInt;
    }
    public double multi(double firstDouble, double secondDouble){
        return firstDouble * secondDouble;
    }

    public int div(int firstInt, int secondInt){
        if (secondInt == 0)
        throw new ArithmeticException();
        else return firstInt / secondInt;
    }
    public double div(double firstDouble, double secondDouble){
        if (secondDouble == 0)
            throw new ArithmeticException();
        else return firstDouble / secondDouble;
    }

}
