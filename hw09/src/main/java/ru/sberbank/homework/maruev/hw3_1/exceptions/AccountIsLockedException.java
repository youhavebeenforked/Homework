package ru.sberbank.homework.maruev.hw3_1.exceptions;

/**
 * Created by Иван on 20.02.2018.
 */
public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException(String message) {
        super(message);
    }
}
