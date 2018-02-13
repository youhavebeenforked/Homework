package ru.sberbank.homework.kashin.task_1.exception;

public class InvalidPinCodeException extends RuntimeException {
    public InvalidPinCodeException(String message) {
        super(message);
    }
}
