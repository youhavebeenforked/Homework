package ru.sberbank.homework.kiseleva;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class PageReader {
    public static boolean isSuccess;

    public void readPage(String urlAddress) throws IOException {
        URL url = new URL(urlAddress);
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
        String string = reader.readLine();
        while (string != null) {
            System.out.println(string);
            string = reader.readLine();
        }
        reader.close();
        isSuccess = true;
    }
}
