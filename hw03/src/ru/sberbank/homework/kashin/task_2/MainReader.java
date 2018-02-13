package ru.sberbank.homework.kashin.task_2;

import ru.sberbank.homework.kashin.task_2.reader.UrlReader;

import java.io.IOException;

import static ru.sberbank.homework.kashin.task_2.reader.ConsoleHelper.print;
import static ru.sberbank.homework.kashin.task_2.reader.ConsoleHelper.readConsole;

public class MainReader {

    public static void main(String[] args) {
        UrlReader reader = new UrlReader();
        String userInput;

        while (true) {
            print("Enter URL address: ");
            try {
                userInput = readConsole();
                reader.readPage(userInput);
            } catch (IOException e) {
                print("url not available..");
                continue;
            }
            return;
        }
    }
}
