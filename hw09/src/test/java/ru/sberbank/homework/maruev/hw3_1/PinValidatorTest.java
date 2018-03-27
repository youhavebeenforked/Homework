package ru.sberbank.homework.maruev.hw3_1;

import org.junit.*;

import static org.mockito.Mockito.*;

/**
 * Created by Иван.
 */
public class PinValidatorTest {
    PinValidator validator = new PinValidator();
    String truePin = "1100";
    String falsePin = "1234";
    boolean expected;
    boolean actual;

    @Before
    public void setUp() {
        validator.setPinCode(truePin);
    }

    @Test
    public void correctPinTest() {
        actual = validator.isValidPin(truePin);
        expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void incorrectPinTest() {
        actual = validator.isValidPin(falsePin);
        expected = false;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mockPinValidatorTest() {
        PinValidator validator = mock(PinValidator.class);
        when(validator.isValidPin(falsePin)).thenReturn(false);
        actual = validator.isValidPin(falsePin);
        expected = false;
        Assert.assertEquals(expected, actual);
    }
}