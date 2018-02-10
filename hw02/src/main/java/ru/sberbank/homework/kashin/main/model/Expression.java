package ru.sberbank.homework.kashin.main.model;

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
}
