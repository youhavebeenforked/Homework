package ru.sberbank.homework.kochkova;

import java.util.Arrays;
import java.util.function.BinaryOperator;

/**
 * Created by sofya on 09.02.2018.
 */
public class OperationExecutor {
    private enum Operation {
        ADD("+", (a, b) -> a + b),
        SUBTRACT("-", (a, b) -> a - b),
        MULTIPLY("*", (a, b) -> a * b),
        DIVIDE("/", (a, b) -> a / b);

        private String symbol;
        private BinaryOperator<Double> operation;

        Operation(String symbol, BinaryOperator<Double> operation) {
            this.symbol = symbol;
            this.operation = operation;
        }
    }

    private static Operation[] opers = Operation.values();
    public static String[] operations = Arrays.asList(opers).stream().map(o -> o.symbol).toArray(String[]::new);

    private Operation getOperation(String operation) {
        int id = Arrays.asList(operations).indexOf(operation);
        return opers[id];
    }

    public double completeOperation(double first, double second, String operation) {
        Operation nowOp = getOperation(operation);
        return nowOp.operation.apply(first, second);
    }
}
