package terminal.model;

import org.junit.Before;
import org.junit.Test;
import terminal.exception.AccountIsLockedException;

import static org.junit.Assert.*;

public class PinValidatorImplTest {
    PinValidator validator;

    @Before
    public void before() {
        validator = new PinValidatorImpl();
    }

    @Test
    public void giveAccessFalse() {
        assertEquals("Не должно быть доступа к аккаунту" ,false, validator.giveAccess());
    }

    @Test
    public void giveAccessTruePin() {
        validator.checkPin("0000");
        assertEquals("Должен быть доступ к аккаунту" ,true, validator.giveAccess());
    }

    @Test
    public void giveAccessFalsePin() {
        validator.checkPin("1234");
        assertEquals("Не должно быть доступа к аккаунту" ,false, validator.giveAccess());
    }

    @Test
    public void checkPinTrue() {
        assertEquals("После ввода верного пин, должен быть доступ к аккаунту", true, validator.checkPin("0000"));
    }

    @Test
    public void checkPinFalse() {
        assertEquals("После ввода неверного пин, не должено быть доступа к аккаунту", false, validator.checkPin("1234"));
    }

    @Test
    public void checkBlockFalse() {
        validator.checkPin("0000");
        validator.checkBlock();
    }

    @Test(expected = AccountIsLockedException.class)
    public void checkBlockTrue() {
        validator.checkPin("1234");
        validator.checkPin("1234");
        validator.checkPin("1234");

        validator.checkBlock();
    }
}