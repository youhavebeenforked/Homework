package ru.sberbank.homework.abzaltdinov.first.exception;

public class UnsupportedCashAmountException extends RuntimeException {
    public UnsupportedCashAmountException(String message) {
        super(message);
    }
}
