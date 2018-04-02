package ru.sberbank.homework.maruev.hw3_1;

import java.util.Scanner;

/**
 * Created by Иван on 20.02.2018.
 */
public interface Terminal {
    void checkBalance(TerminalServer terminalServer, Scanner scanner);

    void getMoney(TerminalServer terminalServer, Scanner scanner);

    void setMoney(TerminalServer terminalServer, Scanner scanner);
}
