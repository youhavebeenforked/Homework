package ru.sberbank.homework.dergun.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class UrlConnect {
    private URL url;

    void start() {
        while (true) {
            String url = urlRead();
            if (readPage(url)) {
                break;
            }
        }
    }

    boolean readPage(String url) {
        if (!urlValidator(url)) {
            return false;
        }
        try {
            URLConnection conn = this.url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                System.out.println(inputLine);
            }
            br.close();
            System.out.println("Done");

        } catch (IOException ignored) {
            System.err.println("Error");
        }
        return true;
    }

    String urlRead() {
        System.out.println("Enter the URL:");
        Scanner in = new Scanner(System.in);
        String url = in.next();
        return url;
    }

    boolean urlValidator(String url) {
        try {
            this.url = new URL(url);
            this.url.toURI();
            return true;
        } catch (URISyntaxException | MalformedURLException e) {
            System.out.println("Can't install connection. Reply.");
            return false;
        }
    }
}
