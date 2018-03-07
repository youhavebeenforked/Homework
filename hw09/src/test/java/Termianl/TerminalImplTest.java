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

    @Test
    public void setPinIncorrect() throws Exception {
        Assert.assertFalse(terminal.isCorrectPin(45888));
        Assert.assertFalse(terminal.isCorrectPin(999));
        Assert.assertFalse(terminal.isCorrectPin(0));
        Assert.assertFalse(terminal.isCorrectPin(10000000));
    }

    @Test
    public void block() throws Exception {
        Assert.assertFalse(terminal.isCorrectPin(45888));
        Assert.assertFalse(terminal.isCorrectPin(999));
        Assert.assertFalse(terminal.isCorrectPin(0));
        Assert.assertFalse(terminal.isCorrectPin(4875));
        Thread.sleep(5000);
        Assert.assertTrue(terminal.isCorrectPin(4875));

    }

    @Test
    public void setPinCorrect() throws Exception {
        Assert.assertTrue(terminal.isCorrectPin(4875));
        Assert.assertTrue(terminal.isCorrectPin(4875));
        Assert.assertTrue(terminal.isCorrectPin(487500));
        Assert.assertTrue(terminal.isCorrectPin(4));
    }

    @Test
    public void getBankBook() throws Exception {
        terminal.isCorrectPin(4875);
        terminal.setScore(10200);
        Assert.assertEquals(terminal.getBankBook(), 10200);
        Assert.assertNotEquals(terminal.getBankBook(), 1020);
        Assert.assertNotEquals(terminal.getBankBook(), 0);
        Assert.assertNotEquals(terminal.getBankBook(), -10200);
    }

    @Test
    public void withdrawMoneyCorrect() throws Exception {
        terminal.isCorrectPin(4875);
        terminal.setScore(10200);
        Assert.assertTrue(terminal.withdrawMoney(100));
        Assert.assertEquals(terminal.getBankBook(), 10100);
        Assert.assertTrue(terminal.withdrawMoney(1100));
        Assert.assertEquals(terminal.getBankBook(), 9000);
    }
    @Test
    public void withdrawMoneyIncorrect() throws Exception {
        terminal.isCorrectPin(4875);
        terminal.setScore(10200);
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

    @Test
    public void putMoneyCorrect() throws Exception {
        terminal.isCorrectPin(4875);
        terminal.setScore(10200);
        int startMoney = terminal.getBankBook();
        Assert.assertTrue(terminal.putMoney(100));
        Assert.assertEquals(terminal.getBankBook(), startMoney + 100);
    }

    @Test
    public void putMoneyIncorrect() throws Exception {
        terminal.isCorrectPin(4875);
        int startMoney = terminal.getBankBook();
        Assert.assertFalse(terminal.putMoney(101));
        Assert.assertEquals(terminal.getBankBook(), startMoney);
        Assert.assertFalse(terminal.putMoney(-100));
        Assert.assertEquals(terminal.getBankBook(), startMoney);
        Assert.assertFalse(terminal.putMoney(0));
        Assert.assertEquals(terminal.getBankBook(), startMoney);
    }
}