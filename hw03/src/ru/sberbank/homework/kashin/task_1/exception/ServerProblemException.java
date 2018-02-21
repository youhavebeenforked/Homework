package ru.sberbank.homework.kashin.task_1.exception;

public class ServerProblemException extends RuntimeException {
    public ServerProblemException(String message) {
        super(message);
    }
}
