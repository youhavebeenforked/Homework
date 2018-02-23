package ru.sberbank.homework.gavrilov.hw03_2;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ReaderPage readerPage = new ReaderPage();
        while (true) {
            System.out.println("Введите URL сайта:");
            String link = new Scanner(System.in).nextLine();
            try {
                readerPage.readPage(link);
            } catch (IOException | IllegalArgumentException e) {
                continue;
            }
            break;
        }
    }

}