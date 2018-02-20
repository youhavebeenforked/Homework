package ru.sberbank.homework.maruev.hw3_1;

/**
 * Created by Иван on 20.02.2018.
 */
public class PinValidator {
    private static final String PIN_CODE = "0011";

    public boolean isValidPin(String inputPin) {
        return inputPin.equals(PIN_CODE);
    }
}
