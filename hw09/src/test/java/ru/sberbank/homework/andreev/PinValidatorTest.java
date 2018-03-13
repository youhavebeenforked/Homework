package ru.sberbank.homework.andreev;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PinValidatorTest {

    @Test
    public void happyPathWithoutException() {
        PinValidator pinValidator = getPinValidator();
        assertTrue(pinValidator.validate("6865465657656", "1234"));

    }

    @Test
    public void checkWrongPin() {
        PinValidator pinValidator = getPinValidator();
        assertFalse(pinValidator.validate("6865465657656", "1233"));

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void checkIncorrectInputException() {
        PinValidator pinValidator = getPinValidator();
        thrown.expect(TerminalException.class);
        thrown.expectMessage("Your input contain not only digits 686ABURVALG57656");
        pinValidator.validate("686ABURVALG57656", "1234");
    }

    @Test
    public void checkUnexistedException() {
        PinValidator pinValidator = getPinValidator();
        thrown.expect(TerminalException.class);
        thrown.expectMessage("Card number 369852741123 don't exist in our base");
        pinValidator.validate("369852741123", "1234");
    }

    @Test
    public void checkWrongLengthException() {
        PinValidator pinValidator = getPinValidator();
        thrown.expect(TerminalException.class);
        thrown.expectMessage("Number of digits in card number must be above 12 and under 20 digits");
        pinValidator.validate("36985274112311111111111111111111111111111", "1234");
    }

    private PinValidator getPinValidator() {
        Map<String, String> map = new HashMap<>();
        map.put("6865465657656", "1234");
        return new PinValidator(map);
    }

}