package ru.sberbank.homework.drozdov.hw3_1;

/**
 * Created by Gleb on 19.02.2018
 */
public class PinValidator {
    private static final String DEFAULT_PASSWORD = "0000";
    private static final int MAX_ATTEMPTS_ALLOWED = 3;
    private static final long MILLISECONDS_IN_SECOND = 1000;
    private static final long BLOCKING_SECONDS = 5;
    private int pinAttempts;
    private long startBlockingTime;

    public boolean checkPin(String pin) {
        if (!checkBlock()) {
            return false;
        }
        pinAttempts++;
        if (pinAttempts > MAX_ATTEMPTS_ALLOWED) {
            startBlockingTime = System.currentTimeMillis();
            pinAttempts = 0;
            throw new AccountIsLockedException("Аккаунт заблокирован на 5 секунд");
        } else {
            boolean isCorrectPin = pin.equals(DEFAULT_PASSWORD);
            if (isCorrectPin) {
                pinAttempts = 0;
            }
            return isCorrectPin;
        }
    }

    private boolean checkBlock() {
        if (startBlockingTime + BLOCKING_SECONDS * MILLISECONDS_IN_SECOND > System.currentTimeMillis()) {
            throw new AccountIsLockedException(String.format("Аккаунт заблокирован, до разблокировки осталось %s cекунд", String.valueOf(BLOCKING_SECONDS - (System.currentTimeMillis() - startBlockingTime) / MILLISECONDS_IN_SECOND)));
        } else {
            return true;
        }
    }
}
