package ru.sberbank.homework.bobrov.terminal;


import ru.sberbank.homework.bobrov.terminal.term.Terminal;
import ru.sberbank.homework.bobrov.terminal.term.TerminalImpl;


/**
 * Task terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */


public class Main {

    public static void main(String[] args) {
        long cardNumber = 1234_1234_1234_1234L;
        Terminal terminal = new TerminalImpl();
        terminal.startTerminal(cardNumber);
    }
}
