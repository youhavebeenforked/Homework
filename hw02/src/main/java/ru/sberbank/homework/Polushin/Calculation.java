package ru.sberbank.homework.Polushin;

import ru.sberbank.homework.common.Calculator;
import ru.sberbank.homework.utils.StringAnalyzer;

public class Calculation implements Calculator {
    private String result;

    @Override
    public String calculate(String userInput) {
        userInput = userInput.toUpperCase();

        result = new StringAnalyzer().analyze(userInput);

        return result;
    }
}