package terminal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import terminal.writer.Writer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainTerminalTest {
    @InjectMocks
    MainTerminal terminal;

    @Mock
    Writer mockWriter;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void printCheckBalance() {
        terminal.enterPin("0000");
        terminal.checkBalance();

        verify(mockWriter, atLeastOnce()).print("Пин введен верно");
        verify(mockWriter, atLeastOnce()).print("Ваш баланс 10000");
    }

    @Test
    public void printCheckBalanceFalsePin() {
        terminal.enterPin("1234");
        terminal.checkBalance();

        verify(mockWriter, atLeastOnce()).print("Неверный пин.");
        verify(mockWriter, atLeastOnce()).print("Вы не авторизованы");
    }

    @Test
    public void putMoney() {
        terminal.enterPin("0000");
        terminal.putMoney(1000);

        verify(mockWriter, atLeastOnce()).print("Пин введен верно");
        verify(mockWriter, atLeastOnce()).print("Вы положили 1000 на ваш счет.");
    }

    @Test
    public void withdrawMoney() {
        terminal.enterPin("0000");
        terminal.withdrawMoney(1000);

        verify(mockWriter, atLeastOnce()).print("Пин введен верно");
        verify(mockWriter, atLeastOnce()).print("Вы сняли 1000 с вашего счета.");
    }

    @Test
    public void enterPin() {
        terminal.enterPin("0000");

        verify(mockWriter, atLeastOnce()).print("Пин введен верно");
    }
}