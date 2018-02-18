package ru.sberbank.homework.kashin.task_1.exception;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException() {
    }
}
