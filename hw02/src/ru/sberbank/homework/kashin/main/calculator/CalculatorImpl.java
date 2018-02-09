package ru.sberbank.homework.kashin.main.calculator;

import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.your_lastname.Calculator;

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
        String result = "";
        try {
            Expression expression = parser(userInput.trim().replaceAll(",", "."));
            result = expression.calculate();
        } catch (RuntimeException e) {
            print(e.getMessage());
        } catch (Exception e) {
            print("Что-то введено неверно. Попробуйте еще раз");
        }
        return result;
    }
}
