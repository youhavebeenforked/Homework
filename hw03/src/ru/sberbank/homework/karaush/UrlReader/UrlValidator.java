package ru.sberbank.homework.karaush.UrlReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class UrlValidator {

    void validate(String url) {
        if (!url.matches("^(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[.]+[-a-zA-Z0-9+&@#/%=~_|]+")) {
            throw new IncorrectUrlFormatException("url is incorrect");
        }
    }
}
