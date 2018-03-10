package ru.sberbank.homework.solovyov.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter your url:");
        } while (!readPage(scanner.nextLine()));

    }

    private static boolean readPage(String urlString) {
        try (InputStream is = new URL(urlString).openStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            return true;
        } catch (MalformedURLException mue) {
            System.out.println("error > Malformed URL");
            return false;
        } catch (IOException ioe) {
            System.out.println("Can not connect to " + urlString);
            return false;
        }
    }
}
