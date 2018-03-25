package terminal.model;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import terminal.exception.NotEnoughMoneyException;
import terminal.spring_test_context.ApplicationTest;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTest.class)
public class TerminalServerImplTest {

    @Autowired
    TerminalServer server;

    @Before
    public void before() {
        server.putMoney(10000);
    }

    @Test
    public void checkBalance() {
        assertEquals("Неверный баланс", 10000, server.checkBalance());
    }

    @Test
    public void putMoney() {
        long money = 1000;
        long startMoney = server.checkBalance();
        server.putMoney(money);
        long endMoney = server.checkBalance();
        assertEquals(String.format("Баланс должен увеличиться на %s рублей", money), money, endMoney - startMoney);
    }

    @Test
    public void withdrawMoneySuccessfully() {
        long money = 1000;
        long startMoney = server.checkBalance();
        server.withdrawMoney(money);
        long endMoney = server.checkBalance();
        assertEquals(String.format("Баланс должен уменшиться на %s рублей", money), money, startMoney - endMoney);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void withdrawMoneyUnsuccessfully() {
        long money = 11000;
        server.withdrawMoney(money);
    }
}