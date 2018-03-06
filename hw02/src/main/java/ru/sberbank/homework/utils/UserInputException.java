package ru.sberbank.homework.utils;

public class UserInputException extends NumberFormatException {
    private static final String ERROR = "error > wrong expression";

    UserInputException() {
        super(ERROR);
    }

    UserInputException(String msg) {
        super(msg);
    }
}