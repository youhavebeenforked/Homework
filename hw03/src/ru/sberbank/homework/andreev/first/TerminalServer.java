package ru.sberbank.homework.andreev.first;

import java.math.BigDecimal;
import java.util.Map;

public class TerminalServer {
    private Map<String, BigDecimal> accounts;

    public TerminalServer(Map<String, BigDecimal> accounts) {
        this.accounts = accounts;
    }

    public BigDecimal getBalance(String cardNumber) {
        Checker.checkCardNumberLength(cardNumber);
        Checker.checkKeyContain(cardNumber, accounts, "Your card number miss in our base");
        return accounts.get(cardNumber);
    }

    public BigDecimal withdraw(String cardNumber, BigDecimal amount) {
        Checker.checkCardNumberLength(cardNumber);
        Checker.checkKeyContain(cardNumber, accounts, "Your card number miss in our base");
        BigDecimal accountBalance = accounts.get(cardNumber);
        checkMoreThanZero(amount);
        checkHaveEnoughAmount(amount, accountBalance);
        BigDecimal result = accountBalance.subtract(amount);
        accounts.put(cardNumber, result.setScale(2, BigDecimal.ROUND_HALF_UP));
        return accounts.get(cardNumber);
    }

    public BigDecimal deposit(String cardNumber, BigDecimal amount) {
        Checker.checkCardNumberLength(cardNumber);
        Checker.checkKeyContain(cardNumber, accounts, "Your card number miss in our base");
        BigDecimal accountBalance = accounts.get(cardNumber);
        checkMoreThanZero(amount);
        BigDecimal result = accountBalance.add(amount);
        accounts.put(cardNumber, result.setScale(2, BigDecimal.ROUND_HALF_UP));
        return accounts.get(cardNumber);
    }

    private void checkHaveEnoughAmount(BigDecimal amount, BigDecimal accountBalance) {
        if (accountBalance.compareTo(amount) < 0) {
            throw new TerminalException("You don't have that much money");
        }
    }

    private void checkMoreThanZero(BigDecimal amount) {
        if (BigDecimal.ZERO.compareTo(amount) >= 0) {
            throw new TerminalException("Value must be more than zero");
        }
    }


}
