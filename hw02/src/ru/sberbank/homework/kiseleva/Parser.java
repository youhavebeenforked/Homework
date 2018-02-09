package ru.sberbank.homework.kiseleva;

/**
 * Created by Ekaterina Kiseleva on 09.02.2018.
 */
public class Parser {
    CalculatorImpl calculatorImpl = new CalculatorImpl();

    public void parse(String userInput) {
        String[] parsedArr = userInput.split(" ");
        if (parsedArr.length == 3) {
            calculatorImpl.firstInput(parsedArr);
        }
        else if (parsedArr.length == 2) {
            calculatorImpl.continueInput(parsedArr);
        }
    }
}
