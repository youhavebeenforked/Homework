package ru.sberbank.homework.kashin.main.calculator;

import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.kashin.main.util.Helper;
import ru.sberbank.homework.your_lastname.Calculator;

public class CalculatorImpl implements Calculator {

    /**
     * Обрабатывает пользовательский ввод.
     * примеры команд:
     * 0345 * 0b10101
     * / 1.04
     * quit
     *
     * @param userInput команда пользователя.
     * @return отформатированный результат вычисления
     */
    @Override
    public String calculate(String userInput) {
        Expression expression = Helper.parser(userInput);
        String result = Helper.calculateHelper(expression);
        return result;
    }
}
