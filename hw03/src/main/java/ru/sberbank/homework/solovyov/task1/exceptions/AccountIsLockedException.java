package ru.sberbank.homework.solovyov.task1.exceptions;

public class AccountIsLockedException extends AccountException {
    public AccountIsLockedException(String message) {
        super(message);
    }
}
