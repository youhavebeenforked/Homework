package ru.sberbank.homework.kashin.main.calculator;

import ru.sberbank.homework.kashin.main.exception.WrongExpression;
import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.kashin.main.model.Number;
import ru.sberbank.homework.kashin.main.util.CalculateHelper;
import ru.sberbank.homework.kashin.main.util.FactoryExpression;
import ru.sberbank.homework.kashin.main.util.FactoryNumber;

import java.util.HashMap;
import java.util.Map;

import static ru.sberbank.homework.kashin.main.util.FactoryExpCalc.expressionCalculatorClear;

public class ExpressionCalculator {
    private Double preResult;
    public static final Map<Integer, String> ORIGINAL_LITERALS = new HashMap<>();

    public Double getPreResult() {
        return preResult;
    }

    public void setPreResult(Double preResult) {
        this.preResult = preResult;
    }

    public Double checkNotation(String number, int item) {
        number = CalculateHelper.checkEndWithL(number);

        Number num = FactoryNumber.getNumber(number);

        try {
            return Double.valueOf(num.checkAndParse(number, item));
        } catch (Exception e) {
            expressionCalculatorClear();
            throw new WrongExpression(String.format("error > %s", ORIGINAL_LITERALS.get(item)));
        }
    }

    public Expression parser(String expString) {
        String[] elements = expString.split(" ");
        ORIGINAL_LITERALS.put(1, elements[0]);
        ORIGINAL_LITERALS.put(2, elements[2]);
        String firstElement = elements[0].toLowerCase();
        String secondElement = elements[2].toLowerCase();
        if (CalculateHelper.checkIncorrectUnderscore(firstElement)) {
            expressionCalculatorClear();
            throw new WrongExpression(String.format("error > %s", ORIGINAL_LITERALS.get(1)));
        }
        if (CalculateHelper.checkIncorrectUnderscore(secondElement)) {
            expressionCalculatorClear();
            throw new WrongExpression(String.format("error > %s", ORIGINAL_LITERALS.get(2)));
        }
        firstElement = firstElement.replaceAll("_", "");
        secondElement = secondElement.replaceAll("_", "");
        if (!CalculateHelper.checkWithRegExp(firstElement, CalculateHelper.CORRECT_LITERAL_REG_EXP) || CalculateHelper.checkIncorrectOctal(firstElement)) {
            expressionCalculatorClear();
            throw new WrongExpression(String.format("error > %s", ORIGINAL_LITERALS.get(1)));
        }
        if (!CalculateHelper.checkWithRegExp(secondElement, CalculateHelper.CORRECT_LITERAL_REG_EXP) || CalculateHelper.checkIncorrectOctal(secondElement)) {
            expressionCalculatorClear();
            throw new WrongExpression(String.format("error > %s", ORIGINAL_LITERALS.get(2)));
        }
        Expression expression = FactoryExpression.getExpression(elements[1].charAt(0));
        expression.setFirst(checkNotation(firstElement, 1));
        expression.setSecond(checkNotation(secondElement, 2));
        return expression;

    }
}
