package ru.sberbank.homework.abzaltdinov.first.exception;

public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException() {
        super();
    }

    public AccountIsLockedException(String message) {
        super(message);
    }
}
