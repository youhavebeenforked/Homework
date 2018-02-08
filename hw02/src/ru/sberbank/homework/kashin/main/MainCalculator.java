package ru.sberbank.homework.kashin.main;

import ru.sberbank.homework.kashin.main.calculator.CalculatorImpl;
import ru.sberbank.homework.kashin.main.util.Helper;
import ru.sberbank.homework.your_lastname.Calculator;

public class MainCalculator {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        String userInput = Helper.readConsole();
        String result = calculator.calculate(userInput);
        Helper.print(result);
    }
}
