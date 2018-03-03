package ru.sberbank.homework.andreev.first;

import java.math.BigDecimal;
import java.util.Map;

public class TerminalServer {
    private Map<String, BigDecimal> accounts;

    public TerminalServer(Map<String, BigDecimal> accounts) {
        this.accounts = accounts;
    }

    public BigDecimal getBalance(String cardNumber) {
        Checker checker = new Checker(cardNumber, accounts);
        checker.checkParameterCorrectness();
        checker.checkExistence();
        return accounts.get(cardNumber);
    }

    public BigDecimal withdraw(String cardNumber, BigDecimal amount) {
        BigDecimal accountBalance = getAccountBalance(cardNumber, amount);
        checkHaveEnoughAmount(amount, accountBalance);
        BigDecimal result = accountBalance.subtract(amount);
        accounts.put(cardNumber, result.setScale(2, BigDecimal.ROUND_HALF_UP));
        return accounts.get(cardNumber);
    }

    public BigDecimal deposit(String cardNumber, BigDecimal amount) {
        BigDecimal accountBalance = getAccountBalance(cardNumber, amount);
        BigDecimal result = accountBalance.add(amount);
        accounts.put(cardNumber, result.setScale(2, BigDecimal.ROUND_HALF_UP));
        return accounts.get(cardNumber);
    }

    private BigDecimal getAccountBalance(String cardNumber, BigDecimal amount) {
        checkInputParameter(cardNumber, amount);
        return accounts.get(cardNumber);
    }

    private void checkInputParameter(String cardNumber, BigDecimal amount) {
        Checker checker = new Checker(cardNumber, accounts);
        checker.checkParameterCorrectness();
        checker.checkExistence();
        checkMoreThanZero(amount);
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
