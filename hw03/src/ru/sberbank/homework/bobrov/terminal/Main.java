package ru.sberbank.homework.bobrov.terminal;


import ru.sberbank.homework.bobrov.terminal.exception.CheckPinException;
import ru.sberbank.homework.bobrov.terminal.term.Terminal;
import ru.sberbank.homework.bobrov.terminal.term.TerminalImpl;

import java.util.Scanner;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */


public class Main {
    private Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Terminal terminal = new TerminalImpl();
        try {
            terminal.startTerminal(1234_1234_1234_1234L);
        } catch (CheckPinException e) {
            e.printStackTrace();
        }
    }
}
