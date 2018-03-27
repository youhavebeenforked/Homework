package ru.sberbank.homework.maruev.hw3_1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.maruev.hw3_1.exceptions.EmptyBillException;

/**
 * Created by Иван.
 */
public class TerminalServerTest {
    private TerminalServer server = new TerminalServer();
    private int sum;
    private int balance = 2000;

    @Before
    public void setUp() {
        server.setBalance(balance);
    }

    @Test
    public void upBalanceTest() {
        sum = 500;

        server.upBalance(sum);

        int actual = server.getBalance();
        int expected = balance + sum;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void validDownBalanceTest() {
        sum = 400;

        server.downBalance(sum);

        int actual = server.getBalance();
        int expected = balance - sum;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = EmptyBillException.class)
    public void invalidDownBalance() {
        server.setBalance(55);
        server.downBalance(sum);
    }
}