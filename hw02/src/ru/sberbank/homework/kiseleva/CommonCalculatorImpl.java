package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.interfaces.CommonCalculator;

/**
 * Created by Ekaterina Kiseleva on 11.02.2018.
 */
public class CommonCalculatorImpl implements CommonCalculator {
    @Override
    public Double sum(Number one, Number two) {
        return one.doubleValue() + two.doubleValue();
    }

    @Override
    public Double subtract(Number one, Number two) {
        return one.doubleValue() - two.doubleValue();
    }

    @Override
    public Double multiply(Number one, Number two) {
        return one.doubleValue() * two.doubleValue();
    }

    @Override
    public Double divide(Number one, Number two) {
        return one.doubleValue() / two.doubleValue();
    }

    @Override
    public Number cast(String num) {
        num = num.toLowerCase();

        if (num.contains("_")) {
            num = num.replaceAll("_", "");
        }

        if (num.contains(".") || num.contains("e") || num.contains("p") || num.contains("d") || num.contains("f")) { //вещественный литерал
            if (num.charAt(num.length() - 1) == 'f' || num.charAt(num.length() - 1) == 'd') {
                num = num.substring(0, num.length() - 1);
            }
            return Double.parseDouble(num);
        } else { //целочисленный литерал
            if (num.charAt(num.length() - 1) == 'l') { //L
                num = num.substring(0, num.length() - 1);
            }
            if (num.length() > 1 && num.charAt(1) == 'b') {
                return Long.parseLong(num.substring(2), 2);
            } else if (num.length() > 1 && num.charAt(1) == 'x') {
                return Long.parseLong(num.substring(2), 16);
            } else if (num.length() > 1 && num.charAt(0) == '0') {
                return Long.parseLong(num.substring(1), 8);
            } else {
                return Long.parseLong(num);
            }
        }
    }
}
