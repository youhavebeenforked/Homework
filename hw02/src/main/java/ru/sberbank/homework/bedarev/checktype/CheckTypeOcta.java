package ru.sberbank.homework.bedarev.checktype;

public class CheckTypeOcta extends CheckTypeBin {

    public CheckTypeOcta(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        return isMaxValue(Long.parseLong(stringNum,8));
    }
}