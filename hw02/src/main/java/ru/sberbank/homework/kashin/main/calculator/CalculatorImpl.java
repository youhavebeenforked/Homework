package ru.sberbank.homework.kashin.main.calculator;

import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.common.Calculator;

import static ru.sberbank.homework.kashin.main.util.Helper.parser;
import static ru.sberbank.homework.kashin.main.util.Helper.print;

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
        if (userInput.equalsIgnoreCase("(-?(0b|0x)?\\d+(.\\d+)? )(\\+|-|\\*|/) -?(0b|0x)?\\d+(.\\d+)?")) {

        }
        Expression expression = parser(userInput.trim().replaceAll(",", "."));
        String result = expression.calculate();
        return result;
    }
}
