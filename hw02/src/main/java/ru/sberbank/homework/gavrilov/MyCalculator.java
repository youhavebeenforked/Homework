package ru.sberbank.homework.gavrilov;

import ru.sberbank.homework.common.Calculator;
import ru.sberbank.homework.gavrilov.utils.Helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static ru.sberbank.homework.gavrilov.utils.Parser.parseNumber;

public class MyCalculator implements Calculator {

    /**
     * The constant for the error expression.
     */
    private static final String WRONG_LINE = "error > wrong expression";

    /**
     * The constant for save result of expression.
     */
    private BigDecimal resultBigDec;

    private final Helper helper = new Helper();

    private final DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##", otherSymbols);

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
        BigDecimal first = null;
        BigDecimal second = null;
        if (!helper.isValidCommand(userInput)) {
            resultBigDec = null;
            return WRONG_LINE;
        }

        try {
            if (helper.getUsersCommand().length == 2) {
                if (resultBigDec == null) {
                    throw new NumberFormatException(WRONG_LINE);
                }
                first = resultBigDec;
                second = parseNumber(helper.getUsersCommand()[1]);
            }
            if (helper.getUsersCommand().length == 3) {
                first = parseNumber(helper.getUsersCommand()[0]);
                second = parseNumber(helper.getUsersCommand()[2]);
            }
            resultBigDec = helper.execute().calculating(first, second);
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
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(number);
    }
}
