package ru.sberbank.homework.gavrilov.hw03_1.exceptions;

public class NotExistCardNumberException extends NotExistAccountException {
    public NotExistCardNumberException(String message) {
        super(message);
    }
}
