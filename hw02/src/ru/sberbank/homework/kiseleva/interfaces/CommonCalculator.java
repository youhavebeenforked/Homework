package ru.sberbank.homework.kiseleva.interfaces;

/**
 * Created by Ekaterina Kiseleva on 08.02.2018.
 */
public interface CommonCalculator {

    public Number addition(Number one, Number two);

    public Number subtraction(Number one, Number two);

    public Number multiplication(Number one, Number two);

    public Number division(Number one, Number two);

    public Number cast(String num);
}
