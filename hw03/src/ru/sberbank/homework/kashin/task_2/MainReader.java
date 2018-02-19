package ru.sberbank.homework.kashin.task_2;

import ru.sberbank.homework.kashin.task_2.reader.UrlReader;

import static ru.sberbank.homework.kashin.task_2.reader.ConsoleHelper.print;
import static ru.sberbank.homework.kashin.task_2.reader.ConsoleHelper.readConsole;

public class MainReader {

    public static void main(String[] args) {
        UrlReader reader = new UrlReader();

        while (true) {
            print("Enter URL address: ");
            String userInput = readConsole();
            if (reader.readPage(userInput)) {
                break;
            }
        }
    }
}
