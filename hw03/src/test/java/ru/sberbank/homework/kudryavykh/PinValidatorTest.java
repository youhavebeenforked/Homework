package ru.sberbank.homework.kudryavykh;

import jdk.nashorn.internal.AssertsEnabled;
import org.junit.Test;

import static org.junit.Assert.*;

public class PinValidatorTest {

    @Test(timeout = 100)
    public void checkPin() {
        Account account = new Account("name", 1000, 1234, 333, 1234_1234_1234_1234l);
        PinValidator pinValidator = new PinValidator(account);
        int parameters = 1234;
        boolean result = false;
        try {
             result = pinValidator.checkPin(parameters);
        } catch (AccountIsLockedException e) {
            e.printStackTrace();
        }
        assertEquals(true, result);
    }
}