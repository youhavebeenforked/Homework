package ru.sberbank.homework.dergun.hw1;

public interface Terminal {
    void setPin(int pin);

    int getBankBook();

    void withdrawMoney(int money);

    void putMoney(int money);

}
