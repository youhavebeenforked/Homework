package ru.sberbank.homework.kalugin;

enum NumberType {
    BINARY(2, "^[01]+$"),
    HEX(16, "^[0-9A-Fa-f]+$"),
    OCTAL(8, "^[0-7]+$"),
    DECIMAL(10, "^[+-]{0,1}\\d*\\.{0,1}\\d+$");

    private String correspondingRegEx;
    private int radix;

    NumberType (int radix, String regEx) {
        this.radix = radix;
        correspondingRegEx = regEx;
    }

    int getRadix() {
        return radix;
    }

    String getRegEx() {
        return correspondingRegEx;
    }
}
