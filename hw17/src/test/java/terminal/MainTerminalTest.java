package terminal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import terminal.spring_test_context.ApplicationTest;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTest.class)
public class MainTerminalTest {

    @Autowired
    private MainTerminal terminal;

    @Test
    public void printCheckBalance() {
        terminal.enterPin("0000");
        long expected = 0;
        long actual = terminal.checkBalance();

        assertEquals(expected, actual);
    }

    @Test
    public void putMoney() {
        terminal.enterPin("0000");
        terminal.putMoney(1000);
        long actual = terminal.checkBalance();
        long expected = 1000;

        assertEquals(expected, actual);
    }

    @Test
    public void withdrawMoney() {
        terminal.enterPin("0000");
        terminal.putMoney(10000);
        terminal.withdrawMoney(1000);
        long actual = terminal.checkBalance();
        long expected = 9000;

        assertEquals(expected, actual);
    }

    @Test
    public void enterPin() {
        terminal.enterPin("0000");
        terminal.checkBalance();
    }
}