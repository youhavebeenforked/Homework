package ru.sberbank.homework.karaush.UrlReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class UrlReader {

    private static UrlValidator validator = new UrlValidator();

    public boolean readPage(String pageUrl) {

        validator.validate(pageUrl);
        URL url;
        BufferedReader in;

        try {
            url = new URL(pageUrl);
        } catch (MalformedURLException e) {
            System.out.println("Incorrect url(thrown by java.net.URL class)");
            return false;
        }

        try {
            in = new BufferedReader(
                    new InputStreamReader(url.openStream()));
        } catch (UnknownHostException e) {
            System.out.println("incorrect host, try again");
            return false;
        } catch (IOException e) {
            System.out.println("incorrect arguments, try again");
            return false;
        }
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("IOException while reading");
        }

        return true;
    }

}
