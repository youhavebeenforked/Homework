package ru.sberbank.homework.gavrilov.hw03_1.ui;

/**
 * Класс для вывода пользовательского интерфейса (сообщений) на консоль.
 */
public class ConsoleUserInterfaceMessages implements UserInterfaceMessages {
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showMoneyMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showAccountMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showNetworkMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showPinMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showMenuMessage(String message) {
        System.out.println(message);
    }
}
