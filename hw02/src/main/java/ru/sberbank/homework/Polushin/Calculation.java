package ru.sberbank.homework.Polushin;

import ru.sberbank.homework.common.Calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculation implements Calculator {
    private Pattern pattern = Pattern.compile("/d");
    private Matcher matcher;
    @Override
    public String calculate(String userInput) {
        userInput.toUpperCase();
        switch (userInput) {
            case ("QUIT"):
                Operation.EXIT.toString();
                break;
            default:
                matcher = pattern.matcher(userInput);
                

        }
        return null;
    }
}
