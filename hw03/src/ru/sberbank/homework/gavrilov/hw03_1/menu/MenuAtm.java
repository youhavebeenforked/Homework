package ru.sberbank.homework.gavrilov.hw03_1.menu;

import ru.sberbank.homework.gavrilov.hw03_1.exceptions.MenuOutException;
import ru.sberbank.homework.gavrilov.hw03_1.exceptions.MoneyException;
import ru.sberbank.homework.gavrilov.hw03_1.ui.ConsoleUserInterfaceMessages;
import ru.sberbank.homework.gavrilov.hw03_1.Input;
import ru.sberbank.homework.gavrilov.hw03_1.Terminal;
import ru.sberbank.homework.gavrilov.hw03_1.ui.UserInterfaceMessages;

import java.util.ArrayList;
import java.util.List;

public class MenuAtm {

    private Terminal terminal;
    private Input input;
    private UserInterfaceMessages uim;

    public MenuAtm(Terminal terminal, Input input) {
        this.terminal = terminal;
        this.input = input;
        this.uim = new ConsoleUserInterfaceMessages();
    }

    /**
     * Список пунктов меню.
     */
    private List<Command> commands = new ArrayList<>();

    /**
     * Заполнение пунктов меню.
     */
    public void fillMenu() {
        commands.add(new WithDrawMoney(1, "Withdraw money."));
        commands.add(new CheckBalance(2, "Check balance."));
        commands.add(new DepositMoney(3, "Deposit money."));
        commands.add(new Exit(4, "Exit."));
    }

    /**
     * Выбор пункта меню.
     *
     * @param key номер пункта меню, начина с 1.
     * @throws MenuOutException если такого пункта меню не существует.
     */
    public void selectMenu(int key) throws MenuOutException {
        if (key < 0 || key > commands.size()) {
            throw new MenuOutException("Некоректный пункт меню.");
        }
        commands.get(key - 1).execute(terminal, input);
    }

    /**
     * Показать меню.
     */
    public void showMenu() {
        commands.forEach(t -> uim.showMessage(t.info()));
    }


//    Сделал классы для каждого пункта меню вложенными,
//    т.к. впринципе это всё относится к одной логике - создание меню.
//    Но можно выделить каждый класс по отдельности.
//    Как лучше?

    private abstract class BaseCommand implements Command {
        /**
         * Номер пункта меню.
         */
        private int key;

        /**
         * Название пункта меню.
         */
        private String name;

        BaseCommand(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public String info() {
            return String.format("%s. %s", key(), name);
        }
    }

    /**
     * Команда для снятия денег со счёта.
     */
    private class WithDrawMoney extends BaseCommand {
        WithDrawMoney(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Terminal atm, Input input) {
            int amount = input.inputInt("Введите сумму:");
            try {
                atm.withDrawMoney(amount);
            } catch (MoneyException e) {
                uim.showMoneyMessage(e.getMessage());
            }
        }
    }

    /**
     * Команда для проверки баланса счёта.
     */
    private class CheckBalance extends BaseCommand {
        CheckBalance(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Terminal atm, Input input) {
            String msg = String.format("Текущий баланс: %s", atm.checkBalance());
            uim.showMessage(msg);
        }
    }

    /**
     * Команда для внесения денег на счёт.
     */
    private class DepositMoney extends BaseCommand {
        DepositMoney(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Terminal atm, Input input) {
            int amount = input.inputInt("Введите сумму:");
            try {
                atm.depositMoney(amount);
            } catch (MoneyException e) {
                uim.showMoneyMessage(e.getMessage());
            }
        }
    }

    /**
     * Команда для выхода из меню.
     * Можно через System.exit() реализовать, но это мне кажется неправильным.
     * Поэтому пока, что команда ни чего толкового не делает,
     * но в будещем можно зарефакторить.
     */
    private class Exit extends BaseCommand {
        Exit(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Terminal atm, Input input) {
            uim.showMessage("I'll be back!");
        }
    }

}
