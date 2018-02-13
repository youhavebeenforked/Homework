package ru.sberbank.homework.dergun.hw1.exeptions;

public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException(String message) {
        super(message);
    }
}
