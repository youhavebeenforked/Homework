package ru.sberbank.homework.abzaltdinov.first.data;

import ru.sberbank.homework.abzaltdinov.first.model.Account;
import ru.sberbank.homework.abzaltdinov.first.repository.AccountStorage;

public class PinValidator {
    public boolean validate(int accountNumber, int pinCode) {
        Account account = AccountStorage.getAccount(accountNumber);
        if (account != null && account.getPinCode() == pinCode) {
            return true;
        }
        return false;
    }
}
