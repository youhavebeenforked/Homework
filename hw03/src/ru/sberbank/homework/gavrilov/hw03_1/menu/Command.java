package ru.sberbank.homework.gavrilov.hw03_1.menu;

import ru.sberbank.homework.gavrilov.hw03_1.Input;
import ru.sberbank.homework.gavrilov.hw03_1.Terminal;

public interface Command {

    /**
     * Номер пункта меню.
     *
     * @return номер.
     */
    int key();

    /**
     * Выполняет команду.
     * или
     * Выполняет действия этого пункта меню.
     *
     * @param atm   терминал.
     * @param input ввод.
     */
    void execute(Terminal atm, Input input);

    /**
     * Информация о пункте меню.
     * Для вывода названия пункта меню.
     *
     * @return Строковое представление.
     */
    String info();
}
