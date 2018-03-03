package ru.sberbank.homework.kuznecov.first;

public class AccountIsLockedException extends RuntimeException {

    public AccountIsLockedException(String message) {
        super(message);
    }
}
