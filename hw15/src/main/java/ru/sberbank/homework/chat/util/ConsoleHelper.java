package ru.sberbank.homework.chat.util;

import ru.sberbank.homework.chat.writer.ConsoleWriter;
import ru.sberbank.homework.chat.writer.Writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Writer writer = new ConsoleWriter();

    public static String readString() {
        String readConsole;
        while (true) {
            try {
                readConsole = reader.readLine();
            } catch (IOException e) {
                writer.write("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
                continue;
            }
            break;
        }
        return readConsole;
    }
}