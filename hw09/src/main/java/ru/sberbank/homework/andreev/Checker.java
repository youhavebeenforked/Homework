package ru.sberbank.homework.andreev;

import java.util.Map;
import java.util.Objects;

public class Checker {

    private static final int PIN_LENGTH = 4;
    private static final int MIN_CARD_NUMBER_LENGTH = 12;
    private static final int MAX_CARD_NUMBER_LENGTH = 19;

    private String cardNumber;
    private String pin;
    private Map map;

    public Checker(String cardNumber, String pin, Map map) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.map = map;
    }

    public Checker(String cardNumber, Map map) {
        this.cardNumber = cardNumber;
        this.map = map;
    }

    public void checkParameterCorrectness() {
        if (Objects.nonNull(pin)) {
            checkContainNumber(pin);
            checkPinLength();
        }
        checkContainNumber(cardNumber);
        checkCardNumberLength();
    }

    public void checkExistence() {
        if (!map.containsKey(cardNumber)) {
            throw new TerminalException("Card number " + cardNumber + " don't exist in our base");
        }
    }

    private static void checkContainNumber(String input) {
        if (!input.matches("\\d+")) {
            throw new TerminalException("Your input contain not only digits " + input);
        }
    }

    private void checkPinLength() {
        if (!(pin.length() == PIN_LENGTH)) {
            throw new TerminalException("Number of digits in pin code must be 4. \n Your pin have " + pin.length());
        }
    }

    private void checkCardNumberLength() {
        if (cardNumber.length() < MIN_CARD_NUMBER_LENGTH || cardNumber.length() > MAX_CARD_NUMBER_LENGTH) {
            throw new TerminalException("Number of digits in card number must be above 12 and under 20 digits. \n Your card number have " + cardNumber.length());
        }
    }
}
