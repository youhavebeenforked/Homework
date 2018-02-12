package ru.sberbank.homework.kochkova;

import ru.sberbank.homework.common.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by sofya on 09.02.2018.
 */

public class CalculatorImpl implements Calculator {

    private double currentResult;
    private boolean errorOccured;

    private double parseString(String value) throws NumberFormatException {
        if (value.equals("0")) {
            return 0;
        }
        if (value.startsWith("_") || value.endsWith("_")) {
            throw new NumberFormatException();
        }
        value = value.toLowerCase().replaceAll("[_]+", "_");
        String[] valueSplit = value.split("_");
        for (int i = 0; i < valueSplit.length; i++) {
            if (valueSplit.length == 1) {
                break;
            }
            String elem = valueSplit[i];
            if (elem.isEmpty() || elem.startsWith("x") || elem.startsWith("l")
                    || elem.startsWith(".") || elem.startsWith("b")
                    || elem.endsWith("x") || (elem.endsWith("l") && i != valueSplit.length - 1)
                    || elem.endsWith(".") || elem.endsWith("b")) {
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
        if (value.startsWith("0b") || value.startsWith("-0b") || value.startsWith("+0b")) {
            return Long.valueOf(value.replace("0b", ""), 2);
        }
        if (value.startsWith("0x") || value.startsWith("-0x") || value.startsWith("+0x")) {
            return Long.valueOf(value.replace("0x", ""), 16);
        }
        if (value.startsWith("0")) {
            return Long.valueOf(value.substring(1), 8);
        }
        if (value.startsWith("-0") || value.startsWith("+0")) {
            return Long.valueOf(value.substring(2), 8);
        }
        return Long.valueOf(value);
    }

    @Override
    public String calculate(String userInput) {
        String[] expression = userInput.split(" ");
        if (errorOccured) {
            currentResult = 0;
            if (expression.length != 3) {
                return "error > wrong expression";
            }
        }
        if (expression.length != 2 && expression.length != 3) {
            errorOccured = true;
            return "error > wrong expression";
        }
        double first = 0;
        if (!(expression[0].length() == 1 && Operation.isOperation(expression[0].charAt(0)))) {
            try {
                first = parseString(expression[0]);
            } catch (NumberFormatException e) {
                errorOccured = true;
                return "error > " + expression[0];
            }
        }
        double second;
        try {
            second = parseString(expression[expression.length - 1]);
        } catch (NumberFormatException e) {
            errorOccured = true;
            return "error > " + expression[expression.length - 1];
        }

        if (expression.length == 2) {
            try {
                currentResult = Operation.completeOperation(currentResult, second, expression[0].charAt(0));
            } catch (RuntimeException e) {
                errorOccured = true;
                return "error > wrong expression";
            }
        } else {
            try {
                currentResult = Operation.completeOperation(first, second, expression[1].charAt(0));
            } catch (RuntimeException e) {
                errorOccured = true;
                return "error > wrong expression";
            }
        }
        errorOccured = false;
        BigDecimal bd = new BigDecimal(Double.toString(currentResult));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String ans = bd.toString();
        if (ans.endsWith(".00")) {
            return ans.substring(0, ans.length() - 3);
        } else if (ans.endsWith("0")) {
            return ans.substring(0, ans.length() - 1);
        }
        return ans;

    }
}
