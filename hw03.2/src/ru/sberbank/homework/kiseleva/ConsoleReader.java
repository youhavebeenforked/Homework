package ru.sberbank.homework.kiseleva;

import java.util.Scanner;

/**
 * Created by Ekaterina Kiseleva on 16.02.2018.
 */
public class ConsoleReader {
    public String readUrl() {
        System.out.println("please enter URL: ");
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        return url;
    }
}
