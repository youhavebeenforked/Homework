package terminal.model;

import org.junit.*;
import terminal.exception.NotEnoughMoneyException;

import static org.junit.Assert.*;

public class TerminalServerImplTest {
    TerminalServer server;

    @Before
    public void before() {
        server = new TerminalServerImpl();

    }

    @Test
    public void checkBalance() {
        assertEquals("Неверный баланс" ,10000, server.checkBalance());
    }

    @Test
    public void putMoney() {
        long money = 1000;
        long startMoney = server.checkBalance();
        server.putMoney(money);
        long endMoney = server.checkBalance();
        assertEquals(String.format("Баланс должен увеличиться на %s рублей", money) ,money, endMoney - startMoney);
    }

    @Test
    public void withdrawMoneySuccessfully() {
        long money = 1000;
        long startMoney = server.checkBalance();
        server.withdrawMoney(money);
        long endMoney = server.checkBalance();
        assertEquals(String.format("Баланс должен уменшиться на %s рублей", money) ,money, startMoney - endMoney);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void withdrawMoneyUnsuccessfully() {
        long money = 11000;
        server.withdrawMoney(money);
    }
}