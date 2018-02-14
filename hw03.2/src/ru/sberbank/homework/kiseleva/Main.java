package ru.sberbank.homework.kiseleva;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Железяка on 30.04.2015.
 */

/**
 * Реализуйте метод readPage(String url), который отображает на консоль содержимое сайта, ссылка задаётся параметром url.
 * <p>
 * Напишите программу, считывающую строку (URL ресурса) из консоли, после чего вызывается метод readPage().
 * Если URL неправильного формата или нет доступа до ресурса, пользователю предлагается повторить ввод.
 */
public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("please enter URL: ");
                PageReader pageReader = new PageReader();
                Scanner scanner = new Scanner(System.in);
                String url = scanner.nextLine();
                pageReader.readPage(url);
                if (PageReader.isSuccess) {
                    break;
                }
            } catch (IOException e) {
                System.out.println("URL not found! repeat input");
            }
        }
    }
}