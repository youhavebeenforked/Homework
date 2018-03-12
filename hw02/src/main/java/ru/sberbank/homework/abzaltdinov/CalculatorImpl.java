package ru.sberbank.homework.abzaltdinov;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CalculatorImpl implements ru.sberbank.homework.common.Calculator {
    private static final double EPSILON = 1e-6;
    public static final int OPERANDS_AMOUNT = 2;
    private static final String WRONG_EXPRESSION = "wrong expression";
    private String currentState;

    @Override
    public String calculate(String userInput) {
        double[] operands = new double[OPERANDS_AMOUNT];
        Operation operation;
        String[] splittedUserInput;
        splittedUserInput = splitUserInput(userInput);
        if (splittedUserInput.length != 3 || splittedUserInput[0] == null) {
            return getFormattedError(WRONG_EXPRESSION);
        }
        String[] inputOperands = {splittedUserInput[0], splittedUserInput[2]};
        String inputOperation = splittedUserInput[1];
        for (int i = 0; i < OPERANDS_AMOUNT; ++i) {
            try {
                operands[i] = Parser.parseNumber(inputOperands[i]);
            } catch (NumberFormatException nfex) {
                return getFormattedError(inputOperands[i]);
            }
        }
        try {
            operation = Parser.parseOperation(inputOperation);
        } catch (UnsupportedOperationException uoex) {
            return getFormattedError(WRONG_EXPRESSION);
        }
        double result = operation.calculate(operands[0], operands[1]);
        currentState = formatResult(result);
        return currentState;
    }

    private String formatResult(double result) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.##", otherSymbols);
        return df.format(result);
    }

    private String[] splitUserInput(String userInput) {
        String[] splittedUserInput = userInput.split(" ");
        if (splittedUserInput.length == 2) { // formatting "@_b" to "a_@_b", a,b-operands, @-operator
            String[] largerSplittedInput = new String[3];
            largerSplittedInput[0] = currentState;
            for (int i = 0; i < OPERANDS_AMOUNT; ++i) {
                largerSplittedInput[i + 1] = splittedUserInput[i];
            }
            splittedUserInput = largerSplittedInput;
        }
        return splittedUserInput;
    }

    private String getFormattedError(String message) {
        return "error > " + message;
    }
}
