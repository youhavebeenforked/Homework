package ru.sberbank.homework.kashin.task_1.model;

import ru.sberbank.homework.kashin.task_1.exception.NotEnoughMoneyException;

public class AccountImpl implements Account {
    private long moneyAccount = 10000;

    @Override
    public long checkBalance() {
        return moneyAccount;
    }

    @Override
    public long withdrawMoney(long money) {
        if (checkWithdrawMoney(money)) {
            return money;
        } else {
            throw new NotEnoughMoneyException("Недостаточно денег на счете. Попробуйте снять меньшую сумму.");
        }
    }

    @Override
    public void putMoney(long money) {
        this.moneyAccount = this.moneyAccount + money;
    }

    @Override
    public boolean checkWithdrawMoney(long money) {
        return this.moneyAccount >= money;
    }
}
