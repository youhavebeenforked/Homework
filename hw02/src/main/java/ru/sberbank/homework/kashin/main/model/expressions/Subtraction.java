package ru.sberbank.homework.kashin.main.model.expressions;

import ru.sberbank.homework.kashin.main.calculator.ExpressionCalculator;
import ru.sberbank.homework.kashin.main.model.Expression;

import static ru.sberbank.homework.kashin.main.util.FactoryExpCalc.getExpressionCalculator;

public class Subtraction extends Expression {
    @Override
    public String calculate() {
        ExpressionCalculator calculator = getExpressionCalculator();
        Double result = first - second;
        calculator.setPreResult(result);
        return roundNumber(result);
    }
}
