package ru.sberbank.homework.bobrov.url;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

/**
 * WWW Reader.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 15.02.2018
 */


public class Main {
    public static void main(String[] args) {
        WebReader reader = new WebReader();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Please Enter URL or type quit");
                String inputUrl = sc.nextLine();
                if (inputUrl.equals("quit")) {
                    System.exit(0);
                }
                reader.readPage(inputUrl);
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
