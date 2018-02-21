package ru.sberbank.homework.dergun.hw1.exeptions;

public class ValidateMoneyExeption extends RuntimeException {
    public ValidateMoneyExeption(String message) {
        super(message);
    }
}
