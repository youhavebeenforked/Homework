package terminal.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import terminal.exception.NotAuthorizedException;
import terminal.exception.NotEnoughMoneyException;
import terminal.exception.NumberException;
import terminal.spring_test_context.ApplicationTest;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTest.class)
public class TerminalImplTest {

    @Autowired
    private Terminal terminal;

    @Test
    public void checkBalanceTruePin() {
        setCorrectPin();
        assertEquals(0, terminal.checkBalance());
    }

    @Test(expected = NotAuthorizedException.class)
    public void checkBalanceFalsePin() {
        terminal.enterPin("1234");
        terminal.checkBalance();
    }

    @Test
    public void putMoneySuccessfully() {
        setCorrectPin();
        long money = 1000;
        long startMoney = terminal.checkBalance();
        terminal.putMoney(money);
        long endMoney = terminal.checkBalance();
        assertEquals(String.format("Баланс должен увеличиться на %s рублей", money), money, endMoney - startMoney);
    }

    @Test(expected = NotAuthorizedException.class)
    public void putMoneyFalsePin() {
        terminal.enterPin("1234");
        long money = 1000;
        terminal.putMoney(money);
    }

    @Test(expected = NumberException.class)
    public void putMoneyNotDivisibleNumber() {
        setCorrectPin();
        long money = 1010;
        terminal.putMoney(money);
    }

    @Test
    public void withdrawMoneySuccessfully() {
        setCorrectPin();
        terminal.putMoney(10000);
        long money = 1000;
        long startMoney = terminal.checkBalance();
        terminal.withdrawMoney(money);
        long endMoney = terminal.checkBalance();
        assertEquals(String.format("Баланс должен уменшиться на %s рублей", money), money, startMoney - endMoney);
    }

    @Test(expected = NotAuthorizedException.class)
    public void withdrawMoneyFalsePin() {
        terminal.enterPin("1234");
        long money = 1000;
        terminal.withdrawMoney(money);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void withdrawMoneyNotEnoughMoney() {
        setCorrectPin();
        long money = 11000;
        terminal.withdrawMoney(money);
    }

    @Test
    public void enterPinSuccessfully() {
        assertEquals("Введен верный пин, должен быть доступ", true, terminal.enterPin("0000"));
    }

    @Test
    public void enterPinUnsuccessfully() {
        assertEquals("Введен неверный пин, не должно быть доступа", false, terminal.enterPin("1234"));
    }

    private void setCorrectPin() {
        terminal.enterPin("0000");
    }
}