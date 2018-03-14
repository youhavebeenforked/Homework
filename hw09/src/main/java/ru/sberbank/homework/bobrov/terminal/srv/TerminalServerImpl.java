package ru.sberbank.homework.bobrov.terminal.srv;


import ru.sberbank.homework.bobrov.terminal.exception.BadNetworkException;
import ru.sberbank.homework.bobrov.terminal.exception.NotEnoughMoney;
import ru.sberbank.homework.bobrov.terminal.exception.WrongSumException;
import ru.sberbank.homework.bobrov.terminal.msg.ShowMessage;
import ru.sberbank.homework.bobrov.terminal.msg.ShowMessageImpl;

import java.util.Random;

/**
 * Task terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */


public class TerminalServerImpl implements TerminalServer {
    private final ShowMessage showMsg = new ShowMessageImpl();
    private int balance = 1000;

    @Override
    public int getMoney(int amount) {
        int result = 0;
        checkNetwork();
        if (amount > balance) {
            throw new NotEnoughMoney("Not Enough Money");
        }
        try {
            if (checkValidSum(amount)) {
                balance -= amount;
                result = amount;
            }
        } catch (WrongSumException e) {
            showMsg.showWrongSumException();
        }
        return result;
    }

    @Override
    public int depositMoney(int amount) {
        checkNetwork();
        if (checkValidSum(amount)) {
            balance += amount;
        }

        return balance;
    }

    @Override
    public int getBalance() {
        checkNetwork();
        return balance;
    }

    private void checkNetwork() {
        Random random = new Random();
        int goodNetwork = 99;
        int currentNetwork = 40 + random.nextInt(100 - 40);
        if (goodNetwork < currentNetwork) {
            try {
                throw new BadNetworkException("Bad network");
            } catch (BadNetworkException e) {
                showMsg.showNetworkProblem();
                System.exit(0);
            }
        }
    }

    boolean checkValidSum(int amount) {
        if (amount % 100 == 0) {
            return true;
        } else {
            throw new WrongSumException("Wrong sum");
        }
    }
}
