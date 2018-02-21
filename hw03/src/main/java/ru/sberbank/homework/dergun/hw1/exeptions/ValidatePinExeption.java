package ru.sberbank.homework.dergun.hw1.exeptions;

public class ValidatePinExeption extends RuntimeException {
    public ValidatePinExeption(String message) {
        super(message);
    }
}
