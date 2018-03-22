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
            return null;
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
        answer = new BigDecimal(answer).setScale(2, RoundingMode.HALF_UP).doubleValue();

        if ((answer - Math.round(answer)) == 0) {
            return String.valueOf(Math.round(answer));
        } else {
            return String.valueOf(answer);
        }
    }

    /**
     * Проверка переданных в калькулятор данных.
     */
    private static final String OPERATION_REGEX = "^[*/+-]{1}$";

    private List<Element> validate(String input, String lastResult, ErrorMessage error) {
        if (lastResult != null
                && input.substring(0,1).matches(OPERATION_REGEX)
                && input.charAt(1) == ' ') {
            input = lastResult + " " + input;
        }

        List<Element> equation = new ArrayList<>();

        String[] tokens = input.split(" ");
        if (tokens.length < 3 || tokens.length % 2 == 0) {
            error.setMessage("error > wrong expression");
            return null;
        }

        boolean elementIsOperator = false;

        for (String element: tokens) {
            if (elementIsOperator) {
                if (!checkOperator(element, equation)) {
                    error.setMessage("error > wrong expression");
                    return null;
                }
                elementIsOperator = false;
            } else {
                if (!checkNumber(element, equation)) {
                    error.setMessage("error > " + element);
                    return null;
                }
                elementIsOperator = true;
            }
        }
        return equation;
    }

    private boolean checkOperator(String s, List<Element> equation) {
        if (s.matches(OPERATION_REGEX)) {
            equation.add(new Operator(s.charAt(0)));
            return true;
        } else {
            return false;
        }
    }

    private boolean checkNumber(String s, List<Element> equation) {
        NumberParser number = new NumberParser(s);
        if (!number.parse()) {
            return false;
        }

        try {
            if (number.type == NumberType.DECIMAL) {
                equation.add(new EquationNumber(Double.valueOf(number.getNumber())));
            } else if (number.type != null) {
                equation.add(new EquationNumber(Long.parseLong(number.getNumber(), number.type.getRadix())));
            } else {
                throw new NullPointerException("The type for the number provided has not been assigned " + s);
            }
        } catch (NumberFormatException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private class NumberParser {
        String number;
        boolean minusSign;
        NumberType type;

        NumberParser(String s) {
            number = s;
        }

        boolean parse() {
            if (!number.isEmpty()) {
                handleLiterals();
                handleSign();
                handlePrefix();
                if (!handleUnderscore()) {
                    return false;
                }
                return validateParsedNumber();
            } else {
                return false;
            }
        }

        void handleLiterals() {
            char endChar = number.charAt(number.length() - 1);
            if (endChar == 'l'
                    || endChar == 'L'
                    || endChar == 'd'
                    || endChar == 'D') {
                number = number.substring(0, number.length() - 1);
            }
        }

        void handleSign() {
            if (number.charAt(0) == '-') {
                minusSign = true;
                number = number.substring(1);
            } else if (number.charAt(0) == '+') {
                number = number.substring(1);
            }
        }

        void handlePrefix() {
            if (number.length() > 1) {
                if (number.charAt(0) == '0') {
                    char prefix = number.charAt(1);

                    if (prefix == 'x' || prefix == 'X') {
                        number = number.substring(2);
                        type = NumberType.HEX;
                    } else if (prefix == 'b' || prefix == 'B') {
                        number = number.substring(2);
                        type = NumberType.BINARY;
                    } else if (prefix != '.') {
                        number = number.substring(1);
                        type = NumberType.OCTAL;
                    }
                }
            }

            if (type == null) {
                type = NumberType.DECIMAL;
            }
        }

        boolean handleUnderscore() {
            if (number.contains("_.")) {
                return false;
            }
            if ( (number.startsWith("_") && type != NumberType.OCTAL)
                    || number.endsWith("_")) {
                return false;
            }
            number = number.replace("_", "");
            return true;
        }

        boolean validateParsedNumber() {
            if (type == null) {
                throw new NullPointerException("The type for the number provided has not been assigned "
                                                + number);
            }
            return number.matches(type.getRegEx());
        }

        String getNumber() {
            if (minusSign) {
                number = "-" + number;
            }
            return number;
        }
    }
}