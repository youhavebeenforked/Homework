package ru.sberbank.homework.drozdov.hw3_1;

public interface Terminal {
    void putMoney(long amount);

    void withdrawMoney(long amount);

    void checkPin(String pin);

    long showBalance();
}
