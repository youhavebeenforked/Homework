package ru.sberbank.homework.kiseleva;

import java.util.Date;
import java.util.Scanner;

public class Main {
    private static TerminalClient client = new TerminalClient();
    public static void main(String[] args) throws InterruptedException {
        client.start();
    }
}