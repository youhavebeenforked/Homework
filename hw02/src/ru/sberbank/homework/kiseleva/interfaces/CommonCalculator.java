package ru.sberbank.homework.kiseleva;

/**
 * Created by Ekaterina Kiseleva on 08.02.2018.
 */
public interface CommonCalculator {

    public Number addition(String one, String two) throws IllegalArgumentException;

    public Number subtraction(String one, String two) throws IllegalArgumentException;

    public Number multiplication(String one, String two) throws IllegalArgumentException;

    public Number division(String one, String two) throws IllegalArgumentException;
}
