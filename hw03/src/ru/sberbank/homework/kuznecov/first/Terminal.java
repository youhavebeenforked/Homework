package ru.sberbank.homework.kuznecov.first;

public interface Terminal {

    CardNumber getCardNumber();

    boolean validate(CardNumber cardNumber, Pin pin);

    double checkAccount(CardNumber cardNumber);

    double getCash(CardNumber cardNumber, double sum);

    double putCash(CardNumber cardNumber, double sum);

}
