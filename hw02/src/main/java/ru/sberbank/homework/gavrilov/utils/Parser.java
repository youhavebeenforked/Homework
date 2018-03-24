package ru.sberbank.homework.gavrilov.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final String WRONG_LITERAL = "error > ";

    /**
     * Regular expression for all literal of numbers that are not supported.
     */
    private static final Pattern NOT_SUPPORT_LITERAL_NUMBER = Pattern.compile(
            "(^_.*)|(.*_$)|(.*_x.*)|(.*x_.*)|(.*_\\..*)|(.*\\._.*)|(.*_b.*)|(.*b_.*)|(.*_f$)|(.*_l$)");

    /**
     * Parses the string argument as a BigDecimal.
     *
     * @param number the string to be parsed.
     * @return the BigDecimal value represented by the string argument.
     */
    public static BigDecimal parseNumber(String number) {
        BigDecimal result;
        Matcher m = NOT_SUPPORT_LITERAL_NUMBER.matcher(number);
        if (m.matches()) {
            throw new NumberFormatException(WRONG_LITERAL + number);
        }
        number = number.replaceAll("_", ""); // for supported number as 0x123__123_123
        if (number.startsWith("+")) {
            number = number.substring(1);
        }
        try {
            if (number.matches("^-?[0-9]*[.][0-9]+$")) {
                result = BigDecimal.valueOf(parserDouble(number));
            } else if (number.endsWith("l")) {
                result = BigDecimal.valueOf(parseLong(number.substring(0, number.length() - 1)));
            } else {
                result = BigDecimal.valueOf(parseLong(number));
            }
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(WRONG_LITERAL + number);
        }
        return result;
    }

    /**
     * Returns a new double initialized to the value
     * represented by the specified String.
     *
     * @param number the string to be parsed.
     * @return the double value represented by the string argument.
     */
    private static double parserDouble(String number) {
        if (number.startsWith("-")) {
            return -Double.parseDouble(number.substring(1));
        }
        return Double.parseDouble(number);
    }

    /**
     * Parses the string argument as a signed long.
     *
     * @param number the String containing the
     *               long representation to be parsed.
     * @return the long number represented by the string argument.
     */
    private static long parseLong(String number) {
        if (number.startsWith("-")) {
            number = number.substring(1);
            return number.startsWith("0b")
                    ? -new BigInteger(number.replaceFirst("0b", ""), 2).longValue()
                    : -Long.decode(number);
        }
        return number.startsWith("0b")
                ? new BigInteger(number.substring(2), 2).longValue() : Long.decode(number);
    }
}