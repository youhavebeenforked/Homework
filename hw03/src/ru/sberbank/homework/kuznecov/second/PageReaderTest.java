package ru.sberbank.homework.kuznecov.second;

import java.io.IOException;
import java.util.Scanner;

public class PageReaderTest {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the url");
            String input = scanner.nextLine();
            boolean answer = PageReader.readPage(input);
            if (answer) {
                break;
            } else {
                System.out.println("Try again");
            }
        } while (true);
    }
}
