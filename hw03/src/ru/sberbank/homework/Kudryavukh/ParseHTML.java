package ru.sberbank.homework.Kudryavukh;

/*    Реализуйте метод readPage(String url), который отображает на консоль содержимое сайта,
    ссылка задаётся параметром url.
    Напишите программу, считывающую строку (URL ресурса) из консоли, после чего вызывается
    метод readPage(). Если URL неправильного формата или нет доступа до ресурса, пользователю
    предлагается повторить ввод.*/

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.Scanner;

public class ParseHTML {

    private static String readPage(String url) throws Exception {

        Document doc = Jsoup.connect(url).get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Title WebPage: ")
                .append(doc.title())
                .append("\n*********************************\n")
                //.append(doc.text())
                .append(doc.html());
        return stringBuilder.toString();
    }

    public static void inputURL() {

        try {
            System.out.println("Введите URL сайта ");
            Scanner in = new Scanner(System.in);
            String inputURL = in.nextLine();
            System.out.println(readPage(inputURL));
        }
        catch (Exception e) {
            System.out.println("Ошибка ввода. Неверный формат URL или отсутствует доступ");
            System.out.println("Попробуйте еще раз!");
            inputURL();
        }
    }

}
