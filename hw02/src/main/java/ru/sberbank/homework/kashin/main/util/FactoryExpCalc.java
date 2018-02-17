package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.calculator.ExpressionCalculator;

import static java.util.Objects.isNull;

public class FactoryExpCalc {
    private static ExpressionCalculator expressionCalculator;

    public static ExpressionCalculator getExpressionCalculator() {
        if  (isNull(expressionCalculator)) {
            expressionCalculator = new ExpressionCalculator();
            return expressionCalculator;
        } else {
            return expressionCalculator;
        }
    }

    public static void expressionCalculatorClear() {
        expressionCalculator = null;
    }
}
