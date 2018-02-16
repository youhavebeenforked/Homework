package ru.sberbank.homework.bobrov.url;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WWW Reader.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 15.02.2018
 */


public class WebReader {
    public void readPage(String url) throws MalformedURLException {
        if (urlValidator(url)) {
            outPage(url);
        } else {
            throw new MalformedURLException(String.format("Wrong URL %s", url));
        }
    }

    private boolean urlValidator(String url) {
        boolean result = false;
        String regexUrl = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
        Pattern pattern = Pattern.compile(regexUrl);
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            result = true;
        }
        return result;
    }

    private void outPage(String inputUrl) throws MalformedURLException {
        URL url = new URL(inputUrl);
        try (BufferedReader webReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            while (webReader.readLine() != null) {
                System.out.println(webReader.readLine());
            }
        } catch (IOException e) {
            throw new MalformedURLException(String.format("URL %s not found", url));
        }
    }
}
