package ru.sberbank.homework.bobrov.terminal.exception;


/**
 * Task terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */


public class CheckPinException extends RuntimeException {
    public CheckPinException(String message) {
        super(message);
    }
}
