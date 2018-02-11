package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.interfaces.CommonCalculator;

/**
 * Created by Ekaterina Kiseleva on 11.02.2018.
 */
public class DoubleCalculator implements CommonCalculator {
    @Override
    public Double addition(String one, String two) {
        return Double.parseDouble(one) + Double.parseDouble(two);
    }

    @Override
    public Double subtraction(String one, String two) {
        return Double.parseDouble(one) - Double.parseDouble(two);
    }

    @Override
    public Double multiplication(String one, String two) {
        return Double.parseDouble(one) * Double.parseDouble(two);
    }

    @Override
    public Double division(String one, String two) {
        return Double.parseDouble(one) / Double.parseDouble(two);
    }
}
