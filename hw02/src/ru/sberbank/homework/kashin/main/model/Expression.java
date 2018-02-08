package ru.sberbank.homework.kashin.main.model;

import ru.sberbank.homework.your_lastname.Operation;

public class Expression {
    private double first;
    private double second;
    private Operation operator;

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

    public Operation getOperator() {
        return operator;
    }

    public void setOperator(Operation operator) {
        this.operator = operator;
    }
}
