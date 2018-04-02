package ru.sberbank.homework.maruev.hw3_1;

/**
 * Created by Иван on 20.02.2018.
 */
public class PinValidator {
    private static String PIN_CODE = "0011";

    public static void setPinCode(String pinCode) {
        PIN_CODE = pinCode;
    }

    public boolean isValidPin(String inputPin) {
        return inputPin.equals(PIN_CODE);
    }
}
