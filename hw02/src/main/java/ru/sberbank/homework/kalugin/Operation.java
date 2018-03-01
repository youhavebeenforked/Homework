package ru.sberbank.homework.kalugin;

import java.util.function.DoubleBinaryOperator;

/**
 * Арифметическая операция.
 * При вызове конструктора, через лямбду, задаем нужную матем. операцию функциональному интерфейсу DoubleBinaryOperator
 * который принимает два объекта типа Double, возвращает Double
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
}
