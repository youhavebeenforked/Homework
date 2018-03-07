package Termianl;

import Termianl.exeptions.InsufficientFundsExeption;
import Termianl.exeptions.NotCorrectPinExeption;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TerminalServerTest {
    TerminalServer server = new TerminalServer();

    @Before
    public void initialize() {
        server.setPinStorage(() -> 5555);
    }

    @Test
    public void checkPinCorrect() throws Exception {
        try {
            server.checkPin(5555);
        } catch (NotCorrectPinExeption e) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

    @Test(expected = NotCorrectPinExeption.class)
    public void checkPinIncorrect() throws Exception {
        server.checkPin(111);
        server.checkPin(1111);
        server.checkPin(11111);
    }

    @Test
    public void setPinStorage() throws Exception {
        server.setPinStorage(() -> 9999);
    }

    @Test
    public void getBankBook() throws Exception {
        Assert.assertEquals(server.getBankBook(), 10200);
        Assert.assertNotEquals(server.getBankBook(), 1020);
        Assert.assertNotEquals(server.getBankBook(), 0);
        Assert.assertNotEquals(server.getBankBook(), -10200);
    }

    @Test
    public void withdrawMoneyCorrect() throws Exception {
        final int startMoney = server.getBankBook();
        server.withdrawMoney(100);
        Assert.assertEquals(server.getBankBook(), startMoney - 100);
        server.withdrawMoney(0);
        Assert.assertEquals(server.getBankBook(), startMoney - 100);
        server.withdrawMoney(-100);
        Assert.assertEquals(server.getBankBook(), startMoney);
    }

    @Test(expected = InsufficientFundsExeption.class)
    public void withdrawMoneyIncorrect() throws Exception {
        final int startMoney = server.getBankBook();
        server.withdrawMoney(startMoney + 100);
    }

    @Test
    public void putMoney() throws Exception {
        final int startMoney = server.getBankBook();
        server.putMoney(100);
        Assert.assertEquals(server.getBankBook(), startMoney + 100);
        server.putMoney(1000);
        Assert.assertEquals(server.getBankBook(), startMoney + 1100);
    }
}