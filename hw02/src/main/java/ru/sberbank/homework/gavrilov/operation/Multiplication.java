package ru.sberbank.homework.gavrilov.operation;

import java.math.BigDecimal;

/**
 * Operation the product of the arguments.
 */
public class Multiplication implements Operation {

    /**
     * Returns the product of the arguments.
     *
     * @param first  the first value.
     * @param second the second value.
     * @return the result.
     */
    @Override
    public BigDecimal calculating(BigDecimal first, BigDecimal second) {
        return first.multiply(second);
    }
}
