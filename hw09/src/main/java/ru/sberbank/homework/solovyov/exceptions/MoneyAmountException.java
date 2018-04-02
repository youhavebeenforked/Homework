package ru.sberbank.homework.solovyov.exceptions;

public class MoneyAmountException extends IllegalArgumentException{
    public MoneyAmountException(String message) {
        super(message);
    }
}
