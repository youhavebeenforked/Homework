package ru.sberbank.homework.kalugin;

import java.math.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Проверка переданных в калькулятор данных.
 */
class InputValidator {
    private static final String OPERATION_REGEX = "^[*/+-]{1}$";
    private static final String DECIMAL_OR_INT_REGEX = "^[+-]{0,1}\\d*\\.{0,1}\\d+$";

    static List<Element> validate(String input, String lastResult, ErrorMessage error) {
        if (lastResult != null)
            if (input.substring(0,1).matches(OPERATION_REGEX) && input.charAt(1) == ' ')
                input = lastResult + " " + input;

        List<Element> equation = new ArrayList<Element>();
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

    private static boolean checkNumber(String s, List<Element> equation) {
        if (s.isEmpty()) return false;
        if (s.endsWith("l") || s.endsWith("L") || s.endsWith("d") || s.endsWith("D")) {
            s = s.substring(0, s.length() - 1);
        }

        if (!checkUnderscoreAtTheBegAndTheEnd(s)) return false;

        int prefixIndex = 0;
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            prefixIndex += 1;
        }

        try {
            if (s.length() > prefixIndex + 1) {

                if (s.charAt(prefixIndex) == '0' && s.charAt(prefixIndex + 1) != '.') {
                    char prefix = s.charAt(prefixIndex + 1);
                    // binary
                    if (prefix == 'b' || prefix == 'B') {
                        String longToParse = s.substring(prefixIndex + 2);

                        if (!checkUnderscore(longToParse)) return false;
                        longToParse = longToParse.replace("_", "");

                        if (s.charAt(0) == '-') longToParse = '-' + longToParse;

                        equation.add(new EquationNumber(new BigInteger(longToParse, 2).longValue()));
                        return true;
                    }
                    // hexadecimal
                    else if (prefix == 'x' || prefix == 'X') {
                        if (!checkUnderscore(s.substring(prefixIndex + 2))) return false;

                        equation.add(new EquationNumber(Long.decode(s.replace("_", ""))));
                        return true;
                    }
                    // octa
                    else {
                        equation.add(new EquationNumber(Long.decode(s.replace("_", ""))));
                        return true;
                    }
                }

            }
            // decimal or integer
            if (!checkUnderscore(s.substring(prefixIndex))) return false;
            s = s.replace("_","");

            if (s.matches(DECIMAL_OR_INT_REGEX)) {
                equation.add(new EquationNumber(Double.valueOf(s)));
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

    private static boolean checkOperator(String s, List<Element> equation) {
        if (s.isEmpty()) return false;
        else if (s.matches(OPERATION_REGEX)) {
            equation.add(new Operator(s));
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean checkUnderscore(String s) {
        return !s.contains("_.") && checkUnderscoreAtTheBegAndTheEnd(s);
    }

    private static boolean checkUnderscoreAtTheBegAndTheEnd(String s) {
        if (s.length() == 0) return false;
        if (s.startsWith("_") || s.endsWith("_")) return false;
        return true;
    }
}