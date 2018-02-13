package ru.sberbank.homework.dergun.hw1.exeptions;

public class InsufficientFundsExeption extends RuntimeException {
    public InsufficientFundsExeption(String message) {
        super(message);
    }
}
