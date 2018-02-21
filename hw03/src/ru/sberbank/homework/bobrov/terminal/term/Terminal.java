package ru.sberbank.homework.bobrov.terminal.term;


/**
 * Task terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */

public interface Terminal {
    void startTerminal(long cardNumber);

    void getMoney();

    void depositMoney();

    void getBalance();
}
