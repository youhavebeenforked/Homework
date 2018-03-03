package ru.sberbank.homework.gavrilov.operation;

import java.math.BigDecimal;

/**
 * Represents an various operation upon two operands of the BigDecimal type, producing a result
 * of the BigDecimal type.
 */
public interface Operation {

    /**
     * Applies this function to the given arguments.
     *
     * @param first  the first number.
     * @param second the second number.
     * @return the function result.
     */
    BigDecimal calculating(BigDecimal first, BigDecimal second);
}
