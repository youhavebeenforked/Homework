package ru.sberbank.homework.kashin.task_1.exception;

public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException(String message) {
        super(message);
    }
}
