package ru.sberbank.homework.gavrilov.operation;

import java.math.BigDecimal;

/**
 * Operation the sum of its arguments.
 */
public class Addition implements Operation {

    /**
     * Returns the sum of its arguments.
     *
     * @param first  the first value.
     * @param second the second value.
     * @return the result.
     */
    @Override
    public BigDecimal calculating(BigDecimal first, BigDecimal second) {
        return first.add(second);
    }
}
