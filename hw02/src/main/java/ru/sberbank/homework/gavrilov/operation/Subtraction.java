package ru.sberbank.homework.gavrilov.operation;

import java.math.BigDecimal;

/**
 * Operation the difference of the arguments.
 */
public class Subtraction implements Operation {

    /**
     * Returns the difference of the arguments.
     *
     * @param first  the first value.
     * @param second the second value to subtract from the first.
     * @return the result.
     */
    @Override
    public BigDecimal calculating(BigDecimal first, BigDecimal second) {
        return first.subtract(second);
    }
}
