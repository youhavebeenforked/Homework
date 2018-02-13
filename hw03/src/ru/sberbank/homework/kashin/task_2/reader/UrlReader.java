package ru.sberbank.homework.kashin.task_2.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlReader {

    public void readPage(String url) throws IOException {
        URL address = new URL(url);
        BufferedReader br = new BufferedReader(new InputStreamReader(address.openConnection().getInputStream()));
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        br.close();
    }
}
