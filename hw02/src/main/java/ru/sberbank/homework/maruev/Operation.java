
package ru.sberbank.homework.maruev;

/**
 * Используется в классе HardCalculator
 */
public enum Operation {
    SUM('+'),
    SUBSTRACT('-'),
    MULTIPLY('*'),
    DIVISION('/');

    private final char OPERATOR;

    Operation(char OPERATOR) {
        this.OPERATOR = OPERATOR;

    }

    public char getOperation() {
        return OPERATOR;
    }
}
