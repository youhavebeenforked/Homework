package ru.sberbank.homework.kashin.main.calculator;

import ru.sberbank.homework.common.Calculator;

import static java.util.Objects.nonNull;
import static ru.sberbank.homework.kashin.main.util.CalculateHelper.*;

public class CalculatorImpl implements Calculator {
    private static final String regExpOneLiteral = "^(\\+|-|\\*|/) (\\+|-)?\\w+(.\\w+)?$";
    private static final String regExpTwoLiterals = "^(\\+|-)?\\w+(.\\w+)? (\\+|-|\\*|/) (\\+|-)?\\w+(.\\w+)?$";

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
        if (checkWithRegExp(userInput.trim(), regExpTwoLiterals)) {
            try {
                return parser(userInput.trim()).calculate();
            } catch (Exception e) {
                return e.getMessage();
            } finally {
                originalLiteralsClear();
            }

        } else if (checkWithRegExp(userInput.trim(), regExpOneLiteral) && nonNull(getPreResult())) {
            userInput = getPreResult() + " " + userInput;
            try {
                return parser(userInput.trim()).calculate();
            } catch (Exception e) {
                return e.getMessage();
            } finally {
                originalLiteralsClear();
            }

        } else {
            return "error > wrong expression";
        }
    }
}
