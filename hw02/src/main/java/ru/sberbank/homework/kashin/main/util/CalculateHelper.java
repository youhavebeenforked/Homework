package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.exception.WrongExpression;
import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.kashin.main.model.Number;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateHelper {

    public static final String BINARY_NUMBER = "^(\\+|-)?(0b)(0|1)+(l)?$";
    public static final String OCTAL_NUMBER = "^(\\+|-)?(0)([0-7])+(l)?$";
    public static final String HEX_NUMBER = "^(\\+|-)?(0x)([0-9]|[a-f])+(l)?$";
    public static final String CORRECT_LITERAL_REG_EXP = "^(\\+|-)?(0b|0x)?(\\d|[a-f])+(.(\\d|[a-f])+)?(l)?$";

    private static Double preResult;
    private static Map<Integer, String> originalLiterals = new HashMap<>();

    public static Map<Integer, String> getOriginalLiterals() {
        return originalLiterals;
    }

    public static Double getPreResult() {
        return preResult;
    }

    public static void setPreResult(Double preResult) {
        CalculateHelper.preResult = preResult;
    }

    private static Double checkNotation(String number, int item) {
        number = checkEndWithL(number);

        Number num = FactoryNumber.getNumber(number);

        try {
            return Double.valueOf(num.checkAndParse(number, item));
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
        if (!checkWithRegExp(firstElement, CORRECT_LITERAL_REG_EXP) || checkIncorrectOctal(firstElement)) {
            preResult = null;
            throw new WrongExpression(String.format("error > %s", originalLiterals.get(1)));
        }
        if (!checkWithRegExp(secondElement, CORRECT_LITERAL_REG_EXP) || checkIncorrectOctal(secondElement)) {
            preResult = null;
            throw new WrongExpression(String.format("error > %s", originalLiterals.get(2)));
        }
        Expression expression = FactoryExpression.getExpression(elements[1].charAt(0));
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
        return (element.startsWith("0") && !element.startsWith("0b") && !element.startsWith("0x") && !checkWithRegExp(element, OCTAL_NUMBER) && !element.startsWith("0."));
    }

    private static String checkEndWithL(String element) {
        return element.endsWith("l") ? element.substring(0, element.length() - 1) : element;
    }


    public static void originalLiteralsClear() {
        originalLiterals.clear();
    }
}
