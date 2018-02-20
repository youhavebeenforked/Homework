package ru.sberbank.homework.maruev.hw3_1.operations;

import ru.sberbank.homework.maruev.hw3_1.*;

import java.util.Scanner;

/**
 * Created by Иван on 20.02.2018.
 */
public interface Operation {
    PinValidator validator = new PinValidator();

    String PIN_CODE = "Введите PIN:";
    String INCORRECT_PIN = "Некорректный PIN. Осталось попыток: ";
    String MONEY_SUM = "Введите сумму: ";
    String SET_MONEY = "Сумма взноса должна быть кратна 100";
    String BALANCE_SUM = "Текущий остаток: ";
    String GET_MONEY = "Сумма снятия должна быть кратна 100";
    int LOCK_NUMBER = 3;

    void move(TerminalServer server, Scanner scanner);
}
