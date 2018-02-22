package ru.sberbank.homework.maruev.hw3_1.enums;

/**
 * Created by Иван on 22.02.2018.
 */
public enum BankCommand {
    PIN_CODE("Введите PIN:"),
    INCORRECT_PIN("Некорректный PIN. Осталось попыток: "),
    MONEY_SUM("Введите сумму: "),
    SET_MONEY("Сумма взноса должна быть кратна 100"),
    BALANCE_SUM("Текущий остаток: "),
    GET_MONEY("Сумма снятия должна быть кратна 100");


    private final String command;

    BankCommand(String command) {
        this.command = command;

    }

    public String getCommand() {
        return command;
    }
}
