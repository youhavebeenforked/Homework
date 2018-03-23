package ru.sberbank.homework.solovyov;

import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.solovyov.exceptions.AccountFundsException;
import ru.sberbank.homework.solovyov.exceptions.MoneyAmountException;
import ru.sberbank.homework.solovyov.exceptions.PinFormatException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TerminalImplTest {
    private static final int INITIAL_CASH_AMOUNT = 10_000;
    private static final String CORRECT_PIN = "1234";

    private TerminalImpl terminal;
    private TerminalServer terminalServer;

    @Before
    public void setUp() {
        terminalServer = mock(TerminalServer.class);
        terminal = new TerminalImpl(terminalServer, new PinValidator());
    }

    @Test
    public void getPinAcceptanceStatus() {
        assertFalse(terminal.getPinAcceptanceStatus());
    }

    @Test
    public void getPinAcceptanceStatusAfterEntrance() {
        terminal.requestPinValidation(CORRECT_PIN);
        assertTrue(terminal.getPinAcceptanceStatus());
    }

    @Test
    public void requestPinValidation() {
        assertTrue(terminal.requestPinValidation(CORRECT_PIN));
    }

    @Test
    public void requestWrongPinValidation() {
        assertFalse(terminal.requestPinValidation("5555"));
    }

    @Test(expected = PinFormatException.class)
    public void requestIncorrectFormatPinValidation() {
        terminal.requestPinValidation("999");
    }

    @Test
    public void checkAccountInfo() {
        terminal.requestPinValidation(CORRECT_PIN);
        when(terminalServer.getAccountInfo()).thenReturn(INITIAL_CASH_AMOUNT);
        assertEquals("Неверный баланс", INITIAL_CASH_AMOUNT, terminal.checkAccount());
    }

    @Test
    public void getCash() {
        int money = 500;
        terminal.requestPinValidation(CORRECT_PIN);
        when(terminalServer.getAccountInfo()).thenReturn(INITIAL_CASH_AMOUNT - money);
        assertEquals("Неверный баланс после снятия денег", (long)(INITIAL_CASH_AMOUNT - money), terminal.getCash(money));
    }

    @Test
    public void putCash() {
        int money = 1000;
        terminal.requestPinValidation(CORRECT_PIN);
        when(terminalServer.getAccountInfo()).thenReturn(INITIAL_CASH_AMOUNT + money);
        assertEquals("Неверный баланс после снятия денег", (long)(INITIAL_CASH_AMOUNT + money), terminal.putCash(money));
    }

    @Test(expected = MoneyAmountException.class)
    public void getWrongCashAmount() {
        int money = 50;
        terminal.requestPinValidation(CORRECT_PIN);
        terminal.getCash(money);
    }

    @Test(expected = MoneyAmountException.class)
    public void getNegativeCashAmount() {
        int money = -10;
        terminal.requestPinValidation("1234");
        terminal.getCash(money);
    }

    @Test(expected = AccountFundsException.class)
    public void getMoreCashAmountThanHave() {
        int money = INITIAL_CASH_AMOUNT + 100;
        terminal.requestPinValidation(CORRECT_PIN);
        terminal.getCash(money);
    }


    @Test(expected = MoneyAmountException.class)
    public void putWrongCashAmount() {
        int money = 50;
        terminal.requestPinValidation(CORRECT_PIN);
        terminal.putCash(money);
    }

    @Test(expected = MoneyAmountException.class)
    public void putNegativeCashAmount() {
        int money = -10;
        terminal.requestPinValidation(CORRECT_PIN);
        terminal.putCash(money);
    }
}