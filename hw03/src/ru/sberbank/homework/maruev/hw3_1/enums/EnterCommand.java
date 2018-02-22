package ru.sberbank.homework.maruev.hw3_1.enums;

import ru.sberbank.homework.maruev.hw3_1.MainClass;

/**
 * Created by Иван on 22.02.2018.
 */
public enum EnterCommand {
    CHOOSE_OPERATION("Выберите операцию:\n"),
    BALANCE("Доступный остаток -- 1\n"),
    UP_BALANCE("Пополнить счет -- 2\n"),
    DOWN_BALANCE("Снять наличные -- 3\n"),
    EXIT_CODE("Завершить работу -- 4"),
    INCORRECT_COMMAND("Некорректный ввод\n"),
    UN_LOCK("До разблокировки: "),
    SECONDS(" секунд"),
    CONTINUE_MESSAGE(String.format("Нажмите %s что бы продолжить, или %s для завершения",
            MainClass.COMMANDS_LIST[0], MainClass.COMMANDS_LIST[3]));


    private final String command;

    EnterCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
