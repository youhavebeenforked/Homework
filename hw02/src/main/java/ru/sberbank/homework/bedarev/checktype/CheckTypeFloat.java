package ru.sberbank.homework.bedarev.checktype;

public class CheckTypeFloat implements VerifyType {
    String stringNum;

    public CheckTypeFloat(String stringNum) {
        this.stringNum = stringNum;
    }

    public Double check() {
        return isMaxValue(Float.valueOf(stringNum).longValue());
    }

    Double isMaxValue(Long num) {
        if (num < Double.MAX_VALUE) {
            return num.doubleValue();
        }

        return Double.MAX_VALUE;
    }
}
