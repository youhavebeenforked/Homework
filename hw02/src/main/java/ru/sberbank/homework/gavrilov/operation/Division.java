package ru.sberbank.homework.gavrilov.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Operation the quotient of the arguments.
 */
public class Division implements Operation {

    /**
     * Returns the quotient of the arguments.
     *
     * @param first  the first value - the dividend.
     * @param second the first value - the divisor.
     * @return the result.
     */
    @Override
    public BigDecimal calculating(BigDecimal first, BigDecimal second) {
        try {
            return first.divide(second, 6, RoundingMode.HALF_UP);
        } catch (ArithmeticException ae) {
            System.out.println("Divide by zero is forbidden!");
            throw ae;
        }
    }
}
