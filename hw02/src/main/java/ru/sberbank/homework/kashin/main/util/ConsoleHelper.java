package ru.sberbank.homework.kashin.main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
