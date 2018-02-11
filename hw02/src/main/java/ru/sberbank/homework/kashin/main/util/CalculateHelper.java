package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.exception.WrongExpression;
import ru.sberbank.homework.kashin.main.model.Expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

public class CalculateHelper {
    private static Double preResult;

    public static Double getPreResult() {
        return preResult;
    }

    public static void setPreResult(Double preResult) {
        CalculateHelper.preResult = preResult;
    }

    public static Double checkNotation(String number) {
        String result = number;

        if (checkWithRegExp(number, "^-?(0b|0B)(0|1)+$")) {
            result = String.valueOf(Integer.parseInt(number.substring(2, number.length()), 2));
        } else if (checkWithRegExp(number, "^-?(0)([0-7])+$")) {
            result = String.valueOf(Integer.parseInt(number.substring(1, number.length()), 8));
        } else if (checkWithRegExp(number, "^-?(0x|0X)([0-9]|[a-f]|[A-F])+$")) {
            result = String.valueOf(Integer.parseInt(number.substring(2, number.length()), 16));
        }
        return Double.valueOf(result);
    }

    public static Expression parser(String expString) {
        String[] elements = expString.split(" ");
        if (!checkWithRegExp(elements[0], "^-?(0b|0x)?\\d+(.\\d+)?$")) {
            throw new WrongExpression(String.format("error > %s", elements[0]));
        }
        if (!checkWithRegExp(elements[2], "^-?(0b|0x)?\\d+(.\\d+)?$")) {
            throw new WrongExpression(String.format("error > %s", elements[2]));
        }
        Expression expression = Factory.getExpression(elements[1].charAt(0));
        expression.setFirst(checkNotation(elements[0]));
        expression.setSecond(checkNotation(elements[2]));
        return expression;

    }

    public static boolean checkWithRegExp(String checkExp, String regExp){
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(checkExp);
        return m.matches();
    }

    public static boolean checkInteger(double checkNumber) {
        if ((checkNumber == Math.floor(checkNumber)) && !Double.isInfinite(checkNumber)) {
            return true;
        } else {
            return false;
        }
    }
}
