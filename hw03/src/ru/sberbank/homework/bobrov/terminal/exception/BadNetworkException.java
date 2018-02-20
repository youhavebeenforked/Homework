package ru.sberbank.homework.bobrov.terminal.exception;


/**
 * Task terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 20.02.2018
 */


public class BadNetworkException extends RuntimeException {
    public BadNetworkException(String message) {
        super(message);
    }
}
