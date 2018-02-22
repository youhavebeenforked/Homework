package ru.sberbank.homework.andreev.first;

import java.util.Map;

public class PinValidator {
    private Map<String, String> pinCode;

    public PinValidator(Map<String, String> pinCode) {
        this.pinCode = pinCode;
    }

    public boolean validate(String cardNumber, String pin) {
        Checker.checkCardNumberLength(cardNumber);
        Checker.checkPinLength(pin);
        Checker.checkKeyContain(cardNumber, pinCode, "Your card number miss in our base");
        return pinCode.get(cardNumber).equals(pin);
    }
}
