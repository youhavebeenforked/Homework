package terminal.model;

import terminal.exception.NotEnoughMoneyException;

public class AccountImpl implements Account {
    private long moneyAccount = 0;

    @Override
    public long checkBalance() {
        return moneyAccount;
    }

    @Override
    public long withdrawMoney(long money) {
        if (checkWithdrawMoney(money)) {
            moneyAccount = moneyAccount - money;
            return money;
        } else {
            throw new NotEnoughMoneyException("Недостаточно денег на счете. Попробуйте снять меньшую сумму.");
        }
    }

    @Override
    public void putMoney(long money) {
        this.moneyAccount = this.moneyAccount + money;
    }

    private boolean checkWithdrawMoney(long money) {
        return this.moneyAccount >= money;
    }
}
