package ru.sberbank.homework.andreev.first;

import java.math.BigDecimal;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private String cardNumber;
    private boolean sessionStarted;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public boolean openSession(String cardNumber, String pin) {
        if (pinValidator.validate(cardNumber, pin)) {
            this.cardNumber = cardNumber;
            sessionStarted = true;
            return true;
        }
        return false;
    }

    @Override
    public void closeSession() {
        cardNumber = null;
        sessionStarted = false;
    }

    @Override
    public BigDecimal checkBalance() {
        checkSessionIsStarted();
        return server.getBalance(cardNumber);
    }

    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        checkSessionIsStarted();
        return server.withdraw(cardNumber, amount);
    }

    @Override
    public BigDecimal deposit(BigDecimal amount) {
        checkSessionIsStarted();
        return server.deposit(cardNumber, amount);
    }

    private void checkSessionIsStarted() {
        if (!sessionStarted) {
            throw new TerminalException("Session not started");
        }
    }
}
