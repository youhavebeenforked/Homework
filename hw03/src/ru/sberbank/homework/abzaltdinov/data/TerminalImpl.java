package ru.sberbank.homework.abzaltdinov.data;

import ru.sberbank.homework.abzaltdinov.exception.AccountIsLockedException;
import ru.sberbank.homework.abzaltdinov.exception.Logger;

public class TerminalImpl implements Terminal {
    private static final long LOCK_TIME = 5000; // in milliseconds
    public static final int MAX_WRONG_PINCODES = 3;
    private static final TerminalServer server = new TerminalServer();
    private static final PinValidator pinValidator = new PinValidator();
    private boolean isValidated = false;
    private boolean isLocked = false;
    private long lockedUntil;
    private int userWrongPinCodesCount = 0;

    public boolean enterPin(int accountNumber, int pinCode) {
        try {
            checkForLock();
        } catch (AccountIsLockedException ex) {
            Logger.print(ex);
            return false;
        }
        if (pinValidator.validate(accountNumber, pinCode)) {
            isValidated = true;
            userWrongPinCodesCount = 0;
        } else {
            isValidated = false;
            userWrongPinCodesCount++;
            if (userWrongPinCodesCount == MAX_WRONG_PINCODES) {
                setLock();
            }
        }
        return isValidated;
    }

    public void withdrawRequest(int amount) {
        checkForLock();
        try {
            server.
        }
    }

    public void depositRequest(int amount) {

    }

    private void checkForLock() throws AccountIsLockedException {
        if (isLocked) {
            long timeForUnlock = (lockedUntil - System.currentTimeMillis()) / 1000;
            if (timeForUnlock > 0) {
                throw new AccountIsLockedException("Аккаунт заблокирован! Попробуйте через " + timeForUnlock + " секунд");
            }
        }
    }

    private void setLock() {
        isLocked = true;
        lockedUntil = System.currentTimeMillis() + LOCK_TIME;
    }

}
