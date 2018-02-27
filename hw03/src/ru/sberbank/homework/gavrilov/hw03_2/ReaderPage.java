package ru.sberbank.homework.gavrilov.hw03_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReaderPage {

    /**
     * Regular expression for the website address.
     */
    private static String REG_VALID_URL = "^(?:(?:https?|ftp|telnet)://(?:[a-z0-9_-]{1,32}(?::[a-z0-9_-]{1,32})?@)?)?(?:(?:[a-z0-9-]{1,128}\\.)+(?:ru|su|com|net|org|mil|edu|arpa|gov|biz|info|aero|inc|name|[a-z]{2})|(?!0)(?:(?!0[^.]|255)[0-9]{1,3}\\.){3}(?!0|255)[0-9]{1,3})(?:/[a-z0-9.,_@%&?+=\\~/-]*)?(?:#[^ '\\\"&]*)?$";

    /**
     * Returns web-page content in console.
     *
     * @param url the website address.
     * @throws IOException - if incorrect website address.
     */
    public void readPage(String url) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            if (!url.matches(REG_VALID_URL)) {
                throw new MalformedURLException();
            }
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (MalformedURLException | IllegalArgumentException e) {
            System.out.println("URL is not valid!");
            throw e;
        } catch (IOException e) {
            System.out.println("Error! Access denied!");
            throw e;
        }
    }
}
