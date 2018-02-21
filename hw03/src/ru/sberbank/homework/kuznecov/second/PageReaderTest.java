package ru.sberbank.homework.kuznecov.second;

import java.io.IOException;
import java.util.Scanner;

public class PageReaderTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the url");
                String input = scanner.nextLine();
                boolean answer = PageReader.readPage(input);
                if (answer) {
                    break;
                } else {
                    System.out.println("Try again");
                }
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
