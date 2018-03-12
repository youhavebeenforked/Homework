package ru.sberbank.homework.abzaltdinov.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isURLReaded = false;
        while (true) {
            String url = in.next();
            try {
                readPage(url);
                break;
            } catch (MalformedURLException | IllegalArgumentException e) {
                System.err.println("Неверный формат ссылки. " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Ресурс недоступен. " + e.getMessage());
            }
        }
    }

    public static void readPage(String url) throws IOException {
        URL urlObject = new URL(url);
        URLConnection connection = urlObject.openConnection();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String nextLine;
            while ((nextLine = br.readLine()) != null) {
                System.out.println(nextLine);
            }
        }

    }
}
