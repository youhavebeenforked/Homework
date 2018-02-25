package ru.sberbank.homework.Kudryavukh;

public class Main {

/*    Реализуйте метод readPage(String url), который отображает на консоль содержимое сайта,
    ссылка задаётся параметром url.
    Напишите программу, считывающую строку (URL ресурса) из консоли, после чего вызывается
    метод readPage(). Если URL неправильного формата или нет доступа до ресурса, пользователю
    предлагается повторить ввод.*/

//Test site:
//http://www.quizful.net/post/java-exceptions

    public static void main(String[] args) {
        sqr(234234);
        ParseHTML.inputURL();
        ParseHTML prs = new ParseHTML() {
            void method() {

            }
        };

    }

    public static double sqr(double arg) {
            while (true); // Удивительно, но КОМПИЛИРУЕТСЯ!
    }
}

