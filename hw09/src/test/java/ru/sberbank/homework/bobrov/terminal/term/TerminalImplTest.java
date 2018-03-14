package ru.sberbank.homework.bobrov.terminal.term;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Task terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 12.03.2018
 */

public class TerminalImplTest {
    private TerminalImpl terminal;

    @Before
    public void preparationCheckPin() {
        terminal = new TerminalImpl();
        terminal.pinCheck(1234_1234_1234_1234L, 1234);

    }

    @Test
    public void getMoneyThenCheckActualGetBalance() {
        String input = "100";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        terminal.getMoney();
        assertEquals(900, terminal.getBalance());
    }

    @Test
    public void depositMoneyThenCheckActualGetBalance() {
        String input = "200";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        terminal.depositMoney();
        assertEquals(1200, terminal.getBalance());
    }
}