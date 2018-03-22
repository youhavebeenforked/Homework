package ru.sberbank.homework.karaush.TerminalHW;

import java.util.Scanner;

public class Example {
    public static void main(String[] args) {

        Terminal terminal = new MyTerminal();
        Scanner scanner = new Scanner(System.in);

        Client client = new Client(terminal, scanner);
        client.interact();
    }
}
