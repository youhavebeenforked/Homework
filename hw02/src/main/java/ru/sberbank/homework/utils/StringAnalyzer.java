package ru.sberbank.homework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAnalyzer {
    private Pattern pattern =
            Pattern.compile("^([(]*(" + RegularExpr.NUMBER.getRegExp() + RegularExpr.OPERATOR.getRegExp() + ")" +
                    "|" + RegularExpr.SHORT_OPERATOR.getRegExp() + ")[(]*" + RegularExpr.NUMBER.getRegExp()
                    + "(" + RegularExpr.OPERATOR.getRegExp() + "[(]*" + RegularExpr.NUMBER.getRegExp() + "[)]*" + ")*[)]*$");
    private Matcher matcher;
    private static final String ERROR = "error > wrong expression";

    public String analyze(String input) {

        matcher = pattern.matcher(input);
        if (matcher.find() == false) {
            return ERROR;
        } else {
            return "good";
        }
    }
}