package ru.sberbank.homework.kalugin;

import java.util.function.DoubleBinaryOperator;

/**
 * Арифметическая операция.
 */
public enum Operation {
    ADD ((x, y) -> (x + y)),
    SUBTRACT ((x, y) -> ( x - y)),
    MULTIPLY ((x, y) -> ( x * y)),
    DIVIDE ((x, y) -> ( x / y));
    // POWER (Math::pow);

    private final DoubleBinaryOperator function;

    Operation (DoubleBinaryOperator function) {
        this.function = function;
    }

    public DoubleBinaryOperator getFunction() {
        return function;
    }

    public Double compute(double x1, double x2) {
        return this.function.applyAsDouble(x1, x2);
    }
}
