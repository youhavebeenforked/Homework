package ru.sberbank.homework.kashin.main.model.expressions;

import ru.sberbank.homework.kashin.main.model.Expression;

import static ru.sberbank.homework.kashin.main.calculator.ExpressionCalculator.setPreResult;

public class Division extends Expression {
    @Override
    public String calculate() {
        if (Math.floor(second) == 0) {
            throw new RuntimeException(String.format("error > %s", second));
        }

        Double result = first / second;
        setPreResult(result);
        return roundNumber(result);
    }
}
