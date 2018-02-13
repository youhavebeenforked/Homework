package ru.sberbank.homework.dergun.hw1.exeptions;

public class NetworkConnectionExeption extends RuntimeException {
    public NetworkConnectionExeption(String message) {
        super(message);
    }
}
