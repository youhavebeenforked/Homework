package ru.sberbank.homework.Abzaltdinov;

public class DivisionByZeroException extends RuntimeException {
    DivisionByZeroException() {
        super("Divided by zero!");
    }
}