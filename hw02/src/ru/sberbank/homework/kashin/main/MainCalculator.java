package ru.sberbank.homework.kashin.main;

import ru.sberbank.homework.kashin.main.calculator.CalculatorImpl;
import ru.sberbank.homework.kashin.main.util.Helper;
import ru.sberbank.homework.your_lastname.Calculator;

import static java.util.Objects.nonNull;
import static ru.sberbank.homework.kashin.main.util.Helper.print;

public class MainCalculator {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        String userInput;
        while (true) {
            print("Введите выражение или quit для выхода");
            userInput = Helper.readConsole();
            if (userInput == null || userInput.length() == 0) {
                print("Вы ввели пустую строку");
                break;
            } else if (userInput.equals("quit")) {
                print("Пока");
                break;
            } else {
                String result = calculator.calculate(userInput);
                if (nonNull(result)) {
                    print(result);
                }
            }
        }
    }
}
