package ru.sberbank.homework.kudryavykh;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    Account account = new Account("name", 1000, 1234, 333, 1234_1234_1234_1234L);

    @Test
    public void getPin() {
        int result = account.getPin();
        assertEquals(1234, result);
    }

    @Test
    public void getBalance() {
        double result = account.getBalance();
        assertEquals(1000, 1000);
    }

    @Test
    public void setBalance() throws IncorrectBalanceException {
        double sum = 500;
        account.setBalance(sum);
        double result = account.getBalance();
        assertEquals(500, result, 0.0001);
    }

    @Test(expected = IncorrectBalanceException.class)
    public void setBalanceException() throws IncorrectBalanceException {
        double sum = -500;
        account.setBalance(sum);
    }

}