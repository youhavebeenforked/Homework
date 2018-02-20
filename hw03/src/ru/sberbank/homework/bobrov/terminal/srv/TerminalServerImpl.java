package ru.sberbank.homework.bobrov.terminal.srv;


import ru.sberbank.homework.bobrov.terminal.exception.NotEnoughMoney;
import ru.sberbank.homework.bobrov.terminal.msg.ShowMessage;
import ru.sberbank.homework.bobrov.terminal.msg.ShowMessageImpl;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */


public class TerminalServerImpl implements TerminalServer {
    private final ShowMessage showMsg = new ShowMessageImpl();


    private int balance = 1000;

    @Override
    public int getMoney(int amount) {
        if (amount > balance) {
            try {
                throw new NotEnoughMoney("Not Enough Money");
            } catch (NotEnoughMoney notEnoughMoney) {
                showMsg.showNotEnoughMoney();
                return 0;
            }
        }
        return amount;
    }

    @Override
    public void depositMoney(int amount) {
        balance = balance + amount;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
