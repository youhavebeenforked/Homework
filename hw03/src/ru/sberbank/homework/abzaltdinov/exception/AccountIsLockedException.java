package ru.sberbank.homework.abzaltdinov.exception;

public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException() {
        super();
    }

    public AccountIsLockedException(String message) {
        super(message);
    }
}
