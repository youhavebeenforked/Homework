package terminal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MainTerminalTest {
    MainTerminal terminal;

    @Before
    public void before() {
        terminal = new MainTerminal();
    }


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