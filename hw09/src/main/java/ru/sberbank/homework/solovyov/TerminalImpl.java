package ru.sberbank.homework.solovyov;

import ru.sberbank.homework.solovyov.exceptions.AccountFundsException;
import ru.sberbank.homework.solovyov.exceptions.AccountLoginException;
import ru.sberbank.homework.solovyov.exceptions.MoneyAmountException;


public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private boolean isAllowedAccess = false;

    public TerminalImpl() {
        server = new TerminalServer();
        pinValidator = new PinValidator();
    }

    @Override
    public boolean getPinAcceptanceStatus() {
        return isAllowedAccess;
    }

    @Override
    public boolean requestPinValidation(String pin) {
        isAllowedAccess = pinValidator.validatePin(pin);
        return isAllowedAccess;
    }

    @Override
    public int checkAccount() {
        if (!isAllowedAccess) {
            throw new AccountLoginException("No PIN input");
        }
        isAllowedAccess = false;
        return server.getAccountInfo();
    }

    @Override
    public int getCash(int outputCashAmount) {
        if (!isAllowedAccess) {
            throw new AccountLoginException("No PIN input");
        }
        if (checkMoneyAmount(outputCashAmount)) {
            if (outputCashAmount > server.getAccountInfo()) {
                isAllowedAccess = false;
                throw new AccountFundsException("Not enough money to withdraw");
            }
            server.withdraw(outputCashAmount);
        }
        return checkAccount();
    }

    @Override
    public int putCash(int inputCashAmount) {
        if (!isAllowedAccess) {
            throw new AccountLoginException("No PIN input");
        }
        if (checkMoneyAmount(inputCashAmount)) {
            server.replenish(inputCashAmount);
        }
        return checkAccount();
    }

    private boolean checkMoneyAmount(int amount) {
        if (amount <= 0) {
            isAllowedAccess = false;
            throw new MoneyAmountException("Negative or zero amount of money");
        }
        if (amount % 100 != 0) {
            isAllowedAccess = false;
            throw new MoneyAmountException("The amount of money is not a multiple of 100");
        }
        return true;
    }
}
