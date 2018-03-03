package ru.sberbank.homework.gavrilov.hw03_1.exceptions;

public class NotExistAccountException extends RuntimeException {
    public NotExistAccountException(String message) {
        super(message);
    }
}
