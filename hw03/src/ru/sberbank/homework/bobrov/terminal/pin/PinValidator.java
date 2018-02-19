package ru.sberbank.homework.bobrov.terminal.pin;


import ru.sberbank.homework.bobrov.terminal.exception.CheckPinException;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */


public class PinValidator {
    private Map<Long, Integer> accounts = new HashMap<>();

    public boolean checkCredentials(long cardNumber, int pinCOde) throws CheckPinException {
        boolean result = false;
        if (accounts.isEmpty()) {
            fillAccounts();
        }
        return (accounts.containsKey(cardNumber) && checkPinCode(cardNumber, pinCOde));
    }

    private boolean checkPinCode(long cardNumber, int pinCOde) {
        boolean result = false;
        for (Map.Entry<Long, Integer> longIntegerEntry : accounts.entrySet()) {
            if (longIntegerEntry.getKey() == cardNumber && longIntegerEntry.getValue() == pinCOde) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Simply simulate a database of card and pin.
     */
    private void fillAccounts() {

        accounts.put(1234_1234_1234_1234L, 1234);
    }
}
