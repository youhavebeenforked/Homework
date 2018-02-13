package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.exception.WrongExpression;
import ru.sberbank.homework.kashin.main.model.Expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateHelper {
    private static final String binaryNumber = "^-?(0b)(0|1)+(l)?$";
    private static final String octalNumber = "^-?(0)([0-7])+(l)?$";
    private static final String hexNumber = "^-?(0x)([0-9]|[a-f])+(l)?$";
    private static final String correctLiteralRegExp = "^(-)?(0b|0x)?(\\d|[a-f])+(.(\\d|[a-f])+)?(l)?$";
    private static String firstOriginalLit;
    private static String secondOriginalLit;
    private static Double preResult;

    public static Double getPreResult() {
        return preResult;
    }

    public static void setPreResult(Double preResult) {
        CalculateHelper.preResult = preResult;
    }

    public static Double checkNotation(String number) {
        if (number.endsWith("l")) {
            number = number.substring(0, number.length() - 1);
        }
        if (checkWithRegExp(number, binaryNumber)) {
            number = BinaryToDecimal(number);
        } else if (checkWithRegExp(number, octalNumber)) {
            number = String.valueOf(Long.parseLong(number.substring(1, number.length()), 8));
        } else if (checkWithRegExp(number, hexNumber)) {
            number = String.valueOf(Long.parseLong(number.substring(2, number.length()), 16));
        }
        return Double.valueOf(number);
    }

    public static Expression parser(String expString) {
        String[] elements = expString.split(" ");
        firstOriginalLit = elements[0];
        secondOriginalLit = elements[2];
        String firstElement = elements[0].toLowerCase();
        String secondElement = elements[2].toLowerCase();
        if (checkIncorrectUnderscore(firstElement)) {
            throw new WrongExpression(String.format("error > %s", firstOriginalLit));
        }
        if (checkIncorrectUnderscore(secondElement)) {
            throw new WrongExpression(String.format("error > %s", secondOriginalLit));
        }
        firstElement = firstElement.replaceAll("_", "");
        secondElement = secondElement.replaceAll("_", "");
        if (!checkWithRegExp(firstElement, correctLiteralRegExp) || checkIncorrectOctal(firstElement)) {
            throw new WrongExpression(String.format("error > %s", firstOriginalLit));
        }
        if (!checkWithRegExp(secondElement, correctLiteralRegExp) || checkIncorrectOctal(secondElement)) {
            throw new WrongExpression(String.format("error > %s", secondOriginalLit));
        }
        Expression expression = Factory.getExpression(elements[1].charAt(0));
        expression.setFirst(checkNotation(firstElement));
        expression.setSecond(checkNotation(secondElement));
        return expression;

    }

    public static boolean checkWithRegExp(String checkExp, String regExp) {
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(checkExp);
        return m.matches();
    }

    public static boolean checkInteger(double checkNumber) {
        return (checkNumber == Math.floor(checkNumber)) && !Double.isInfinite(checkNumber);
    }

    public static boolean checkIncorrectUnderscore(String element) {
        return (element.startsWith("_") || element.endsWith("_") || element.contains("_.") ||element.contains("._") || element.startsWith("0b_") || element.startsWith("0x_"));
    }

    public static boolean checkIncorrectOctal(String element) {
        return (element.startsWith("0") && !element.startsWith("0b") && !element.startsWith("0x") && !checkWithRegExp(element, octalNumber));
    }


    public static String BinaryToDecimal(String base2) {
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
}
