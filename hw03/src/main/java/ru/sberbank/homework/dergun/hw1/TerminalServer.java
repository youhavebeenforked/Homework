package ru.sberbank.homework.dergun.hw1;

import java.util.Random;

public class TerminalServer {
    private final int PIN = 4875;
    private final int NETWORK_QUALITY = 99;
    private int bankBook = 10200;
    private final Random random = new Random();

    public boolean checkPin(int pin) {
        if (networkProblem()) {
            throw new NetworkConnectionProblem("Couldn't connect to the network");
        }
        if (pin == this.PIN) {
            return true;
        }
        throw new NotCorrectPin("Not the correct PIN");
    }

    public int getBankBook() {
        if (networkProblem()) {
            throw new NetworkConnectionProblem("Couldn't connect to the network");
        }
        return bankBook;
    }

    public void withdrawMoney(int money) {
        if (networkProblem()) {
            throw new NetworkConnectionProblem("Couldn't connect to the network");
        }
        if (money > bankBook) {
            throw new InsufficientFunds("Your account has insufficient funds");
        }
        bankBook -= money;
    }

    public void putMoney(int money) {
        if (networkProblem()) {
            throw new NetworkConnectionProblem("Couldn't connect to the network");
        }
        bankBook += money;
    }

    private boolean networkProblem() {
        final int num = random.nextInt();
        return num % NETWORK_QUALITY == 0;
    }

}

class NetworkConnectionProblem extends RuntimeException {
    public NetworkConnectionProblem(String message) {
        super(message);
    }
}

class NotCorrectPin extends RuntimeException {
    public NotCorrectPin(String message) {
        super(message);
    }
}

class InsufficientFunds extends RuntimeException {
    public InsufficientFunds(String message) {
        super(message);
    }
}