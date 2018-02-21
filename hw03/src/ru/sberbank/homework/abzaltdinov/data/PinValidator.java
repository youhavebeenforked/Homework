package ru.sberbank.homework.abzaltdinov.data;

import ru.sberbank.homework.abzaltdinov.model.Account;

public class PinValidator {
    public boolean validate(int accountNumber, int pinCode) {
        Account account = AccountStorage.getAccount(accountNumber);
        if (account != null && account.getPinCode() == pinCode) {
            return true;
        }
        return false;
    }
}
