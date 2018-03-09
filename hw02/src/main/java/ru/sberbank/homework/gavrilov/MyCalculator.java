package ru.sberbank.homework.gavrilov;

import ru.sberbank.homework.common.Calculator;
import ru.sberbank.homework.gavrilov.utils.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MyCalculator implements Calculator {

    /**
     * The constant for the error expression.
     */
    private static final String WRONG_LINE = "error > wrong expression";

    /**
     * The constant for save result of expression.
     */
    private BigDecimal resultBigDec;

    /**
     * Processes user input.
     * Commands examples:
     * 0345 * 0b10101
     * / 1.04
     *
     * @param userInput user command.
     * @return Formatted calculation result.
     */
    @Override
    public String calculate(String userInput) {
        Validator validator = new Validator();
        BigDecimal first = null;
        BigDecimal second = null;
        if (!validator.isValidCommand(userInput)) {
            resultBigDec = null;
            return WRONG_LINE;
        }

        try {
            if (validator.getUsersCommand().length == 2) {
                if (resultBigDec == null) {
                    throw new NumberFormatException(WRONG_LINE);
                }
                first = resultBigDec;
                second = validator.parseNumber(validator.getUsersCommand()[1]);
            }
            if (validator.getUsersCommand().length == 3) {
                first = validator.parseNumber(validator.getUsersCommand()[0]);
                second = validator.parseNumber(validator.getUsersCommand()[2]);
            }
            resultBigDec = validator.execute().calculating(first, second);
        } catch (ArithmeticException | NumberFormatException exp) {
            resultBigDec = null;
            return exp.getMessage();
        }
        return printDecFormat(resultBigDec);
    }

    /**
     * Formats an number to produce a string.
     *
     * @param number The number to format.
     * @return Formatted string.
     */
    private String printDecFormat(Number number) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("#.##", otherSymbols);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(number);
    }
}
