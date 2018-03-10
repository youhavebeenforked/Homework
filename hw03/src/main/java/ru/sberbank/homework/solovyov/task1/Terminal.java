package ru.sberbank.homework.solovyov.task1;

public interface Terminal {
    boolean getPinAcceptanceStatus();

    boolean requestPinValidation(String pin);

    int checkAccount();

    int getCash(int outputCashAmount);

    int putCash(int inputCashAmount);
}
