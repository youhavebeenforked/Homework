package ru.sberbank.homework.kashin.main.model.expressions;

import ru.sberbank.homework.kashin.main.calculator.ExpressionCalculator;
import ru.sberbank.homework.kashin.main.exception.WrongExpression;
import ru.sberbank.homework.kashin.main.model.Expression;

import static ru.sberbank.homework.kashin.main.util.FactoryExpCalc.expressionCalculatorClear;
import static ru.sberbank.homework.kashin.main.util.FactoryExpCalc.getExpressionCalculator;

public class Division extends Expression {
    @Override
    public String calculate() {
        if (Math.floor(second) == 0) {
            expressionCalculatorClear();
            throw new WrongExpression(String.format("error > %s", second));
        }
        ExpressionCalculator calculator = getExpressionCalculator();
        Double result = first / second;
        calculator.setPreResult(result);
        return roundNumber(result);
    }
}
