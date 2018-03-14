package ru.sberbank.homework.bedarev.checktype;

public class CheckTypeBin extends CheckTypeFloat {

    public CheckTypeBin(String stringNum) {
        super(stringNum);
    }

    public Double check() {
        String replace0b = stringNum.replace("0b","");
        return isMaxValue(Long.parseLong(replace0b ,2));
    }
}
