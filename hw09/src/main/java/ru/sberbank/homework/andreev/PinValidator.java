package ru.sberbank.homework.andreev;

import java.util.Map;

public class PinValidator {
    private Map<String, String> pinCode;

    public PinValidator(Map<String, String> pinCode) {
        this.pinCode = pinCode;
    }

    public boolean validate(String cardNumber, String pin) {
        Checker checker = new Checker(cardNumber, pin, pinCode);
        checker.checkParameterCorrectness();
        checker.checkExistence();
        return pinCode.get(cardNumber).equals(pin);
    }
}
