package ru.sberbank.homework.kiseleva;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                ConsoleReader consoleReader = new ConsoleReader();
                PageReader pageReader = new PageReader();
                pageReader.readPage(consoleReader.readUrl());
                if (pageReader.isSuccess()) {
                    break;
                }
            } catch (IOException e) {
                System.out.println("URL not found! repeat input");
            }
        }
    }
}