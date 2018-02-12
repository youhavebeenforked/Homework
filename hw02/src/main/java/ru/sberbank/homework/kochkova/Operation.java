package ru.sberbank.homework.kochkova;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * Created by sofya on 09.02.2018.
 */

public enum Operation {
    ADD('+', (a, b) -> a + b),
    SUBTRACT('-', (a, b) -> a - b),
    MULTIPLY('*', (a, b) -> a * b),
    DIVIDE('/', (a, b) -> a / b);

    private char symbol;
    private BinaryOperator<Double> operation;
    private static final Map<Character, Operation> operations = Arrays.stream(Operation.values())
                                                                .collect(HashMap<Character, Operation>::new,
                                                                        (m, o) -> m.put(o.symbol, o),
                                                                        (m, u) -> {});

    Operation(char symbol, BinaryOperator<Double> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public static boolean isOperation(char operation) {
        return operations.containsKey(operation);
    }

    public static double completeOperation(double first, double second, char operation) {
        Operation nowOp = operations.get(operation);
        return nowOp.operation.apply(first, second);
    }
}

