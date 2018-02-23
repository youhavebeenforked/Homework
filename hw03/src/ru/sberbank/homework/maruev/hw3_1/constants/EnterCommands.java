package ru.sberbank.homework.maruev.hw3_1.constants;

import ru.sberbank.homework.maruev.hw3_1.MainClass;

/**
 * Created by Иван on 23.02.2018.
 */
public class EnterCommands {
    public static final String CHOOSE_OPERATION = "Выберите операцию:\n";
    public static final String BALANCE = "Доступный остаток -- 1\n";
    public static final String UP_BALANCE = "Пополнить счет -- 2\n";
    public static final String DOWN_BALANCE = "Снять наличные -- 3\n";
    public static final String EXIT_CODE = "Завершить работу -- 4";
    public static final String INCORRECT_COMMAND = "Некорректный ввод\n";
    public static final String UN_LOCK = "До разблокировки: %s секунд";
    public static final String CONTINUE_MESSAGE = String.format("Нажмите %s что бы продолжить, или %s для завершения",
                     MainClass.COMMANDS_LIST[0], MainClass.COMMANDS_LIST[3]);
}
