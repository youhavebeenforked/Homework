package ru.sberbank.homework.kiseleva;

/**
 * Created by Ekaterina Kiseleva on 11.02.2018.
 */
public class LongCalculator implements CommonCalculator {
    @Override
    public Long addition(String one, String two) {
        return Long.parseLong(one) + Long.parseLong(two);
    }

    @Override
    public Long subtraction(String one, String two) {
        return Long.parseLong(one) - Long.parseLong(two);
    }

    @Override
    public Long multiplication(String one, String two) {
        return Long.parseLong(one) * Long.parseLong(two);
    }

    @Override
    public Long division(String one, String two) {
        return Long.parseLong(one) / Long.parseLong(two);
    }
}
