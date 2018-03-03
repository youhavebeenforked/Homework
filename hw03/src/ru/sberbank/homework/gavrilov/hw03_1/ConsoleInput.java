package ru.sberbank.homework.gavrilov.hw03_1;

import java.util.Scanner;

/**
 * Ввод с консоли.
 */
public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String input(String ask) {
        System.out.println(ask);
        return scanner.nextLine();
    }
}
