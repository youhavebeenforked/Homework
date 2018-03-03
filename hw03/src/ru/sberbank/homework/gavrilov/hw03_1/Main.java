package ru.sberbank.homework.gavrilov.hw03_1;

import ru.sberbank.homework.gavrilov.hw03_1.exceptions.MenuOutException;
import ru.sberbank.homework.gavrilov.hw03_1.exceptions.MoneyException;
import ru.sberbank.homework.gavrilov.hw03_1.exceptions.NetworkProblemException;
import ru.sberbank.homework.gavrilov.hw03_1.menu.MenuAtm;
import ru.sberbank.homework.gavrilov.hw03_1.ui.ConsoleUserInterfaceMessages;
import ru.sberbank.homework.gavrilov.hw03_1.ui.UserInterfaceMessages;

public class Main {

    private static final int EXIT = 4;

    private Terminal terminal;
    private Input input;
    private UserInterfaceMessages uim;

    public Main(Terminal terminal, Input input) {
        this.terminal = terminal;
        this.input = input;
        this.uim = new ConsoleUserInterfaceMessages();
    }

    public static void main(String[] args) {
        new Main(new Atm(), new ConsoleInput()).start();
    }

    private void start() {
        MenuAtm menu = new MenuAtm(terminal, input);
        try {
            String card = input.input("Введите номер карты:");
            if (terminal.startTerminal(card)) {
                menu.fillMenu();
            }
        } catch (NumberFormatException e) {
            uim.showMenuMessage("Некорректный ввод!");
        }
        int userChoice = 0;
        while (true) {
            menu.showMenu();
            try {
                userChoice = input.inputInt("Выберите пункт меню:");
                menu.selectMenu(userChoice);
            } catch (MenuOutException e) {
                uim.showMenuMessage(e.getMessage());
            } catch (MoneyException e) {
                uim.showMoneyMessage(e.getMessage());
            } catch (NetworkProblemException e) {
                uim.showNetworkMessage(e.getMessage());
            } catch (IllegalArgumentException | NullPointerException | IndexOutOfBoundsException e) {
                uim.showMenuMessage("Некорректный ввод!");
            }
            if (userChoice == EXIT) {
                break;
            }
        }
    }

}
