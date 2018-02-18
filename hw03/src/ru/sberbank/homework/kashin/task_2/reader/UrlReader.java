package ru.sberbank.homework.kashin.task_2.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static java.util.Objects.nonNull;
import static ru.sberbank.homework.kashin.task_2.reader.ConsoleHelper.print;

public class UrlReader {

    public int readPage(String url) {
        URL address;
        URLConnection connection;
        try {
            address = new URL(url);
            connection = address.openConnection();
        } catch (IOException e) {
            print("url not available..");
            return 0;
        }
        if (nonNull(connection)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while (nonNull(line = reader.readLine())) {
                    System.out.println(line);
                }
                return 1;
            } catch (IOException e) {
                print("url not available..");
                return 0;
            }
        } else {
            print("url not available..");
            return 0;
        }
    }
}
