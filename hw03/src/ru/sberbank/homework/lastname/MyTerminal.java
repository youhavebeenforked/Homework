package ru.sberbank.homework.lastname;

public class MyTerminal implements Terminal {
    private final TerminalServer terminalServer;
    private final PinValidator pinValidator;


    MyTerminal() {
        pinValidator = new PinValidator("1234");
        terminalServer = new TerminalServer();
    }

    @Override
    public boolean validatePin(String pin) {
        return pinValidator.validate(pin);
    }

    @Override
    public int checkBalance() {
        return terminalServer.getAccountMoney();
    }

    @Override
    public void withdrawMoney(int amount) {
        terminalServer.withdrawMoney(amount);
    }

    @Override
    public void putMoney(int amount) {
        terminalServer.putMoney(amount);
    }
}
