package ru.sberbank.homework.kashin.task_1.model;

import ru.sberbank.homework.kashin.task_1.exception.NotEnoughMoneyException;
import ru.sberbank.homework.kashin.task_1.exception.ServerProblemException;

public class TerminalServerImpl implements TerminalServer {
    private Account account = new AccountImpl();

    @Override
    public long checkBalance() {
        return account.checkBalance();
    }

    @Override
    public void putMoney(long money) {
        long startAccountMoney = account.checkBalance();
        account.putMoney(money);
        long endAccountMoney = account.checkBalance();
        if ((startAccountMoney + money) != endAccountMoney) {
            throw new ServerProblemException("Проблема на сервере. Попробуйте снова положить деньги");
        }
    }

    @Override
    public long withdrawMoney(long money) {
        if (account.checkBalance() >= money) {
            account.withdrawMoney(money);
            return money;
        } else {
            throw new NotEnoughMoneyException("Недостаточно денег на счете. Попробуйте снять меньшую сумму.");
        }
    }
}
