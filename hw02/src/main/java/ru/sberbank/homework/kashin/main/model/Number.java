package ru.sberbank.homework.kashin.main.model;

public abstract class Number {
    public abstract String checkAndParse(String number, int item);
    protected String parse(String number, int numStartWith, int radix) {
        return String.valueOf(Long.parseLong(number.substring(numStartWith, number.length()), radix));
    }
}
