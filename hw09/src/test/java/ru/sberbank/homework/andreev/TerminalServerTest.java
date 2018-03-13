package ru.sberbank.homework.andreev;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TerminalServerTest {
    private TerminalServer testTerminalServer;

    @Before
    public void Before() {
        Map<String, BigDecimal> accounts = new HashMap<>();
        accounts.put("753951456852", new BigDecimal("1000").setScale(2, BigDecimal.ROUND_HALF_UP));
        accounts.put("123456789101112", new BigDecimal("20").setScale(2, BigDecimal.ROUND_HALF_UP));
        testTerminalServer = new TerminalServer(accounts);
    }

    @Test
    public void getBalance() {
        assertEquals(new BigDecimal("1000").setScale(2, BigDecimal.ROUND_HALF_UP), testTerminalServer.getBalance("753951456852"));
    }

    @Test
    public void withdraw() {
        BigDecimal actual = testTerminalServer.withdraw("753951456852", new BigDecimal("499.99").setScale(2, BigDecimal.ROUND_HALF_UP));
        BigDecimal expected = new BigDecimal("500.01").setScale(2, BigDecimal.ROUND_HALF_UP);
        assertEquals(expected, actual);
    }

    @Test
    public void depositWithHalfDownRounding() {
        BigDecimal actual = testTerminalServer.deposit("123456789101112", new BigDecimal("111.3333333333").setScale(2, BigDecimal.ROUND_HALF_UP));
        BigDecimal expected = new BigDecimal("131.33").setScale(2, BigDecimal.ROUND_HALF_UP);
        assertEquals(expected, actual);
    }

    @Test
    public void depositWithHalfUpRounding() {
        BigDecimal actual = testTerminalServer.deposit("123456789101112", new BigDecimal("111.335").setScale(2, BigDecimal.ROUND_HALF_UP));
        BigDecimal expected = new BigDecimal("131.34").setScale(2, BigDecimal.ROUND_HALF_UP);
        assertEquals(expected, actual);
    }

    @Test(expected = TerminalException.class)
    public void InvokeException() {
        testTerminalServer.getBalance("31");
        testTerminalServer.deposit("123456789101112", new BigDecimal("-1229.99").setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}