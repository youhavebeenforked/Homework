package ru.sberbank.homework.maruev.hw3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Иван on 20.02.2018.
 */
public class WebReader {

    public void readPage(String inputLink) throws MalformedURLException {
        URL linkURL = new URL(inputLink);
        try {
            BufferedReader linkReader = new BufferedReader(new InputStreamReader(linkURL.openStream()));
            while (linkReader.readLine() != null) {
                System.out.println(linkReader.readLine());
            }
            linkReader.close();
        } catch (IOException e) {
            throw new MalformedURLException();
        }
    }

    public boolean isValid(String link) {
        URL linkURL;
        try {
            linkURL = new URL(link);
        } catch (MalformedURLException e) {
            return false;
        }

        try {
            linkURL.toURI();
        } catch (URISyntaxException e) {
            return false;
        }
        return true;
    }
}
