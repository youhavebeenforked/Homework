package terminal.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import terminal.exception.NotEnoughMoneyException;
import terminal.exception.ServerProblemException;

@Component
public class TerminalServerImpl implements TerminalServer {
    private final Account account;

    @Autowired
    public TerminalServerImpl() {
        this.account = new AccountImpl();
    }

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
