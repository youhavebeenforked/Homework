package ru.sberbank.homework.kiseleva;

import org.junit.*;
import ru.sberbank.homework.kiseleva.exceptions.AccountIsLockedException;

import java.util.Date;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class TerminalServerTest extends Assert {

    static TerminalImpl terminal;
    static TerminalServer server;
    static PinValidator pinValidator;

    @BeforeClass
    public static void initTest() {
        terminal = new TerminalImpl();
        server = new TerminalServer();
        pinValidator = new PinValidator();
    }

    @Test
    public void isRightPassword() {
        final String pass = "1234";
        assertTrue(pinValidator.validate(pass));
    }

    @Test
    public void isWrongPassword() {
        final String pass = "1111";
        assertFalse(pinValidator.validate(pass));
    }

    @Test
    public void isLock() {
        server.setWrongPins(3);
        assertTrue(server.isLock());
    }

    @Test
    public void isLock5SecAndUnlock() throws InterruptedException {
        server.setWrongPins(3);
        assertTrue(server.isLock());

        Thread.sleep(5000);

        server.locker(new Date());
        assertFalse(server.isLock());
    }


    @Test(expected = AccountIsLockedException.class)
    public void isLockLess5Sec() throws InterruptedException {
        server.setWrongPins(3);
        assertTrue(server.isLock());

        Thread.sleep(3000);

        server.locker(new Date());
    }

}
