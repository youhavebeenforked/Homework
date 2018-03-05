package ru.sberbank.homework.andreev.second;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

class SimplePageDownloader {
    private static URL url;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("write URL:");
        while (true) {
            String input = sc.nextLine();
            if ("exit".equals(input.toLowerCase())) {
                break;
            }
            readPage(input);
        }
    }

    private static void readPage(String pageUrl) {
        if (!generateURL(pageUrl)) {
            System.out.println("wrong page url");
            return;
        }
        if (!printPage()) {
            System.out.println("trouble with download");
        }
    }

    private static boolean generateURL(String pageUrl) {
        try {
            url = new URL(pageUrl);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }

    private static boolean printPage() {
        BufferedReader br;
        String line;
        try (InputStream is = url.openStream()) {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }


}