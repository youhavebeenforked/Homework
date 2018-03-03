package ru.sberbank.homework.gavrilov.operation;

/**
 * Arithmetic operations.
 */
public enum EnumOperation {
    ADDITION('+'),

    SUBTRACT('-'),

    MULTIPLY('*'),

    DIVIDE('/');

    private char operation;

    EnumOperation(char operation) {
        this.operation = operation;
    }

    public char getOperation() {
        return operation;
    }


}
