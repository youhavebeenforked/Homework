package ru.sberbank.homework.kuznecov.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PageReader {
    static boolean readPage(String url) throws IOException {
        BufferedReader br = null;
        try {
            URL pageURL = new URL(url);
            URLConnection uc = pageURL.openConnection();
            br = new BufferedReader(
                    new InputStreamReader(
                            uc.getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                System.out.println(inputLine);
            }
            return true;
        } catch (IOException e){
            System.out.println("IOException catched!");
            return false;
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}
