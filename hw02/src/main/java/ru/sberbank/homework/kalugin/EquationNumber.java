package ru.sberbank.homework.kalugin;

public class EquationNumber implements Element<Double> {
    private double number;

    EquationNumber (double d) {
        number = d;
    }

    @Override
    public Double getElement() {
        return number;
    }
    @Override
    public boolean isNumber() {
        return true;
    }
}
