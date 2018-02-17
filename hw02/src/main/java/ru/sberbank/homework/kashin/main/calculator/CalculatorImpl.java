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
        ExpressionCalculator calc = new ExpressionCalculator();
        userInput = userInput.trim();
        if (checkWithRegExp(userInput, regExpTwoLiterals)) {
            return parse(userInput, calc);
        } else if (checkWithRegExp(userInput, regExpOneLiteral) && nonNull(ExpressionCalculator.getPreResult())) {
            userInput = ExpressionCalculator.getPreResult() + " " + userInput;
            return parse(userInput, calc);
        } else {
            return "error > wrong expression";
        }
    }

    private String parse(String userInput, ExpressionCalculator calc) {
        try {
            return calc.parser(userInput).calculate();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
