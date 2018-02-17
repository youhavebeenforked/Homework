package ru.sberbank.homework.kashin.main.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public abstract class Expression {
    protected double first;
    protected double second;

    public void setFirst(double first) {
        this.first = first;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public abstract String calculate();

    protected String roundNumber(double number) {
        if (checkInteger(number)) {
            return Long.toString(Math.round(number));
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.HALF_UP);
            String roundedResult = df.format(number).replaceAll(",", ".");
            while (roundedResult.contains(".") && roundedResult.endsWith("0")) {
                roundedResult = roundedResult.substring(0, roundedResult.length() - 1);
            }
            return roundedResult;
        }
    }

    private static boolean checkInteger(double checkNumber) {
        return (checkNumber == Math.floor(checkNumber)) && !Double.isInfinite(checkNumber);
    }
}
