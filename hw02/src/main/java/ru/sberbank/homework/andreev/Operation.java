package ru.sberbank.homework.andreev;

import java.math.BigDecimal;
import java.util.function.BiFunction;

/**
 * Enum класс для выполнения арифметических операций с объектами типа BigDecimal.
 * Перечесление данных операций используется в работе классов ExpressionParser и ExpressionValidator.
 */
public enum Operation {
    DIVIDE("/", (first, second) -> first.divide(second, 20, BigDecimal.ROUND_HALF_UP), 7),
    MULTIPLY("\\*", BigDecimal::multiply, 7),
    PlUS("\\+", BigDecimal::add, 6),
    MINUS("\\-", BigDecimal::subtract, 6);

    private String regExpSymbol;
    private BiFunction<BigDecimal, BigDecimal, BigDecimal> operation;
    private int priority;

    Operation(String regExpSymbol, BiFunction<BigDecimal, BigDecimal, BigDecimal> operation, int priority) {
        this.regExpSymbol = regExpSymbol;
        this.operation = operation;
        this.priority = priority;
    }

    public BiFunction<BigDecimal, BigDecimal, BigDecimal> getOperation() {
        return operation;
    }

    public String getRegExpSymbol() {
        return regExpSymbol;
    }

    public int getPriority() {
        return priority;
    }
}