package ru.sberbank.homework.gavrilov.hw03_1;

import ru.sberbank.homework.gavrilov.hw03_1.exceptions.CheckPinCodeException;
import ru.sberbank.homework.gavrilov.hw03_1.exceptions.NotExistCardNumberException;

public class PinValidator {

    private Bank bank = new Bank();

    public boolean checkNumberCard(String numberCard) throws NotExistCardNumberException {
        boolean result = bank.getListOfAccounts().stream()
                .anyMatch(account -> account.getCardNumber().equals(numberCard));
        if (!result) {
            throw new NotExistCardNumberException("Данной карты не существует.");
        }
        return true;
    }

    public boolean isValidPin(String numberCard, int pass) throws CheckPinCodeException {
        for (Account acc : bank.getListOfAccounts()) {
            if (acc.getCardNumber().equals(numberCard) && acc.getPinCode() == pass) {
                return true;
            }
        }
        throw new CheckPinCodeException("Неправильный пин код.");
    }
}
