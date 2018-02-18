package ru.sberbank.homework.kashin.main.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateHelper {

    public static final String BINARY_NUMBER = "^(\\+|-)?(0b)(0|1)+(l)?$";
    public static final String OCTAL_NUMBER = "^(\\+|-)?(0)([0-7])+(l)?$";
    public static final String HEX_NUMBER = "^(\\+|-)?(0x)([0-9]|[a-f])+(l)?$";
    public static final String CORRECT_LITERAL_REG_EXP = "^(\\+|-)?(0b|0x)?(\\d|[a-f])+(.(\\d|[a-f])+)?(l)?$";

    public static boolean checkWithRegExp(String checkExp, String regExp) {
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(checkExp);
        return m.matches();
    }

    public static boolean checkIncorrectUnderscore(String element) {
        return (element.startsWith("_") || element.endsWith("_") || element.contains("_.") || element.contains("._") || element.startsWith("0b_") || element.startsWith("0x_") || element.contains("_l"));
    }

    public static boolean checkIncorrectOctal(String element) {
        return (element.startsWith("0") && !element.startsWith("0b") && !element.startsWith("0x") && !checkWithRegExp(element, OCTAL_NUMBER) && !element.startsWith("0."));
    }

    public static String checkEndWithL(String element) {
        return element.endsWith("l") ? element.substring(0, element.length() - 1) : element;
    }
}
