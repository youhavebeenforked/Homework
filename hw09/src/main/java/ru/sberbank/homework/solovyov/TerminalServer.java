package ru.sberbank.homework.solovyov;

import ru.sberbank.homework.solovyov.exceptions.ServerConnectionException;

import java.util.Random;

public class TerminalServer {
    private static final int NETWORK_CONNECTION_QUALITY = 95; //в %

    private final Random random = new Random();
    private int moneyAmount = 100500;


    public int getAccountInfo() {
        if (hasConnectionProblem()) {
            throw new ServerConnectionException("Unable to connect the server");
        }
        return moneyAmount;
    }

    public void replenish(int inputCashAmount) {
        if (hasConnectionProblem()) {
            throw new ServerConnectionException("Unable to connect the server");
        }
        moneyAmount += inputCashAmount;
    }

    public void withdraw(int outputCashAmount) {
        if (hasConnectionProblem()) {
            throw new ServerConnectionException("Unable to connect the server");
        }
        moneyAmount -= outputCashAmount;
    }

    private boolean hasConnectionProblem() {
        return random.nextInt(100) > NETWORK_CONNECTION_QUALITY;
    }

}
