package ru.sberbank.homework.solovyov;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BinaryOperator;

public enum Operation {
    PLUS('+', (a, b) -> a.add(b)),
    SUBTRACTION('-', (a, b) -> a.subtract(b)),
    MULTIPLICATION('*', (a, b) -> a.multiply(b)),
    DIVISION('/', (a, b) -> a.divide(b,2, RoundingMode.HALF_UP));

    private char sign;
    private BinaryOperator<BigDecimal> bigDecimalBinaryOperator;

    Operation(char sign, BinaryOperator<BigDecimal> bigDecimalBinaryOperator) {
        this.sign = sign;
        this.bigDecimalBinaryOperator = bigDecimalBinaryOperator;
    }

    public char getSign() {
        return sign;
    }

    public static Operation getBySign(char sign) {
        for (Operation op : Operation.values()) {
            if (op.getSign() == sign) {
                return op;
            }
        }
        return null;
    }

    public BigDecimal executeOperation(BigDecimal first, BigDecimal second) {
        return this.bigDecimalBinaryOperator.apply(first, second);
    }
}
