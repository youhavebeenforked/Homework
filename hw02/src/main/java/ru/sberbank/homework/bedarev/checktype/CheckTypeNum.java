package ru.sberbank.homework.bedarev.checktype;

public class CheckTypeNum extends CheckTypeFloat {

    public CheckTypeNum(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        return isMaxValue(Long.parseLong(stringNum,10));
    }
}
