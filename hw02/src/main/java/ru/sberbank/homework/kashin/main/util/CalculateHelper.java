package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.exception.WrongExpression;
import ru.sberbank.homework.kashin.main.model.Expression;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateHelper {
    private static final String binaryNumber = "^(\\+|-)?(0b)(0|1)+(l)?$";
    private static final String octalNumber = "^(\\+|-)?(0)([0-7])+(l)?$";
    private static final String hexNumber = "^(\\+|-)?(0x)([0-9]|[a-f])+(l)?$";
    private static final String correctLiteralRegExp = "^(\\+|-)?(0b|0x)?(\\d|[a-f])+(.(\\d|[a-f])+)?(l)?$";
    private static Double preResult;
    private static Map<Integer, String> originalLiterals = new HashMap<>();

    public static Double getPreResult() {
        return preResult;
    }

    public static void setPreResult(Double preResult) {
        CalculateHelper.preResult = preResult;
    }

    private static Double checkNotation(String number, int item) {
        if (number.endsWith("l")) {
            number = number.substring(0, number.length() - 1);
        }
        if (checkWithRegExp(number, binaryNumber)) {
            try {
                if (number.startsWith("-")) {
                    number = "-" + BinaryToDecimal(number);
                } else {
                    number = BinaryToDecimal(number);
                }
            } catch (Exception e) {
                preResult = null;
                throw new WrongExpression(String.format("error > %s", originalLiterals.get(item)));
            }
        } else if (checkWithRegExp(number, octalNumber)) {
            try {
                if (number.startsWith("-")) {
                    number = "-" + String.valueOf(Long.parseLong(number.substring(1, number.length()), 8));
                } else {
                    number = String.valueOf(Long.parseLong(number.substring(1, number.length()), 8));
                }
            } catch (Exception e) {
                preResult = null;
                throw new WrongExpression(String.format("error > %s", originalLiterals.get(item)));
            }
        } else if (checkWithRegExp(number, hexNumber)) {
            try {
                if (number.startsWith("-")) {
                    number = "-" + String.valueOf(Long.parseLong(number.substring(3, number.length()), 16));
                } else if (number.startsWith("+")) {
                    number = String.valueOf(Long.parseLong(number.substring(3, number.length()), 16));
                } else {
                    number = String.valueOf(Long.parseLong(number.substring(2, number.length()), 16));
                }
            } catch (Exception e) {
                preResult = null;
                throw new WrongExpression(String.format("error > %s", originalLiterals.get(item)));
            }
        }
        try {
            return Double.valueOf(number);
        } catch (Exception e) {
            preResult = null;
            throw new WrongExpression(String.format("error > %s", originalLiterals.get(item)));
        }
    }

    public static Expression parser(String expString) {
        String[] elements = expString.split(" ");
        originalLiterals.put(1, elements[0]);
        originalLiterals.put(2, elements[2]);
        String firstElement = elements[0].toLowerCase();
        String secondElement = elements[2].toLowerCase();
        if (checkIncorrectUnderscore(firstElement)) {
            preResult = null;
            throw new WrongExpression(String.format("error > %s", originalLiterals.get(1)));
        }
        if (checkIncorrectUnderscore(secondElement)) {
            preResult = null;
            throw new WrongExpression(String.format("error > %s", originalLiterals.get(2)));
        }
        firstElement = firstElement.replaceAll("_", "");
        secondElement = secondElement.replaceAll("_", "");
        if (!checkWithRegExp(firstElement, correctLiteralRegExp) || checkIncorrectOctal(firstElement)) {
            preResult = null;
            throw new WrongExpression(String.format("error > %s", originalLiterals.get(1)));
        }
        if (!checkWithRegExp(secondElement, correctLiteralRegExp) || checkIncorrectOctal(secondElement)) {
            preResult = null;
            throw new WrongExpression(String.format("error > %s", originalLiterals.get(2)));
        }
        Expression expression = Factory.getExpression(elements[1].charAt(0));
        expression.setFirst(checkNotation(firstElement, 1));
        expression.setSecond(checkNotation(secondElement, 2));
        return expression;

    }

    public static boolean checkWithRegExp(String checkExp, String regExp) {
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(checkExp);
        return m.matches();
    }

    private static boolean checkIncorrectUnderscore(String element) {
        return (element.startsWith("_") || element.endsWith("_") || element.contains("_.") || element.contains("._") || element.startsWith("0b_") || element.startsWith("0x_") || element.contains("_l"));
    }

    private static boolean checkIncorrectOctal(String element) {
        return (element.startsWith("0") && !element.startsWith("0b") && !element.startsWith("0x") && !checkWithRegExp(element, octalNumber) && !element.startsWith("0."));
    }


    private static String BinaryToDecimal(String base2) {
        char[] chars = base2.toCharArray();
        Long result = 0L;
        int mult = 1;
        for (int i = base2.length() - 1; i >= 0; i--) {
            if (chars[i] == '1') {
                result += mult;
            }
            mult *= 2;
        }
        return result.toString().replaceAll("L", "");
    }

    public static void originalLiteralsClear() {
        originalLiterals.clear();
    }
}
