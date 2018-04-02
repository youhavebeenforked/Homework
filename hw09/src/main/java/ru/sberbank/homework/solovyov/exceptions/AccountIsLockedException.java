package ru.sberbank.homework.solovyov.exceptions;

public class AccountIsLockedException extends AccountException {
    public AccountIsLockedException(String message) {
        super(message);
    }
}
