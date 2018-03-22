package ru.sberbank.homework.karaush.TerminalHW;

import java.util.Date;

public class PinValidator {

    private static final int MAX_ATTEMPTS_BEFORE_BLOCK = 3;
    private static final int BLOCK_DURATION_IN_MS = 5000;
    private final String pin;
    private int attemptsCounter;
    private boolean accessGranted;
    private long unblockTime;

    PinValidator(String pin) {
        this.pin = pin;
        accessGranted = false;
        attemptsCounter = 0;
    }

    boolean validate(String pin) {

        validateFormat(pin);

        if (new Date().getTime() < unblockTime) {
            throw new IllegalAccessException(Long.toString(unblockTime - System.currentTimeMillis()));
        }

        attemptsCounter++;
        if (attemptsCounter > MAX_ATTEMPTS_BEFORE_BLOCK) {
            attemptsCounter = 0;
            unblockTime = new Date().getTime() + BLOCK_DURATION_IN_MS;
            throw new IllegalAccessException(Long.toString(unblockTime - System.currentTimeMillis()));
        }

        accessGranted = pin.equals(this.pin);
        return accessGranted;
    }

    private void validateFormat(String pin) {
        if (!pin.matches("\\d{4}")) {
            throw new IncorrectPinFormatException("pin format is ****");
        }

    }
}
