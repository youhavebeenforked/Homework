package ru.sberbank.homework.karaush.TerminalHW;

public interface Terminal {

    boolean validatePin(String pin);

    int checkBalance();

    void withdrawMoney(int amount);

    void putMoney(int amount);

}
