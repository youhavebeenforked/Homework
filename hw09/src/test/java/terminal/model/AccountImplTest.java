package terminal.model;

import org.junit.Test;
import terminal.exception.NotEnoughMoneyException;

import static org.junit.Assert.*;

public class AccountImplTest {
    Account account = new AccountImpl();

    @Test
    public void checkBalance() {
        assertEquals(0, account.checkBalance());
    }

    @Test
    public void withdrawMoneySuccessfully() {
        account.putMoney(10000);
        long money = 1000;
        long startMoney = account.checkBalance();
        account.withdrawMoney(money);
        long endMoney = account.checkBalance();
        assertEquals(String.format("Баланс должен уменшиться на %s рублей", money) ,money, startMoney - endMoney);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void withdrawMoneyUnsuccessfully() {
        long money = 1000;
        account.withdrawMoney(money);
    }

    @Test
    public void putMoney() {
        long money = 1000;
        long startMoney = account.checkBalance();
        account.putMoney(money);
        long endMoney = account.checkBalance();
        assertEquals(String.format("Баланс должен увеличиться на %s рублей", money) ,money, endMoney - startMoney);
    }
}