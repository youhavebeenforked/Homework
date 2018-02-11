package ru.sberbank.homework.dergun;

public interface EngineCalculator {

    double getValue();

    void reset();

    void calculate(Operation operation, double a);

    void calculate(double a, Operation operation, double b);

}
