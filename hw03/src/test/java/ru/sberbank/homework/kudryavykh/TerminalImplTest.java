package ru.sberbank.homework.kudryavykh;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class TerminalImplTest {

    Account account = new Account("name", 7000, 7777, 777, 1234L);
    TerminalImpl terminal = new TerminalImpl(account);

    public TerminalImplTest() throws InvalidCardNumber {
    }

    @Test(expected = AccessAccoutException.class)
    public void checkAccount() throws AccessAccoutException {
        terminal.checkAccount();
    }

    @Test
    public void pin()  {
        System.setIn(new ByteArrayInputStream("1234".getBytes()));
        System.setIn(System.in);
        boolean result = terminal.pin();
        assertEquals(false, result);
        System.setIn(new ByteArrayInputStream("7777".getBytes()));
        System.setIn(System.in);
        result = terminal.pin();
        assertEquals(true, result);
    }

    @Test
    public void getMoney() throws AccessAccoutException {
        TerminalImpl terminalCopy = new TerminalImpl(terminal);
        System.setIn(new ByteArrayInputStream("7777".getBytes()));
        System.setIn(System.in);
        terminal.pin();
        System.setIn(new ByteArrayInputStream("3000".getBytes()));
        System.setIn(System.in);
        terminal.getMoney();
        double result = account.getBalance();
        assertEquals(4000, result, 0.00001);
    }

    @Test
    public void setMoney() throws AccessAccoutException {
        TerminalImpl terminalCopy = new TerminalImpl(terminal);
        System.setIn(new ByteArrayInputStream("7777".getBytes()));
        System.setIn(System.in);
        terminal.pin();
        System.setIn(new ByteArrayInputStream("3000".getBytes()));
        System.setIn(System.in);
        terminal.setMoney();
        double result = account.getBalance();
        assertEquals(10000, result, 0.00001);
    }

}