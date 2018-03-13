package ru.sberbank.homework.andreev;

import java.math.BigDecimal;

public interface Terminal {
    boolean openSession(String cardNumber, String pin);

    void closeSession();

    BigDecimal checkBalance();

    BigDecimal withdraw(BigDecimal amount);

    BigDecimal deposit(BigDecimal amount);
}
