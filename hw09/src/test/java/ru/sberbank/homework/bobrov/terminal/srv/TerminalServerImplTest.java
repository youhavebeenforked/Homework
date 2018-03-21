package ru.sberbank.homework.bobrov.terminal.srv;

import org.junit.Before;
import org.junit.Test;
import ru.sberbank.homework.bobrov.terminal.exception.NotEnoughMoney;
import ru.sberbank.homework.bobrov.terminal.exception.WrongSumException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Task Terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 13.03.2018
 */

public class TerminalServerImplTest {
    private TerminalServerImpl server;

    @Before
    public void preparation() {
        server = new TerminalServerImpl();
    }

    @Test
    public void checkGetBalanceShouldReturn1000True() throws Exception {
        assertEquals(1000, server.getBalance());
    }

    @Test
    public void checkGetBalanceShouldReturn1000False() throws Exception {
        assertNotEquals(1001, server.getBalance());
    }

    @Test
    public void checkDepositMoneyShouldReturnTrue() throws Exception {
        assertEquals(1100, server.depositMoney(100));
    }

    @Test
    public void checkDepositMoneyShouldReturnFals() throws Exception {
        assertNotEquals(1100, server.depositMoney(200));
    }

    @Test
    public void checkGetMoneyShouldReturnRequestedSum() {
        assertEquals(200, server.getMoney(200));
    }

    @Test(expected = NotEnoughMoney.class)
    public void checkGetMoneyShouldReturnExceptionNotEnoughMoney() {
        server.getMoney(1100);
    }

    @Test(expected = WrongSumException.class)
    public void checkGetMoneyShouldReturnExceptionWrongSumException() {
        server.checkValidSum(1);
    }

}