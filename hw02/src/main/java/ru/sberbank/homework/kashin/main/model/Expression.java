package ru.sberbank.homework.kashin.main.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static ru.sberbank.homework.kashin.main.util.CalculateHelper.checkInteger;

public abstract class Expression {
    protected double first;
    protected double second;

    public double getFirst() {
        return first;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public abstract String calculate();

    public String roundNumber(double number) {
        if (checkInteger(number)) {
            return Long.toString(Math.round(number));
        } else {
            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.HALF_UP);
            String roundedResult = df.format(number).replaceAll(",", ".");
            while (roundedResult.contains(".") && roundedResult.endsWith("0")) {
                roundedResult = roundedResult.substring(0, roundedResult.length() - 1);
            }
            return roundedResult;
        }
    }
}
