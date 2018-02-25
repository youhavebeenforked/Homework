package ru.sberbank.homework.kalugin;

public class TestFailedException extends RuntimeException {
    TestFailedException(String message) {
        super(message);
    }
}