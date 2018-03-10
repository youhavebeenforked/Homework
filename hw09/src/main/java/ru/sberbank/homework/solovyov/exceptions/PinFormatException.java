package ru.sberbank.homework.solovyov.exceptions;

import java.io.IOException;

public class PinFormatException extends NumberFormatException {
    public PinFormatException(String message) {
        super(message);
    }
}
