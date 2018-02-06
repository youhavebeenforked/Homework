package ru.sberbank.homework.Abzaltdinov;

public class DivideByZeroException extends RuntimeException {
    DivideByZeroException() {
        super("Divided by zero!");
    }
}