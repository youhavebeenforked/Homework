package ru.sberbank.homework.karaush.UrlReader;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UrlReader reader = new UrlReader();
        Scanner scanner = new Scanner(System.in);
        boolean success = false;
        System.out.println("enter url");

        String url;
        do {
            try {
                url = scanner.nextLine();
                success = reader.readPage(url);
            } catch (IncorrectUrlFormatException e) {
                System.out.println("incorrect format of url: \"http(s)://domain.com(ru, org, etc)\"");
            }
        } while (!success);
    }
}
