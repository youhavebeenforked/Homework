package ru.sberbank.homework.andreev.first;

import java.util.Map;

public class Checker {

    public static void checkKeyContain(String key, Map map, String message) {
        if (!map.containsKey(key)) {
            throw new TerminalException(message);
        }
    }

    public static void checkPinLength(String pin) {
        if (!(pin.length() == 4)) {
            throw new TerminalException("Number of digits in pin code must be 4. \n Your pin have " + pin.length());
        }
    }

    public static void checkCardNumberLength(String cardNumber) {
        if (cardNumber.length() < 12 || cardNumber.length() > 19) {
            throw new TerminalException("Number of digits in card number must be above 12 and under 20 digits. \n Your card number have " + cardNumber.length());
        }
    }
}
