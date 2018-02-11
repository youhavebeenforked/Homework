package ru.sberbank.homework.kashin.main.calculator;

import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.common.Calculator;

import java.util.Objects;

import static java.util.Objects.nonNull;
import static ru.sberbank.homework.kashin.main.util.CalculateHelper.checkWithRegExp;
import static ru.sberbank.homework.kashin.main.util.CalculateHelper.getPreResult;
import static ru.sberbank.homework.kashin.main.util.CalculateHelper.parser;

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
        if (checkWithRegExp(userInput.trim(),"^\\w+(.\\w+)? (\\+|-|\\*|/) \\w+(.\\w+)?$")) {
            //goto
            try {
                return parser(userInput.trim()).calculate();
            } catch (Exception e) {
                return e.getMessage();
            }

        } else if (checkWithRegExp(userInput.trim(), "^(\\+|-|\\*|/) \\w+(.\\w+)?$") && nonNull(getPreResult())) {
            userInput = getPreResult() + " " + userInput;
            try {
                return parser(userInput.trim()).calculate();
            } catch (Exception e) {
                return e.getMessage();
            }

        } else {
            return "error > wrong expression";
        }
    }
}
