package ru.sberbank.homework.dergun;

public enum Operation {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public static Operation parse(String s) {
        for (Operation operation : values()) {
            if (operation.symbol.equals(s)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Invalid operator");
    }
}
