package ru.sberbank.homework.kalugin;

import java.math.*;
import java.util.ArrayList;
import java.util.List;
import ru.sberbank.homework.common.Calculator;

/**
 * Имплементация калькулятора на основе постифксных матем. выражений.
 */
public class PostfixCalculator implements Calculator {
    private static final String QUIT_COMMAND = "quit";
    private String result;
    private final PostfixParser parser = new PostfixParser();

    public String calculate(String userInput) {
        if (userInput == null) {
            return null;
        }
        if (userInput.equals(QUIT_COMMAND)) {
            return (result = null);
        }

        ErrorMessage error = new ErrorMessage();
        final List<Element> equation = validate(userInput, result, error);
        if (error.hasMessage()) {
            result = null;
            return error.getMessage();
        }

        final Double answer = parser.compute(equation);
        result = prepareResult(answer);
        return result;
    }

    private String prepareResult(Double answer) {
        // округляем до 2-х знаков после запятой
        answer = new BigDecimal(answer).setScale(2, RoundingMode.HALF_UP).doubleValue();
        // в случае если после округления число оканчивается только нулями после точки,
        // уберем и их из ответа
        if ((answer - Math.round(answer)) != 0) {
            return String.valueOf(answer);
        }
        else {
            return String.valueOf(Math.round(answer));
        }
    }

    /**
     * Проверка переданных в калькулятор данных.
     */
    private static final String OPERATION_REGEX = "^[*/+-]{1}$";
    private static final String DECIMAL_OR_INT_REGEX = "^[+-]{0,1}\\d*\\.{0,1}\\d+$";

    private List<Element> validate(String input, String lastResult, ErrorMessage error) {
        if (lastResult != null
                && input.substring(0,1).matches(OPERATION_REGEX)
                && input.charAt(1) == ' ') {
            input = lastResult + " " + input;
        }

        List<Element> equation = new ArrayList<>();

        String[] tokens = input.split(" ");
        if ((tokens.length < 3) || (tokens.length % 2 == 0) ) {
            error.setMessage("error > wrong expression");
            return null;
        }

        Boolean nextElementIsOperator = false;

        for (String s: tokens) {
            if (nextElementIsOperator) {
                if (!checkOperator(s, equation)) {
                    error.setMessage("error > wrong expression");
                    return null;
                }
                nextElementIsOperator = false;
            }
            else {
                if (!checkNumber(s, equation)) {
                    error.setMessage("error > " + s);
                    return null;
                }
                nextElementIsOperator = true;
            }
        }
        // если закончили цикл проверки оператором, а не числом, ошибка в выражении
        if (!nextElementIsOperator) {
            error.setMessage("error > wrong expression");
            return null;
        }
        return equation;
    }

    private boolean checkOperator(String s, List<Element> equation) {
        if (s.matches(OPERATION_REGEX)) {
            equation.add(new Operator(s));
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkNumber(String s, List<Element> equation) {
        NumberParser number = new NumberParser(s);
        if (number.hasError()) {
            return false;
        }

        try {
            if (number.isBinary) {
                equation.add(new EquationNumber(new BigInteger(number.getNumber(), 2).longValue()));
            }
            else if (number.isHexadecimal) {
                equation.add(new EquationNumber(Long.parseLong(number.getNumber(), 16)));
            }
            else if (number.isOctal) {
                equation.add(new EquationNumber(Long.parseLong(number.getNumber(),8)));
            }
            else if (number.isDecimalOrInt
                    && number.getNumber().matches(DECIMAL_OR_INT_REGEX)) {
                equation.add(new EquationNumber(Double.valueOf(number.getNumber())));
            }
            else {
                return false;
            }

        }
        catch (NumberFormatException e) {
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private class NumberParser {
        private String number;

        private boolean error;
        private boolean minusSign;

        private boolean isBinary;
        private boolean isHexadecimal;
        private boolean isOctal;
        private boolean isDecimalOrInt;

        NumberParser(String s) {
            number = s;
            if (!number.isEmpty()) {
                handleLiterals();
                handleSign();
                handlePrefix();
            }
            else {
                error = true;
            }
        }

        private void handleLiterals() {
            if (number.endsWith("l")
                    || number.endsWith("L")
                    || number.endsWith("d")
                    || number.endsWith("D")) {
                number = number.substring(0, number.length() - 1);
            }
        }

        private void handleSign() {
            if (number.charAt(0) == '-') {
                minusSign = true;
                number = number.substring(1);
            }
            else if (number.charAt(0) == '+') {
                number = number.substring(1);
            }
        }

        private void handlePrefix() {
            if (number.length() > 1) {
                if (number.startsWith("0x") || number.startsWith("0X")) {
                    number = number.substring(2);
                    if (!checkUnderscore(number)) {
                        error = true;
                        return;
                    }
                    number = number.replace("_", "");
                    isHexadecimal = true;
                }
                else if (number.startsWith("0b") || number.startsWith("0B")) {
                    number = number.substring(2);
                    if (!checkUnderscore(number)) {
                        error = true;
                        return;
                    }
                    number = number.replace("_", "");
                    isBinary = true;
                }
                else if (number.startsWith("0") && !number.startsWith("0.")) {
                    number = number.substring(1);
                    number = number.replace("_", "");
                    isOctal = true;
                }

            }
            if (!isHexadecimal && !isOctal && !isBinary) {
                if (!checkUnderscore(number)) {
                    error = true;
                    return;
                }
                number = number.replace("_","");
                isDecimalOrInt = true;
            }
            if (minusSign) {
                number = "-" + number;
            }
        }

        private boolean checkUnderscore(String s) {
            return !s.contains("_.") && checkUnderscoreAtTheBegAndTheEnd(s);
        }
        private boolean checkUnderscoreAtTheBegAndTheEnd(String s) {
            return !(s.isEmpty()
                    || (s.startsWith("_")
                    || s.endsWith("_")) );
        }

        boolean hasError() {
            return error;
        }
        String getNumber() {
            return number;
        }
    }
}