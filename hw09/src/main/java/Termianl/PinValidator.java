package Termianl;

import Termianl.exeptions.ValidatePinExeption;

public class PinValidator {
    public void validPin(int pin) {
        if (pin > 9999 || pin < 1000) {
            throw new ValidatePinExeption("Incorrect format PIN, expected: ****");
        }
    }
}

