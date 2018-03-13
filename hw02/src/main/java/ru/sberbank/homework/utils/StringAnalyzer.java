package ru.sberbank.homework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAnalyzer {
    private static final String ERROR = "error > wrong expression";
    /*
    Паттерн для выражний : а_@_b_@_c... или @_a_@_b...
     */
    private Pattern pattern =
            Pattern.compile("^([(]*(" + RegularExpr.NUMBER.getRegExp() + RegularExpr.OPERATOR.getRegExp() + ")" +
                    "|" + RegularExpr.SHORT_OPERATOR.getRegExp() + ")[(]*" + RegularExpr.NUMBER.getRegExp() + "[)]?"
                    + "(" + RegularExpr.OPERATOR.getRegExp() + "[(]*" + RegularExpr.NUMBER.getRegExp() + "[)]*" + ")*[)]*$");
    private Matcher matcher;

    /*
        Проверяем введеную пользователем строку на корректность.
        Если где-то допущена ощибка, то смотрим не ошибся ли пользователь в литерале.
        Если ошибка в литерале, то возращаем первый некорректный литерал.
        Если ошибка в чем-то другом, возращаем стандартное сообщение об ошибке.
        Если все хорошо, то возвращаем сообщение "good";
    */
    public String analyze(String input) {
        matcher = pattern.matcher(input.toUpperCase());

        if (matcher.find() == false) {
            input = input.replaceAll("[(,)]", "")
                    /*
                     нужно для проверки неакуратного пользователя. когда не поставлены пробелы: 1+1
                     */
                    .replaceAll("[0-9][+,\\-,*,/,^]", " ");

            String[] numbers = input.split("[ ]+");

            /*
            проверяем если это не число и не оператор, тогда ошибка в литерале
             */
            for (String s : numbers) {
                matcher = Pattern.compile("^(" + RegularExpr.NUMBER.getRegExp()
                        + "|" + "[+,\\-,*,/,^])" + "$")
                        .matcher(s.toUpperCase());

                /*
                Если не указать второе условие, то при вводе пустой строки будет "error> "
                вместо "error> wrong expression".
                 */
                if (matcher.find() == false && s.length() > 0) {

                    return ("error > " + s);
                }
            }
            return ERROR;
        } else {

            return "good";
        }
    }
}