package ru.sberbank.homework.abzaltdinov.first.data;

import ru.sberbank.homework.abzaltdinov.first.exception.InsufficientFundsException;
import ru.sberbank.homework.abzaltdinov.first.model.Account;
import ru.sberbank.homework.abzaltdinov.first.repository.AccountStorage;

public class TerminalServerImpl implements TerminalServer {
    public boolean isAccountExists(int accountNumber) {
        return AccountStorage.getAccount(accountNumber) != null;
    }

    public void withdrawCashRequest(int accountNumber, int amount)
            throws InsufficientFundsException {
        Account account = AccountStorage.getAccount(accountNumber);
        int balance = account.getBalance();
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("На счете недостаточно средств!");
        }
        account.withdrawMoney(amount);
    }

    public void depositCashRequest(int accountNumber, int amount) {
        Account account = AccountStorage.getAccount(accountNumber);
        account.depositMoney(amount);
    }

    public int getBalanceRequest(int accountNumber) {
        Account account = AccountStorage.getAccount(accountNumber);
        return account.getBalance();
    }
}
