package ru.sberbank.homework.solovyov.task1.exceptions;

public class ServerConnectionException extends RuntimeException{
    public ServerConnectionException(String message) {
        super(message);
    }
}
