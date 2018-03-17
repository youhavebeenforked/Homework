package ru.sberbank.homework.bedarev.checktype;

import java.math.BigInteger;

public class CheckTypeLong extends CheckTypeFloat {

    public CheckTypeLong(String stringNum) {
        super(stringNum);
    }

    public Double check() {

        stringNum = stringNum.substring(0, stringNum.length() - 1);

        if (stringNum.matches("[+-]?0b[0-1]+")) {
            return isMaxValue(new BigInteger(stringNum.replace("0b",""), 2));
        }

        if (stringNum.matches("[+-]?0[0-7]+")) {
            return isMaxValue(new BigInteger(stringNum, 8));
        }

        if (stringNum.matches("[+-]?0x[0-9a-f]+")) {
            return isMaxValue(new BigInteger(stringNum.replace("0x",""), 16));
        }

        return isMaxValue(new BigInteger(stringNum, 10));
        //Не знаю как ещё можно обработать overflow...(
    }

    Double isMaxValue (BigInteger verifiedValue) {
        final BigInteger MAX_LONG_VALUE = new BigInteger(Long.toString(Long.MAX_VALUE));
        Integer moreThenMax = MAX_LONG_VALUE.compareTo(verifiedValue);

        if (moreThenMax == -1) {
            return -1.0;
        }

        return verifiedValue.doubleValue();
    }

}
