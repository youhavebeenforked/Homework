package ru.sberbank.homework.bobrov.terminal.msg;


/**
 * Task terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 20.02.2018
 */


public interface ShowMessage {
    void showWrongPin();

    void showNotEnoughMoney();

    void showWrongSumException();

    void showIdentError();

    void showNetworkProblem();
}
