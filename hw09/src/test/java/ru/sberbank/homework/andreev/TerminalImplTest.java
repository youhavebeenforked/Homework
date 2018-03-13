package ru.sberbank.homework.andreev;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TerminalImplTest {

    private TerminalImpl testTerminalImpl;

    @Before
    public void setUp() {
        TerminalServer ts = getTerminalServerMock();
        PinValidator pv = getPinValidatorMock();
        testTerminalImpl = new TerminalImpl(ts, pv);
    }

    @Test(expected = TerminalException.class)
    public void closeSession() {
        testTerminalImpl.openSession("320808042", "8246");
        testTerminalImpl.closeSession();
        testTerminalImpl.checkBalance();
    }

    @Test
    public void checkBalance() {
        testTerminalImpl.openSession("320808042", "8246");
        assertEquals(new BigDecimal("500").setScale(2, BigDecimal.ROUND_HALF_UP), testTerminalImpl.checkBalance());
    }

    @Test
    public void withdraw() {
        testTerminalImpl.openSession("320808042", "8246");
        assertEquals(new BigDecimal("0").setScale(2, BigDecimal.ROUND_HALF_UP),
                testTerminalImpl.withdraw(new BigDecimal("500").setScale(2, BigDecimal.ROUND_HALF_UP)));
    }

    @Test
    public void deposit() {
        testTerminalImpl.openSession("320808042", "8246");
        assertEquals(new BigDecimal("1000").setScale(2, BigDecimal.ROUND_HALF_UP),
                testTerminalImpl.deposit(new BigDecimal("500").setScale(2, BigDecimal.ROUND_HALF_UP)));
    }

    private PinValidator getPinValidatorMock() {
        PinValidator result = mock(PinValidator.class);
        when(result.validate(anyString(), anyString())).thenThrow(new TerminalException("pin exception"));
        when(result.validate(eq("320808042"), anyString())).thenReturn(false);
        when(result.validate(eq("320808042"), eq("8246"))).thenReturn(true);
        return result;
    }

    private static TerminalServer getTerminalServerMock() {
        TerminalServer result = mock(TerminalServer.class);
        when(result.getBalance(anyString())).thenThrow(new TerminalException("terminal server exception"));
        when(result.getBalance(eq("320808042"))).thenReturn(new BigDecimal("500").setScale(2, BigDecimal.ROUND_HALF_UP));
        when(result.withdraw("320808042", new BigDecimal("500").setScale(2, BigDecimal.ROUND_HALF_UP)))
                .thenReturn(new BigDecimal("0").setScale(2, BigDecimal.ROUND_HALF_UP));
        when(result.deposit("320808042", new BigDecimal("500").setScale(2, BigDecimal.ROUND_HALF_UP)))
                .thenReturn(new BigDecimal("1000").setScale(2, BigDecimal.ROUND_HALF_UP));
        return result;
    }

}