package ru.sberbank.homework.kudryavykh;

import jdk.nashorn.internal.AssertsEnabled;
import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.*;

public class PinValidatorTest {

    Account account = new Account("name", 1000, 1234, 333, 1234_1234_1234_1234L);
    private int seconds = 0;

    @Test(timeout = 100)
    public void checkPin() throws AccountIsLockedException {
        PinValidator pinValidator = new PinValidator(account); //ctrl p
        int parameters = 1234;
        boolean result = pinValidator.checkPin(parameters);
        assertEquals(true, result);
    }

    @Test
    public void checkPinException() throws AccountIsLockedException {
        PinValidator pinValidator = new PinValidator(account); //ctrl p
        int parameters = 1235;
        boolean result = pinValidator.checkPin(parameters);
        assertEquals(false, result);
    }

    @Test(expected = AccountIsLockedException.class)
    public void checkPinExceptionException() throws AccountIsLockedException {
        PinValidator pinValidator = new PinValidator(account); //ctrl p
        int parameters = 1235;
        boolean result = false;
        for (int i = 0; i < 4; i++) {
            result = pinValidator.checkPin(parameters);
        }
    }

    @Test
    public void checkPinAfterException() throws InterruptedException {
        PinValidator pinValidator = new PinValidator(account); //ctrl p
        int parameters = 1235;
        boolean result = false;
        for (int i = 0; i < 5; i++) {
            try {
                result = pinValidator.checkPin(parameters);
            } catch (AccountIsLockedException e) {
                Thread.sleep(5000);
                parameters = 1234;
            }
        }
        assertEquals(true, result);
    }
}