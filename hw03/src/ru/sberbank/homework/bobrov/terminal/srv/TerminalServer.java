package ru.sberbank.homework.bobrov.terminal.srv;


/**
 * Task terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */

public interface TerminalServer {
    int getMoney(int amount);

    void depositMoney(int amount);

    int getBalance();
}
