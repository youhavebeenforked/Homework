package ru.sberbank.homework.abzaltdinov.first.data;

public interface TerminalServer {
    boolean isAccountExists(int accountNumber);
    void withdrawCashRequest(int accountNumber, int amount);
    void depositCashRequest(int accountNumber, int amount);
    int getBalanceRequest(int accountNumber);
}
