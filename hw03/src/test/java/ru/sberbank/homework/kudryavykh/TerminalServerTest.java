package ru.sberbank.homework.kudryavykh;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalServerTest {

    TerminalServer terminalServer;

    @Before
    public void init() throws InvalidCardNumber {
        terminalServer = new TerminalServer(1234_1234_1234_1234L);
    }


    @Test
    public void pinCheck() throws AccountIsLockedException {
        boolean result = terminalServer.pinCheck(1234);
        assertEquals(true, result);
    }

    @Test
    public void pinCheckFailed() throws AccountIsLockedException {
        boolean result = terminalServer.pinCheck(2534);
        assertEquals(false, result);
    }

    @Test
    public void getBalance() {
        double result = terminalServer.getBalance();
        assertEquals(1000, result, 0.0001);
    }

    @Test
    public void setBalance() throws IncorrectBalanceException {
        terminalServer.setBalance(2000);
        double result = terminalServer.getBalance();
        assertEquals(2000, result, 0.0001);
    }
}