package ru.sberbank.homework.abzaltdinov;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CalculatorImpl implements ru.sberbank.homework.common.Calculator {
    private static final double EPSILON = 1e-6;
    private String currentState;

    @Override
    public String calculate(String userInput) {
        double firstOperand;
        double secondOperand;
        Operation operation;

        String[] splittedUserInput = userInput.split(" ");
        if (splittedUserInput.length == 2) { // formatting "@_b" to "a_@_b", a,b-operands, @-operator
            String[] largerSplittedInput = new String[3];
            largerSplittedInput[0] = currentState;
            for (int i = 0; i < 2; ++i) {
                largerSplittedInput[i + 1] = splittedUserInput[i];
            }
            splittedUserInput = largerSplittedInput;
        }

        if (splittedUserInput.length != 3 || splittedUserInput[0] == null) {
            return getFormattedError("wrong expression");
        }

        try {
            firstOperand = Parser.parseNumber(splittedUserInput[0]);
        } catch (NumberFormatException nfex) {
            return getFormattedError(splittedUserInput[0]);
        }

        try {
            operation = Parser.parseOperation(splittedUserInput[1]);
        } catch (UnsupportedOperationException uoex) {
            return getFormattedError(splittedUserInput[1]);
        }

        try {
            secondOperand = Parser.parseNumber(splittedUserInput[2]);
        } catch (NumberFormatException nfex) {
            return getFormattedError(splittedUserInput[2]);
        }

        double result = operation.calculate(firstOperand, secondOperand);
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat doubleDF = new DecimalFormat("0.00", otherSymbols);
        DecimalFormat intDF = new DecimalFormat("0");
        if (Math.abs(result - Math.round(result)) < EPSILON) {
            currentState = intDF.format(result);
        } else {
            currentState = doubleDF.format(result);
        }
        return currentState;
    }

    private String getFormattedError(String message) {
        return "error > " + message;
    }
}
