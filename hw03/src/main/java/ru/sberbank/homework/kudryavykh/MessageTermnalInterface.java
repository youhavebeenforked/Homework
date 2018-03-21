package ru.sberbank.homework.kudryavykh;

//Просто интерфейс. Сказано же, что в теории можно легко менять интерфейс вывода сообщений
//Это больше условность

public interface MessageTermnalInterface {

    void incorrectPinValue();

    void incorrectBalanceValue();

    void accountLocked();

    void invalidCardNumber();

    void balanceCash(double balance);

    int inputSum();

    String inputPin();

}
