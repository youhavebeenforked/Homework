package ru.sberbank.homework.dergun.hw1;

import ru.sberbank.homework.dergun.hw1.exeptions.InsufficientFundsExeption;
import ru.sberbank.homework.dergun.hw1.exeptions.NetworkConnectionExeption;
import ru.sberbank.homework.dergun.hw1.exeptions.NotCorrectPinExeption;

import java.util.Random;

public class TerminalServer {
    private final int NETWORK_QUALITY = 99;
    private int bankBook = 10200;
    private final Random random = new Random();
    private PinStorage pinStorage;

    public void checkPin(int pin) {
        if (hasNetworkProblem()) {
            throw new NetworkConnectionExeption("Couldn't connect to the network");
        }
        if (pin != pinStorage.getPin()) {
            throw new NotCorrectPinExeption("Not the correct PIN");
        }
    }

    public void setPinStorage(PinStorage pinStorage) {
        this.pinStorage = pinStorage;
    }

    public int getBankBook() {
        if (hasNetworkProblem()) {
            throw new NetworkConnectionExeption("Couldn't connect to the network");
        }
        return bankBook;
    }

    public void withdrawMoney(int money) {
        if (hasNetworkProblem()) {
            throw new NetworkConnectionExeption("Couldn't connect to the network");
        }
        if (money > bankBook) {
            throw new InsufficientFundsExeption("Your account has insufficient funds");
        }
        bankBook -= money;
    }

    public void putMoney(int money) {
        if (hasNetworkProblem()) {
            throw new NetworkConnectionExeption("Couldn't connect to the network");
        }
        bankBook += money;
    }

    private boolean hasNetworkProblem() {
        final int num = random.nextInt();
        return num % NETWORK_QUALITY == 0;
    }

}

