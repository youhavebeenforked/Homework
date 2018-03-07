package terminal.model;

import org.junit.Before;
import org.junit.Test;
import terminal.exception.NotAuthorizedException;
import terminal.exception.NotEnoughMoneyException;
import terminal.exception.NumberException;

import static org.junit.Assert.*;

public class TerminalImplTest {
    Terminal terminal;

    @Before
    public void before() {
        terminal = new TerminalImpl();
    }

    @Test
    public void checkBalanceTruePin() {
        terminal.enterPin("0000");
        assertEquals(0, terminal.checkBalance());
    }

    @Test(expected = NotAuthorizedException.class)
    public void checkBalanceFalsePin() {
        terminal.enterPin("1234");
        terminal.checkBalance();
    }

    @Test
    public void putMoneySuccessfully() {
        terminal.enterPin("0000");
        long money = 1000;
        long startMoney = terminal.checkBalance();
        terminal.putMoney(money);
        long endMoney = terminal.checkBalance();
        assertEquals(String.format("Баланс должен увеличиться на %s рублей", money) ,money, endMoney - startMoney);
    }

    @Test(expected = NotAuthorizedException.class)
    public void putMoneyFalsePin() {
        terminal.enterPin("1234");
        long money = 1000;
        terminal.putMoney(money);
    }

    @Test(expected = NumberException.class)
    public void putMoneyNotDivisibleNumber() {
        terminal.enterPin("0000");
        long money = 1010;
        terminal.putMoney(money);
    }

    @Test
    public void withdrawMoneySuccessfully() {
        terminal.enterPin("0000");
        terminal.putMoney(10000);
        long money = 1000;
        long startMoney = terminal.checkBalance();
        terminal.withdrawMoney(money);
        long endMoney = terminal.checkBalance();
        assertEquals(String.format("Баланс должен уменшиться на %s рублей", money) ,money, startMoney - endMoney);
    }

    @Test(expected = NotAuthorizedException.class)
    public void withdrawMoneyFalsePin() {
        terminal.enterPin("1234");
        long money = 1000;
        terminal.withdrawMoney(money);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void withdrawMoneyNotEnoughMoney() {
        terminal.enterPin("0000");
        long money = 11000;
        terminal.withdrawMoney(money);
    }

    @Test
    public void enterPinSuccessfully() {
        assertEquals("Введен верный пин, должен быть доступ" ,true ,terminal.enterPin("0000"));
    }

    @Test
    public void enterPinUnsuccessfully() {
        assertEquals( "Введен неверный пин, не должно быть доступа" ,false ,terminal.enterPin("1234"));
    }
}