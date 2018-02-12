package ru.sberbank.homework.kochkova;

import ru.sberbank.homework.common.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by sofya on 09.02.2018.
 */

public class CalculatorImpl implements Calculator {

    private double currentResult;
    private boolean currentIntegral = true;

    private double parseString(String value) throws NumberFormatException {
        if (value.equals("0")) {
            return 0;
        }
        value = value.toLowerCase().replaceAll("[_]+", "_");
        String tmpValue = value.replace("l", "");
        String[] valueSplit = tmpValue.split("_");
        for (String elem : valueSplit) {
            if (valueSplit.length == 1) {
                break;
            }
            if (elem.isEmpty() || elem.startsWith("x") || elem.startsWith("l")
                    || elem.startsWith(".") || elem.startsWith("b")
                    || elem.endsWith("x") || elem.endsWith("l")
                    || elem.endsWith(".") || elem.endsWith("b")) {
                throw new NumberFormatException();
            }
        }
        value = value.replace("_", "");
        if (value.contains(".")) {
            currentIntegral = false;
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
