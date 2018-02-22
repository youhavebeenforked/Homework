package ru.sberbank.homework.maruev.hw3_2;

import java.net.MalformedURLException;
import java.util.Scanner;

/**
 * Created by Иван on 20.02.2018.
 */
public abstract class EnterClass {
    public static final String EXIT_CONST = "quit";
    public static final String FIRST_MESSAGE = "Введите URL ссылку, или \"quit\" для выхода: ";
    public static final String INVALID_EXPRESSION = "Некорректный ввод. Попробуйте еще раз.";

    public void enter() throws MalformedURLException {
        Scanner scanner = new Scanner(System.in);
        WebReader reader = new WebReader();

        while (true) {
            System.out.println(FIRST_MESSAGE);
            String link = scanner.nextLine();

            if (link.equals(EXIT_CONST)) {
                break;
            } else {
                if (reader.isValid(link)) {
                    reader.readPage(link);
                } else {
                    System.out.println(INVALID_EXPRESSION);
                    continue;
                }
            }
        }
    }
}
