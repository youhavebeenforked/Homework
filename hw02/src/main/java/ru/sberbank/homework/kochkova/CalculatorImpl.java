package ru.sberbank.homework.kochkova;

import ru.sberbank.homework.common.Calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by sofya on 09.02.2018.
 */

public class CalculatorImpl implements Calculator {

    private double currentResult;

    private double parseString(String value) throws NumberFormatException {
        if (value.equals("0")) {
            return 0;
        }
        value = value.toLowerCase().replaceAll("[_]+", "_");
        String tmpValue = value.replace("l", "");
        String[] valueSplit = tmpValue.split("_");
        for (int i = 0; i < valueSplit.length; i++) {
            if (valueSplit.length == 1) {
                break;
            }
            if (i != 0 && (valueSplit[i].length() != 3 &&
                    ((value.startsWith("0b") || value.startsWith("0x") || value.startsWith("0")) && valueSplit[i].length() != 4))) {
                throw new NumberFormatException();
            }
            String elem = valueSplit[i];
            if (elem.isEmpty() || elem.startsWith("x") || elem.startsWith("l")
                    || elem.startsWith(".") || elem.startsWith("b")
                    || elem.endsWith("x") || elem.endsWith("l")
                    || elem.endsWith(".") || elem.endsWith("b")) {
                throw new NumberFormatException();
            }
            if (i == 0 && valueSplit[i].replaceAll("0b|0x", "").matches("[0]+")) {
                throw new NumberFormatException();
            }
        }
        value = value.replace("_", "");
        if (value.contains(".")) {
            return Double.parseDouble(value);
        }
        if (value.endsWith("l")) {
            value = value.substring(0, value.length() - 1);
        }
        if (value.startsWith("0b")) {
            return Long.valueOf(value.substring(2), 2);
        }
        if (value.startsWith("0x")) {
            return Long.valueOf(value.substring(2), 16);
        }
        if (value.startsWith("0")) {
            return Long.valueOf(value.substring(1), 8);
        }
        return Long.valueOf(value);
    }

    @Override
    public String calculate(String userInput) {
        String[] expression = userInput.split(" ");
        if (expression.length != 2 && expression.length != 3) {
            return "error > wrong expression";
        }
        double first = 0;
        if (!(expression[0].length() == 1 && Operation.isOperation(expression[0].charAt(0)))) {
            currentResult = 0;
            try {
                first = parseString(expression[0]);
            } catch (NumberFormatException e) {
                return "error > " + expression[0];
            }
        }
        double second;
        try {
            second = parseString(expression[expression.length - 1]);
        } catch (NumberFormatException e) {
            currentResult = 0;
            return "error > " + expression[expression.length - 1];
        }

        if (expression.length == 2) {
            try {
                currentResult = Operation.completeOperation(currentResult, second, expression[0].charAt(0));
            } catch (RuntimeException e) {
                return "error > wrong expression";
            }
        } else {
            try {
                currentResult = Operation.completeOperation(first, second, expression[1].charAt(0));
            } catch (RuntimeException e) {
                return "error > wrong expression";
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        return String.valueOf(decimalFormat.format(currentResult));
    }
}
