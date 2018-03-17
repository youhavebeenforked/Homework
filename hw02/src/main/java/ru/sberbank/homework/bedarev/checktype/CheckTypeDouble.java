package ru.sberbank.homework.bedarev.checktype;

public class CheckTypeDouble extends CheckTypeFloat {

    public CheckTypeDouble(String stringNum) {
        super(stringNum);
    }

    @Override
    public Double check() {
        return Double.valueOf(stringNum);
    }
}
