package ru.sberbank.homework.solovyov.exceptions;

public class ServerConnectionException extends RuntimeException{
    public ServerConnectionException(String message) {
        super(message);
    }
}
