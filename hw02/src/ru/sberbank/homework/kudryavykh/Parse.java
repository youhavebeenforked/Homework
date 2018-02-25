package ru.sberbank.homework.kudryavykh;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {

    private static int decodeOnDecimal(String text) {

        return text.toLowerCase().startsWith("0b") ? Integer.parseInt(text.substring(2), 2) : Integer.decode(text);
    }

    private static boolean checkWithRegExp(String userNameString, String regExp) {
        Pattern pat = Pattern.compile(regExp);
        Matcher mat = pat.matcher(userNameString);
        return mat.matches();
    }

    public static String output (String value) {

        double number = Math.rint(10.0 * Double.parseDouble(value)) / 10.0;
        if((long)number == number) {
            number = (long)number;
        }
        return String.valueOf(number);
    }
    
    public static String validationEndParser(String word) {

        word = word.toLowerCase();
        double number;
        if (checkWithRegExp(word, "[+*/-]")) { //Проверка на знак
            return word;
        } else if (checkWithRegExp(word, "[0-9]+\\.[0-9]+")) { //проверка double
            number = Double.parseDouble(word);
            return String.valueOf(number);
        } else if (checkWithRegExp(word, "[0-9]+\\.[0-9]+f|[0-9]+f")) { //Проверка float литерала
            number = Float.parseFloat(word.substring(0, word.length() - 1));
            return String.valueOf(number);
        } else if (checkWithRegExp(word, "[0-9]+l")) { //Проверка long литерала
            number = Long.parseLong(word.substring(0, word.length() - 1));
            return String.valueOf(number);
        } else if (checkWithRegExp(word, "0b[0-1]+|0[0-7]+|0x[1-9abcdef]+|[0-9]+")) { //проверка int во
            number = decodeOnDecimal(word);                                                       //всех системах счисления
            return String.valueOf(number);
        } else if (word.equals("quit")) {
            System.exit(0);
        } else {
            return null;
        }
        return null;
    }
}
