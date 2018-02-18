package ru.sberbank.homework.kashin.main.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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
            df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
            return df.format(number);
        }
    }

    private boolean checkInteger(double checkNumber) {
        return (checkNumber == Math.floor(checkNumber)) && !Double.isInfinite(checkNumber);
    }
}
