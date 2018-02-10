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
        value = value.toLowerCase();
        String[] valueSplit = value.split("_");
        for (int i = 0; i < valueSplit.length; i++) {
            if (valueSplit.length == 1) {
                break;
            }
            if (i != 0 && valueSplit[i].length() != 3) {
                System.out.println(valueSplit.length);
                throw new NumberFormatException();
            }
            String elem = valueSplit[i];
            if (elem.isEmpty() || elem.startsWith("x") || elem.startsWith("L")
                    || elem.startsWith(".") || elem.startsWith("b")
                    || elem.endsWith("x") || elem.endsWith("L")
                    || elem.endsWith(".") || elem.endsWith("b")) {
                throw new NumberFormatException();
            }
            if (i == 0) {
                String tmpValue = valueSplit[i].replaceAll("0b|0x", "");
                if (Integer.parseInt(tmpValue) == 0) {
                    throw new NumberFormatException();
                }
            }
        }
        value = value.replace("_", "");
        if (value.contains(".")) {
            return Double.parseDouble(value);
        }
        if (value.endsWith("l")) {
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
        if (expression.length != 2 && expression.length != 3) {
            return "error > wrong expression";
        }
        double first = 0;
        if (!Operation.isOperation(expression[0].charAt(0))) {
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
