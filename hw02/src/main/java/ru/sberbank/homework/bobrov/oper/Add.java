package ru.sberbank.homework.bobrov.oper;

/**
 * Improved calculator.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 13.02.2018
 */


public class Add implements Operation {
    @Override
    public String calculate(double first, double second) {
        return String.valueOf(first + second);
    }
}
