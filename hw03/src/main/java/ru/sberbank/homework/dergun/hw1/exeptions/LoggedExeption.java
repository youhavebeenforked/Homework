package ru.sberbank.homework.dergun.hw1.exeptions;

public class LoggedExeption extends RuntimeException {
    public LoggedExeption() {
        super("Not logged in.");
    }
}
