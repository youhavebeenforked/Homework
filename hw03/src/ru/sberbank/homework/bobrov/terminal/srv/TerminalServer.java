package ru.sberbank.homework.bobrov.terminal.srv;

import ru.sberbank.homework.bobrov.terminal.exception.NotEnoughMoney;
import ru.sberbank.homework.bobrov.terminal.exception.WrongSumException;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */

public interface TerminalServer {
    int getMoney(int amount) throws WrongSumException, NotEnoughMoney;

    void depositMoney(int amount) throws WrongSumException;

    int getBalance();
}
