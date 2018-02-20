package ru.sberbank.homework.maruev.hw3_1;

import ru.sberbank.homework.maruev.hw3_1.operations.*;

import java.util.Scanner;

/**
 * Created by Иван on 20.02.2018.
 */
public class TerminalImpl implements Terminal {

    Operation operation;

    @Override
    public void checkBalance(TerminalServer terminalServer, Scanner scanner) {
        operation = new CheckOperation();
        operation.move(terminalServer, scanner);
    }

    @Override
    public void getMoney(TerminalServer terminalServer, Scanner scanner) {
        operation = new GetOperation();
        operation.move(terminalServer, scanner);
    }

    @Override
    public void setMoney(TerminalServer terminalServer, Scanner scanner) {
        operation = new SetOperation();
        operation.move(terminalServer, scanner);
    }
}
