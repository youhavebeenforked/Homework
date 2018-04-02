package ru.sberbank.homework.solovyov;

import ru.sberbank.homework.solovyov.exceptions.AccountIsLockedException;
import ru.sberbank.homework.solovyov.exceptions.PinFormatException;

import java.util.Date;

public class PinValidator {

    private static final int ATTEMPTS_NUMBER = 3;
    private static final Long BLOCKED_TIME = 5000L;

    private PinVerificationInfoStorage verificationInfoStorage = new PinVerificationInfoStorage();


    public boolean validatePin(String inputPin) {

        int intPin = Integer.parseInt(inputPin);

        if (intPin > 9999 || intPin < 1000) {
            throw new PinFormatException("Incorrect Pin format");
        }

        long accountUnblockTime = verificationInfoStorage.getAccountUnblockTime();
        if (accountUnblockTime != 0) {
            if (new Date().getTime() > accountUnblockTime) {
                verificationInfoStorage.setUserAttemptsNumber(0);
                verificationInfoStorage.setAccountUnblockTime(0);
            } else {
                throw new AccountIsLockedException(Long.toString(accountUnblockTime - new Date().getTime()));
            }
        }

        if (inputPin.hashCode() == verificationInfoStorage.getVerificationInfo()) {
            verificationInfoStorage.setUserAttemptsNumber(0);
            return true;
        } else {
            verificationInfoStorage.incUserAttemptsNumber();
            if (verificationInfoStorage.getUserAttemptsNumber() == ATTEMPTS_NUMBER) {
                verificationInfoStorage.setAccountUnblockTime(new Date().getTime() + BLOCKED_TIME);
                throw new AccountIsLockedException(Long.toString(verificationInfoStorage.getAccountUnblockTime() - new Date().getTime()));
            }
            return false;
        }
    }
}
