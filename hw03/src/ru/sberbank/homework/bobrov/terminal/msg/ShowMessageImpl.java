package ru.sberbank.homework.bobrov.terminal.msg;


/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 20.02.2018
 */


public class ShowMessageImpl implements ShowMessage {

    @Override
    public void showWrongPin() {
        System.out.println("Please try again");
    }

    @Override
    public void showNotEnoughMoney() {
        System.out.println("Not enough money on the account. Return to the main menu");

    }

    @Override
    public void showWrongSumException() {
        System.out.println("Enter the sum multiple 100");
    }

    @Override
    public void showIdentError() {
        System.out.println("First you have to go through identification");
    }
}
