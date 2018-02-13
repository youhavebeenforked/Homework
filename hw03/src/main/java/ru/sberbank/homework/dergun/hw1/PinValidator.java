package ru.sberbank.homework.dergun.hw1;

public class PinValidator {
    public boolean validPin(int pin) {
        if (pin > 9999 || pin < 1000) {
            throw new FailedValidatePin("Incorrect format PIN, expected: ****");
        }
        return true;
    }
}

class FailedValidatePin extends RuntimeException {
    public FailedValidatePin(String message) {
        super(message);
    }
}
