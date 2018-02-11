package ru.sberbank.homework.kashin.main.model.expressions;

import ru.sberbank.homework.kashin.main.model.Expression;

import static ru.sberbank.homework.kashin.main.util.CalculateHelper.setPreResult;

public class Multiplication extends Expression {
    @Override
    public String calculate() {
        Double result = first * second;
        setPreResult(result);
        return roundNumber(result);
    }
}
