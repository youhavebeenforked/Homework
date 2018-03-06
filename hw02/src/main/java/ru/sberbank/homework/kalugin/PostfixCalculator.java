package ru.sberbank.homework.kalugin;

import java.math.*;
import java.util.List;

import static  ru.sberbank.homework.kalugin.InputValidator.validate;
import static  ru.sberbank.homework.kalugin.PostfixParser.createPostfix;
import static  ru.sberbank.homework.kalugin.PostfixParser.evaluatePostfix;

import ru.sberbank.homework.common.Calculator;

/**
 * Имплементация калькулятора на основе постифксных матем. выражений.
 */
public class PostfixCalculator implements Calculator {
    private static final String QUIT_COMMAND = "quit";
    private static String result;

    public String calculate(String userInput) {
        if (userInput == null) return null;
        if (userInput.equals(QUIT_COMMAND)) {
            return (result = null);
        }

        final ErrorMessage error = new ErrorMessage();

        final List<Element> equation = validate(userInput, result, error);

        if (error.hasMessage()) {
            result = null;
            return error.getMessage();
        }

        final List<Element> postfixEquation = createPostfix(equation);

        final Double answer = evaluatePostfix(postfixEquation);
        result = prepareResult(answer);

        return result;
    }

    private String prepareResult(Double answer) {
        // округляем до 2-х знаков после запятой
        answer = new BigDecimal(answer).setScale(2, RoundingMode.HALF_UP).doubleValue();
        // в случае если после округления число оканчивается только нулями после точки,
        // уберем и их из ответа
        if ((answer - Math.round(answer)) != 0) {
            return String.valueOf(answer);
        }
        else {
            return String.valueOf(Math.round(answer));
        }
    }
}

