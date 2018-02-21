package ru.sberbank.homework.kashin.task_1.model;

public interface TerminalServer {
    long checkBalance();
    void putMoney(long money);
    long withdrawMoney(long money);
}
