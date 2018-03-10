package ru.sberbank.homework.solovyov;

public interface Terminal {
    boolean getPinAcceptanceStatus();

    boolean requestPinValidation(String pin);

    int checkAccount();

    int getCash(int outputCashAmount);

    int putCash(int inputCashAmount);
}
