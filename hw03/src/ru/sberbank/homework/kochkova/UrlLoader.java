package ru.sberbank.homework.kochkova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class UrlLoader {


    private void readPage(String url) throws IOException {
        URL page = new URL(url);
        URLConnection connection = page.openConnection();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine = in.readLine();
            while (inputLine != null) {
                System.out.println(inputLine);
                inputLine = in.readLine();
            }
        }
    }

    public void loadPage() {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            while (true) {
                try {
                    readPage(input);
                    break;
                } catch (IOException e) {
                    System.out.println("Something went wrong, try to repeat your input: ");
                    input = scanner.nextLine();
                }
            }
        }
    }
}
