package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.model.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Objects.nonNull;

public class ConsoleHelper {

    public static String readConsole() {
        String userInput = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }

    public static void print(String str) {
        System.out.println(str);
    }

}
