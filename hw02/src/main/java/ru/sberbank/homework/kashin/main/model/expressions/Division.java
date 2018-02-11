package ru.sberbank.homework.kashin.main.model.expressions;

import ru.sberbank.homework.kashin.main.model.Expression;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static ru.sberbank.homework.kashin.main.util.CalculateHelper.checkInteger;
import static ru.sberbank.homework.kashin.main.util.CalculateHelper.setPreResult;

public class Division extends Expression {
    @Override
    public String calculate() {
        if (Math.floor(second) == 0) {
            throw new RuntimeException(String.format("error > %s", second));
        }

        Double result = first / second;
        setPreResult(result);

        if (checkInteger(result)) {
            return Long.toString(Math.round(result));
        } else {
            String roundedResult = new BigDecimal(result).setScale(3, RoundingMode.UP).toString();
            while (roundedResult.endsWith("0")) {
                roundedResult = roundedResult.substring(0, roundedResult.length() - 1);
            }
            return roundedResult;
        }
    }
}
