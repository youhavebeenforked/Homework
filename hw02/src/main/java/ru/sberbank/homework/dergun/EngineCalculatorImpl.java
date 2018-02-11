package ru.sberbank.homework.dergun;

public class EngineCalculatorImpl implements EngineCalculator {
    private double result = 0;

    @Override
    public double getValue() {
        return result;
    }

    @Override
    public void reset() {
        result = 0;
    }

    @Override
    public void calculate(Operation operation, double b) {
        calculate(result, operation, b);
    }

    @Override
    public void calculate(double a, Operation operation, double b) {
        switch (operation) {
            case PLUS:
                result = a + b;
                break;
            case MINUS:
                result = a - b;
                break;
            case MULTIPLY:
                result = a * b;
                break;
            case DIVIDE:
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}
