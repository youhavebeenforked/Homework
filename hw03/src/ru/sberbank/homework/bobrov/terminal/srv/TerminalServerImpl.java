package ru.sberbank.homework.bobrov.terminal.srv;


import ru.sberbank.homework.bobrov.terminal.exception.NotEnoughMoney;
import ru.sberbank.homework.bobrov.terminal.exception.WrongSumException;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */


public class TerminalServerImpl implements TerminalServer {
    int balance = 1000;

    @Override
    public int getMoney(int amount) throws WrongSumException, NotEnoughMoney {
        if (amount > balance) {
            throw new NotEnoughMoney("Not Enough Money");
        }
        return amount;
    }

    @Override
    public void depositMoney(int amount) throws WrongSumException {
        if (amount % 100 != 0) {
            throw new WrongSumException("Enter the sum multiple 100");
        }
        balance = balance + amount;

    }

    @Override
    public int getBalance() {
        return balance;
    }
}
