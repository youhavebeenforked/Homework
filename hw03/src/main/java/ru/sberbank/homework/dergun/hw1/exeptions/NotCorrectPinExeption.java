package ru.sberbank.homework.dergun.hw1.exeptions;

public class NotCorrectPinExeption extends RuntimeException {
    public NotCorrectPinExeption(String message) {
        super(message);
    }
}
