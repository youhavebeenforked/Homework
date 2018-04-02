package ru.sberbank.homework.solovyov;

import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalServerTest {

    TerminalServer terminalServer = new TerminalServer();

    @Test
    public void getAccountInfo() {
        assertEquals("Неверная начальная сумма на счете", 0, terminalServer.getAccountInfo());
    }

    @Test
    public void replenish() {
        int money = 1000;
        terminalServer.replenish(money);
        assertEquals("Неверная сумма после зачисления", money, terminalServer.getAccountInfo());
    }

    @Test
    public void withdraw() {
        int money = 500;
        terminalServer.withdraw(money);
        assertEquals("Неверная сумма после снятия", -money, terminalServer.getAccountInfo());
    }
}