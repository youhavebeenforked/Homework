package ru.sberbank.homework.gavrilov.hw03_1.exceptions;

public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException(String message) {
        super(message);
    }
}
