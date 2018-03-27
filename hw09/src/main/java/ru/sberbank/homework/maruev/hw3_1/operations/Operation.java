package ru.sberbank.homework.maruev.hw3_1.operations;

import ru.sberbank.homework.maruev.hw3_1.*;

import java.util.Scanner;

/**
 * Created by Иван on 20.02.2018.
 */
public interface Operation {

    int LOCK_NUMBER = 3;
    PinValidator validator = new PinValidator();

    void move(TerminalServer server, Scanner scanner);
}
