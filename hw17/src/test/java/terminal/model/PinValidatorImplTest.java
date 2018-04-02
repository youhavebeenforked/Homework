package terminal.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import terminal.exception.AccountIsLockedException;
import terminal.spring_test_context.ApplicationTest;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTest.class)
public class PinValidatorImplTest {

    @Autowired
    PinValidator validator;

    @Test
    public void giveAccessFalse() {
        assertEquals("Не должно быть доступа к аккаунту", false, validator.giveAccess());
    }

    @Test
    public void giveAccessTruePin() {
        setCorrectPin();
        assertEquals("Должен быть доступ к аккаунту", true, validator.giveAccess());
    }

    @Test
    public void giveAccessFalsePin() {
        validator.checkPin("1234");
        assertEquals("Не должно быть доступа к аккаунту", false, validator.giveAccess());
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
        setCorrectPin();
        validator.checkBlock();
    }

    @Test(expected = AccountIsLockedException.class)
    public void checkBlockTrue() {
        validator.checkPin("1234");
        validator.checkPin("1234");
        validator.checkPin("1234");

        validator.checkBlock();
    }

    private void setCorrectPin() {
        validator.checkPin("0000");
    }
}