package ru.sberbank.homework.dergun.hw1;

import ru.sberbank.homework.dergun.hw1.exeptions.ValidatePinExeption;

public class PinValidator {
    public void validPin(int pin) {
        if (pin > 9999 || pin < 1000) {
            throw new ValidatePinExeption("Incorrect format PIN, expected: ****");
        }
    }
}

