package ru.sberbank.homework.kashin.task_1.model;

public interface Account {
    long checkBalance();
    long withdrawMoney(long money);
    void putMoney(long money);
    boolean checkWithdrawMoney(long money);
}
