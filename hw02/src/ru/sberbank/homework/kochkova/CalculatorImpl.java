package ru.sberbank.homework.kochkova;

import java.util.Arrays;

/**
 * Created by sofya on 09.02.2018.
 */
public class CalculatorImpl implements Calculator {

    private double currentResult;
    private OperationExecutor operationExecutor;

    public CalculatorImpl() {
        currentResult = 0;
        operationExecutor = new OperationExecutor();
    }

    private double parseString(String value) {
        if (value.contains(".")) {
            return Double.parseDouble(value);
        }
        if (value.endsWith("L")) {
            return Long.parseLong(value.substring(0, value.length() - 1));
        }
        if (value.startsWith("0b")) {
            return Integer.parseInt(value.substring(2), 2);
        }
        if (value.startsWith("0x")) {
            return Integer.parseInt(value.substring(2), 16);
        }
        if (value.startsWith("0")) {
            return Integer.parseInt(value.substring(1), 8);
        }
        return Integer.parseInt(value);
    }

    @Override
    public String calculate(String userInput) {
        String[] expression = userInput.split(" ");
        double first = 0;
        if (!Arrays.asList(OperationExecutor.operations).contains(expression[0])) {
           first = parseString(expression[0]);
        }

        double second = parseString(expression[expression.length - 1]);

        if (expression.length == 2) {
            currentResult = operationExecutor.completeOperation(currentResult, second, expression[0]);
        } else {
            currentResult = operationExecutor.completeOperation(first, second, expression[1]);
        }
        return String.valueOf(currentResult);
    }
}
