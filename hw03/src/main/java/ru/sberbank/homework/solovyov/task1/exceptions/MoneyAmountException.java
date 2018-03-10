package ru.sberbank.homework.solovyov.task1.exceptions;

public class MoneyAmountException extends IllegalArgumentException{
    public MoneyAmountException(String message) {
        super(message);
    }
}
