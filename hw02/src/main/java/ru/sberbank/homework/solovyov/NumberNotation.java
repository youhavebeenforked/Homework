package ru.sberbank.homework.solovyov;

import java.math.BigDecimal;

public enum NumberNotation {
    DECIMAL(0, 10),
    HEXADECIMAL(2, 16),
    BINARY(2, 2),
    OCTAL(1, 8);

    NumberNotation(int prefixLength, int base) {
        this.prefixLength = prefixLength;
        this.base = base;
    }

    private int prefixLength;
    private int base;

    public BigDecimal getDecimal(String number) {
        number = number.replaceAll("_+", "");
        if (base != 10) {
            if (number.charAt(0) == '+' | number.charAt(0) == '-') {
                number = number.charAt(0) + number.substring(prefixLength + 1);
            }
            return new BigDecimal(Long.parseLong(number, base));
        } else {
            return new BigDecimal(number);
        }

    }

}
