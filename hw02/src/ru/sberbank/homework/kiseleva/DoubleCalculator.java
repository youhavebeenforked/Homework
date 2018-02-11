package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.interfaces.CommonCalculator;

/**
 * Created by Ekaterina Kiseleva on 11.02.2018.
 */
public class DoubleCalculator implements CommonCalculator {
    @Override
    public Number addition(Number one, Number two) {
        return one.doubleValue() + two.doubleValue();
    }

    @Override
    public Number subtraction(Number one, Number two) {
        return one.doubleValue() - two.doubleValue();
    }

    @Override
    public Number multiplication(Number one, Number two) {
        return one.doubleValue() * two.doubleValue();
    }

    @Override
    public Number division(Number one, Number two) {
        return one.doubleValue() / two.doubleValue();
    }

    @Override
    public Number cast(String num) {
        if(num.length() > 1 && num.charAt(1) == 'b') {
            return Long.parseLong(num.substring(2), 2);
        }
        else if(num.length() > 1 && num.charAt(1) == 'x') {
            return Long.parseLong(num.substring(2), 16);
        }
        else if (num.length() > 1 && num.charAt(0) == '0' && num.charAt(1) != '.') {
            return Long.parseLong(num.substring(1), 8);
        }
        else {
            return Double.parseDouble(num);
        }
    }
}
