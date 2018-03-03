package ru.sberbank.homework.gavrilov.hw03_1;

import ru.sberbank.homework.gavrilov.hw03_1.exceptions.NetworkProblemException;
import ru.sberbank.homework.gavrilov.hw03_1.exceptions.NotEnoughMoneyException;

public class TerminalServer implements Terminal {

    /**
     * Хранилище аккаунтов.
     * P.s. Наверное TerminalServer как раз и подразумевается как какой-то банк. Но сделал почему-то так.
     */
    private Bank bank;
    /**
     * Аккаунт (счёт) с которым происходят операции в банке.
     */
    private Account account;

    public TerminalServer() {
        this.bank = new Bank();
    }

    @Override
    public boolean startTerminal(String cardNumber) throws NetworkProblemException {
        Account acc = bank.getAccount(cardNumber);
        if (acc != null) {
            this.account = acc;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int checkBalance() throws NetworkProblemException {
        return account.checkBalance();
    }

    @Override
    public void depositMoney(int amount) throws NetworkProblemException {
        account.depositMoney(amount);
    }

    @Override
    public int withDrawMoney(int amount) throws NotEnoughMoneyException, NetworkProblemException {
        if (account.withDrawMoney(amount)) {
            return amount;
        } else throw new NotEnoughMoneyException("Не достаточно денег.");
    }

}
