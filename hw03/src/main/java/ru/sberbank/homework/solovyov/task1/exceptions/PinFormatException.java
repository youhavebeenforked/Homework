package ru.sberbank.homework.solovyov.task1.exceptions;

import java.io.IOException;

public class PinFormatException extends NumberFormatException {
    public PinFormatException(String message) {
        super(message);
    }
}
