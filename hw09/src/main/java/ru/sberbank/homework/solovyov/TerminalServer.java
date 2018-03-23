package ru.sberbank.homework.solovyov;

import ru.sberbank.homework.solovyov.exceptions.ServerConnectionException;

import java.util.Random;

public class TerminalServer {
    private static final int NETWORK_CONNECTION_QUALITY = 95; //в %
    private static final String SERVER_CONNECTION_EXCEPTION_MSG = "Unable to connect the server";

    private final Random random = new Random();
    private int moneyAmount = 0;


    public int getAccountInfo() {
        if (hasConnectionProblem()) {
            throw new ServerConnectionException(SERVER_CONNECTION_EXCEPTION_MSG);
        }
        return moneyAmount;
    }

    public void replenish(int inputCashAmount) {
        if (hasConnectionProblem()) {
            throw new ServerConnectionException(SERVER_CONNECTION_EXCEPTION_MSG);
        }
        moneyAmount += inputCashAmount;
    }

    public void withdraw(int outputCashAmount) {
        if (hasConnectionProblem()) {
            throw new ServerConnectionException(SERVER_CONNECTION_EXCEPTION_MSG);
        }
        moneyAmount -= outputCashAmount;
    }

    private boolean hasConnectionProblem() {
        return random.nextInt(100) > NETWORK_CONNECTION_QUALITY;
    }

}
