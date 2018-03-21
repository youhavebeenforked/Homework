package ru.sberbank.homework.bobrov.terminal.pin;

import org.junit.Test;
import ru.sberbank.homework.bobrov.terminal.exception.CheckPinException;

import static org.junit.Assert.assertTrue;

/**
 * Task terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 12.03.2018
 */

public class PinValidatorTest {
    @Test
    public void checkCredentialsIfExist() throws Exception {
        PinValidator pinValidator = new PinValidator();
        boolean result = pinValidator.checkCredentials(1234_1234_1234_1234L, 1234);
        assertTrue(result);
    }

    @Test(expected = CheckPinException.class)
    public void checkCredentialsIfNotExist() throws Exception {
        PinValidator pinValidator = new PinValidator();
        pinValidator.checkCredentials(1234_1234_1234_1234L, 4321);
    }
}