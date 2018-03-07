package Termianl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TerminalImplTest {
    Terminal terminal = new TerminalImpl();
    @Before
    public void initialize(){
        terminal.setPinStorage(() -> 4875);
    }

    @org.junit.Test
    public void setPinStorage() throws Exception {
        Assert.assertTrue(terminal.setPinStorage(()->6789));
    }

    @org.junit.Test
    public void setPinIncorrect() throws Exception {
        Assert.assertFalse(terminal.setPin(45888));
        Assert.assertFalse(terminal.setPin(999));
        Assert.assertFalse(terminal.setPin(0));
        Assert.assertFalse(terminal.setPin(10000000));
    }

    @Test
    public void block() throws Exception {
        Assert.assertFalse(terminal.setPin(45888));
        Assert.assertFalse(terminal.setPin(999));
        Assert.assertFalse(terminal.setPin(0));
        Assert.assertFalse(terminal.setPin(4875));
        Thread.sleep(5000);
        Assert.assertTrue(terminal.setPin(4875));

    }

    @org.junit.Test
    public void setPinCorrect() throws Exception {
        terminal.setPinStorage(() -> 4875);
        Assert.assertTrue(terminal.setPin(4875));
        Assert.assertTrue(terminal.setPin(4875));
        Assert.assertTrue(terminal.setPin(487500));
        Assert.assertTrue(terminal.setPin(4));
    }

    @org.junit.Test
    public void getBankBook() throws Exception {
        terminal.setPin(4875);
        Assert.assertEquals(terminal.getBankBook(), 10200);
        Assert.assertNotEquals(terminal.getBankBook(), 1020);
        Assert.assertNotEquals(terminal.getBankBook(), 0);
        Assert.assertNotEquals(terminal.getBankBook(), -10200);
    }

    @org.junit.Test
    public void withdrawMoneyCorrect() throws Exception {
        terminal.setPin(4875);
        Assert.assertTrue(terminal.withdrawMoney(100));
        Assert.assertEquals(terminal.getBankBook(), 10100);
        Assert.assertTrue(terminal.withdrawMoney(1100));
        Assert.assertEquals(terminal.getBankBook(), 9000);
    }
    @org.junit.Test
    public void withdrawMoneyIncorrect() throws Exception {
        terminal.setPin(4875);
        int currenrMoney = terminal.getBankBook();
        Assert.assertFalse(terminal.withdrawMoney(101));
        Assert.assertEquals(terminal.getBankBook(), currenrMoney);
        Assert.assertFalse(terminal.withdrawMoney(-100));
        Assert.assertEquals(terminal.getBankBook(), currenrMoney);
        Assert.assertFalse(terminal.withdrawMoney(10300));
        Assert.assertEquals(terminal.getBankBook(), currenrMoney);
        Assert.assertFalse(terminal.withdrawMoney(0));
        Assert.assertEquals(terminal.getBankBook(), currenrMoney);
    }

    @org.junit.Test
    public void putMoneyCorrect() throws Exception {
        terminal.setPin(4875);
        int startMoney = terminal.getBankBook();
        Assert.assertTrue(terminal.putMoney(100));
        Assert.assertEquals(terminal.getBankBook(), startMoney + 100);
        Assert.assertTrue(terminal.putMoney(10000));
        Assert.assertEquals(terminal.getBankBook(), startMoney + 10100);

    }

    @org.junit.Test
    public void putMoneyIncorrect() throws Exception {
        terminal.setPin(4875);
        int startMoney = terminal.getBankBook();
        Assert.assertFalse(terminal.putMoney(101));
        Assert.assertEquals(terminal.getBankBook(), startMoney);
        Assert.assertFalse(terminal.putMoney(-100));
        Assert.assertEquals(terminal.getBankBook(), startMoney);
        Assert.assertFalse(terminal.putMoney(0));
        Assert.assertEquals(terminal.getBankBook(), startMoney);
    }
}