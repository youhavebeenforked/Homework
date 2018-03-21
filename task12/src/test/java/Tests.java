import bedarev.input_and_print.Menu;
import bedarev.pin_processor.PinValidator;
import bedarev.terminal.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.*;


public class Tests extends Assert{
    private final static String wrongPinCode = "12345";
    private final static String PinCode = "1234";
    private PinValidator pinValid;
    private TerminalServer termServ;
    private Menu menu;

    @Test
    public void testPinValidator() {
        menu = mock(Menu.class);

        PinValidator pinValidator = new PinValidator(menu);
        assertFalse(new PinValidator(new Menu()).validatePin(wrongPinCode));
        assertTrue(new PinValidator(new Menu()).validatePin(PinCode));

        for (int i = 0; i < 4; i++) {
            pinValidator.validatePin(wrongPinCode);
        }
        assertTrue(pinValidator.isAccountIsLocked());
        verify(menu, times(1)).print("Account is locked. Time left: 0 seconds");
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();
    public ExpectedException exceptionNumber = ExpectedException.none();

    @Test
    public void testCalculateValue() {
        //calcValue
        TerminalServer terminalServer = new TerminalServer(new Menu());
        assertTrue(terminalServer.getAccount() == 0);

        terminalServer.calcValue("1", (x,y) -> x + y, "1200");
        assertTrue(terminalServer.getAccount() == 1200);

        terminalServer.calcValue("1", (x,y) -> x + y, "120");
        assertTrue(terminalServer.getAccount() == 1200);

        exceptionNumber.expect(NumberFormatException.class);
        exception.expectMessage("For input string: \"qwe\"");
        terminalServer.calcValue("1", (x,y) -> x + y, "qwe");
    }

    @Test
    public void testNotEnoughMoneyException() {
        TerminalServer terminalServer = new TerminalServer(new Menu());
        exception.expect(NotEnoughMoneyException.class);
        exception.expectMessage("Not enough money. ");
        terminalServer.calcValue("1", (x,y) -> x - y,"1400");
    }

    @Test
    public void testTerminalImpl() throws Exception {
        pinValid = mock(PinValidator.class);
        TerminalImpl terminal = new TerminalImpl(termServ, pinValid);

        terminal.run("12345");
        verify(pinValid,times(1)).validatePin("12345");
        terminal.run("1234");
        verify(pinValid,times(1)).validatePin("1234");

        terminal.run("q");
        verifyZeroInteractions(pinValid);


    }

    @Test
    public void testTerminal() throws Exception {
        pinValid = new PinValidator(new Menu());
        termServ = mock(TerminalServer.class);
        TerminalImpl terminal = new TerminalImpl(termServ, pinValid);

        terminal.run("12347");
        verify(termServ,times(0)).runTerminal();

        terminal.run("1234");
        verify(termServ,times(1)).runTerminal();
    }

    @Test
    public void testLogicMenu() {
        menu = mock(Menu.class);
        termServ = new TerminalServer(menu);

        termServ.logicMenu("1");
        verify(menu,times(1)).print("Please enter value: ");
        verify(menu,times(1)).print("On your account: 0");

    }

    @Rule
    public ExpectedException netException = ExpectedException.none();


    @Test
    public void testNetworkException() throws NetworkProblemException {
        RandomException randomException = new RandomException();
        netException.expect(NetworkProblemException.class);
        for (int i = 0; i < 100; i++) {
            try {
                randomException.random();
            } catch (HardwareProblemException exception) {
                exception.getMessage();
            }

        }
    }

    @Rule
    public ExpectedException hwException = ExpectedException.none();

    @Test
    public void testHardwareException() throws HardwareProblemException {
        RandomException randomException = new RandomException();
        hwException.expect(HardwareProblemException.class);
        for (int i = 0; i < 100; i++) {
            try {
                randomException.random();
            } catch (NetworkProblemException exception) {
                exception.getMessage();
            }

        }
    }
}
