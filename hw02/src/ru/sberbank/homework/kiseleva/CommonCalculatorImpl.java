package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.interfaces.CommonCalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ekaterina Kiseleva on 11.02.2018.
 */
public class CommonCalculatorImpl implements CommonCalculator {
    @Override
    public Double sum(Number one, Number two) {
        return one.doubleValue() + two.doubleValue();
    }

    @Override
    public Double subtract(Number one, Number two) {
        return one.doubleValue() - two.doubleValue();
    }

    @Override
    public Double multiply(Number one, Number two) {
        return one.doubleValue() * two.doubleValue();
    }

    @Override
    public Double divide(Number one, Number two) {
        return one.doubleValue() / two.doubleValue();
    }


    @Override
    public Number cast(String originalNum) {
        String lowerCaseNum = originalNum.toLowerCase();
        try {
            if (checkWithRegExp(lowerCaseNum)) {
                if (lowerCaseNum.charAt(lowerCaseNum.length() - 1) == 'l') { //L
                    lowerCaseNum = lowerCaseNum.replaceAll("_", "");
                    lowerCaseNum = lowerCaseNum.substring(0, lowerCaseNum.length() - 1);
                }
                if (lowerCaseNum.length() > 1 && lowerCaseNum.charAt(1) == 'b') {
                    lowerCaseNum = replacing(lowerCaseNum);
                    if (lowerCaseNum.length() > 33 && lowerCaseNum.charAt(2) == '1') {
                        return -(Long.parseLong(negativeBin(lowerCaseNum), 2) + 1);
                    }
                    return Long.parseLong(lowerCaseNum.substring(2), 2);
                } else if (lowerCaseNum.length() > 1 && lowerCaseNum.charAt(1) == 'x') {
                    lowerCaseNum = replacing(lowerCaseNum);
                    if (lowerCaseNum.length() > 9 && lowerCaseNum.charAt(2) == 'f') {
                        return -(Long.parseLong(negativeHex(lowerCaseNum), 16) + 1);
                    }
                    return Long.parseLong(lowerCaseNum.substring(2), 16);
                } else if (lowerCaseNum.length() > 1 && lowerCaseNum.charAt(0) == '0') {
                    lowerCaseNum = lowerCaseNum.replaceAll("_", "");
                    return Long.parseLong(lowerCaseNum, 8);
                } else {
                    lowerCaseNum = lowerCaseNum.replaceAll("_", "");
                    return Long.parseLong(lowerCaseNum);
                }
            } else { //вещественный литерал
                if (lowerCaseNum.charAt(lowerCaseNum.length() - 1) == 'f' || lowerCaseNum.charAt(lowerCaseNum.length() - 1) == 'd') {
                    lowerCaseNum = lowerCaseNum.replaceAll("_", "");
                    lowerCaseNum = lowerCaseNum.substring(0, lowerCaseNum.length() - 1);
                }
                return Double.parseDouble(lowerCaseNum);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(originalNum);
        }
    }

    public String negativeHex(String lowerCaseNum) {
        byte[] b = lowerCaseNum.getBytes();
        byte[] newBytes = new byte[b.length - 3];
        for (int i = 3; i < b.length; i++) {
            newBytes[i - 3] = (byte) (102 - b[i] + 48); // F - b[i]
        }
        return new String(newBytes);
    }

    private String negativeBin(String lowerCaseNum) {
        byte[] b = lowerCaseNum.getBytes();
        byte[] newBytes = new byte[b.length - 3];
        for (int i = 3; i < b.length; i++) {
            if (b[i] == 49) {
                newBytes[i - 3] = 48; //заменяем 1 на 0
            } else newBytes[i - 3] = 49;
        }
        return new String(newBytes);
    }

    public String replacing(String originalNum) {
        if (originalNum.charAt(2) != '_') {
            originalNum = originalNum.replaceAll("_", "");
        }
        return originalNum;
    }

    public boolean checkWithRegExp(String num) {
        Pattern p = Pattern.compile("^0([0-9]*)?(b.*)?(x.*)?( )?$");
        Matcher m = p.matcher(num);
        return m.matches();
    }
}
