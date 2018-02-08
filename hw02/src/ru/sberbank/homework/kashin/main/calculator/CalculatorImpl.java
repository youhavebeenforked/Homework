package ru.sberbank.homework.kashin.main.calculator;

import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.kashin.main.util.Helper;
import ru.sberbank.homework.your_lastname.Calculator;

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
        String result = null;
        try {
            Expression expression = Helper.parser(userInput.trim());
            result = Helper.calculateHelper(expression);
        } catch (Exception e) {
            print("Что-то введено неверно. Попробуйте еще раз");
        }
        return result;
    }
}
