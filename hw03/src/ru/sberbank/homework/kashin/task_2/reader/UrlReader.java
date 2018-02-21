package ru.sberbank.homework.kashin.task_2.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static ru.sberbank.homework.kashin.task_2.reader.ConsoleHelper.print;

public class UrlReader {

    public boolean readPage(String url) {
        URL address;
        URLConnection connection;
        try {
            address = new URL(url);
            connection = requireNonNull(address.openConnection());
        } catch (Exception e) {
            print("url not available..");
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while (nonNull(line = reader.readLine())) {
                System.out.println(line);
            }
            return true;
        } catch (IOException e) {
            print("url not available..");
            return false;
        }

    }
}
