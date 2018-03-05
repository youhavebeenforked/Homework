package ru.sberbank.homework.maruev.operations;

import ru.sberbank.homework.maruev.exceptions.DivisionException;

/**
 * Created by Иван on 15.02.2018.
 */
public class DivisionOperator implements Operator {
    @Override
    public Double getResult(double one, double two) {
        if (two != 0) {
            return one / two;
        } else {
            throw new DivisionException();
        }
    }
}
