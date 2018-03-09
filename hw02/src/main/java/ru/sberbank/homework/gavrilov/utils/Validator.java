package ru.sberbank.homework.gavrilov.utils;

import ru.sberbank.homework.gavrilov.operation.EnumOperation;
import ru.sberbank.homework.gavrilov.operation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /**
     * The constant used for errors in literals.
     */
    private static final String WRONG_LITERAL = "error > ";
    /**
     * Regular expression for all literal of numbers that are not supported.
     */
    private static final Pattern NOT_SUPPORT_LITERAL_NUMBER =
            Pattern.compile("(^_.*)|(.*_$)|(.*_x.*)|(.*x_.*)|(.*_\\..*)|(.*\\._.*)|(.*_b.*)|(.*b_.*)|(.*_f$)|(.*_l$)");

    /**
     * Arithmetic operations of Enum.
     */
    private EnumOperation enumOperation;

    /**
     * The slitted user expression.
     */
    private String[] usersCommand;

    /**
     * Checking command is valid.
     * It should not process commands other than expressions a_@_b и @_b
     * If an incorrect command is entered,
     * there should be a value "error > wrong expression"
     *
     * @return true, if entered command a_@_b и @_b otherwise - false;
     */
    public boolean isValidCommand(String userInput) {
        if (userInput == null) {
            return false;
        }
        usersCommand = userInput.trim().toLowerCase(Locale.ENGLISH).split(" ");
        if (usersCommand.length == 2) {
            return setValidOperation(usersCommand[0]);
        }
        return usersCommand.length == 3 && setValidOperation(usersCommand[1]);
    }

    /**
     * Parses the string argument as a BigDecimal.
     *
     * @param number the string to be parsed.
     * @return the BigDecimal value represented by the string argument.
     */
    public BigDecimal parseNumber(String number) {
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
    private double parserDouble(String number) {
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
    private long parseLong(String number) {
        if (number.startsWith("-")) {
            number = number.substring(1);
            return number.startsWith("0b")
                    ? -new BigInteger(number.replaceFirst("0b", ""), 2).longValue()
                    : -Long.decode(number);
        }
        return number.startsWith("0b")
                ? new BigInteger(number.substring(2), 2).longValue() : Long.decode(number);
    }

    /**
     * Setting is validate operation.
     * If contained the operation is in EnumOperation, then the operation is valid .
     *
     * @param operation the symbol enumOperation.
     * @return true if enumOperation exist on ENUM EnumOperation, otherwise - false.
     */
    private boolean setValidOperation(String operation) {
        for (EnumOperation enumOperation : EnumOperation.values()) {
            if (enumOperation.getOperation() == operation.charAt(0)) {
                this.enumOperation = enumOperation;
                return true;
            }
        }
        return false;
    }

    /**
     * Return entered user command.
     *
     * @return the string array of splitted user expression.
     */
    public String[] getUsersCommand() {
        return usersCommand;
    }

    public Operation execute() {
        switch (enumOperation) {
            case ADDITION:
                return new Addition();
            case SUBTRACT:
                return new Subtraction();
            case MULTIPLY:
                return new Multiplication();
            case DIVIDE:
                return new Division();
            default:
                throw new UnsupportedOperationException("Not exist the operation!");
        }
    }
}
