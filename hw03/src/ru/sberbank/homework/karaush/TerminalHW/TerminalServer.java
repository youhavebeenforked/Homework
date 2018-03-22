package ru.sberbank.homework.karaush.TerminalHW;

public class TerminalServer {

    private int accountMoney;

    public int getAccountMoney() {
        return accountMoney;
    }

    private void checkRequestedAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("your amount should be positive");
        }
        if (amount % 100 != 0) {
            throw new IllegalArgumentException("your amount should be divider of 100");
        }
    }

    public void putMoney(int amount) {

        checkRequestedAmount(amount);
        accountMoney += amount;
    }

    public void withdrawMoney(int amount) {

        checkRequestedAmount(amount);
        if (accountMoney < amount) {
            throw new NotEnoughMoneyException("not enough money");
        }
        accountMoney -= amount;
    }
}
