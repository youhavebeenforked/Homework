package ru.sberbank.homework.andreev;

import java.util.Arrays;
import java.util.stream.Collectors;

class RegExp {
    static String INT_DECIMAL = "[+\\-]?(0|([1-9]([0-9_]*\\d)?))[lL]?";
    static String INT_OCTAL = "[+\\-]?0([0-7]|_)*[0-7][lL]?";
    static String INT_HEXADECIMAL = "[+\\-]?0[xX][0-9a-fA-F]([0-9a-fA-F_]*[0-9a-fA-F])?[lL]?";
    static String INT_BINARY = "[+\\-]?0[bB][01]([01_]*[01])?[lL]?";
    static String FLOAT = "[+\\-]?" + "(" +
            "((\\d([0-9_]*\\d)?)\\.(\\d([0-9_]*\\d)?))([eE][+\\-]?(\\d([0-9_]*\\d)?))?[fFdD]?" + "|" +
            "((\\d([0-9_]*\\d)?))([eE][+\\-]?(\\d([0-9_]*\\d)?))[fFdD]?" + "|" +
            "(\\d[fFdD])" + "|" +
            "(0[xX][0-9a-fA-F]([0-9a-fA-F_]*[0-9a-fA-F])?[pP][+\\-]?\\d([0-9_]*\\d)?)" + ")";
    static String LITERAL = "[+\\-]?" + "(" +
            "((0|([1-9]([0-9_]*\\d)?))[lL]?)" + "|" +
            "(0([0-7]|_)*[0-7][lL]?)" + "|" +
            "(0[xX][0-9a-fA-F]([0-9a-fA-F_]*[0-9a-fA-F])?[lL]?)" + "|" +
            "(0[bB][01]([01_]*[01])?[lL]?)" + "|" +
            "(" + "((\\d([0-9_]*\\d)?)\\.(\\d([0-9_]*\\d)?))([eE][+\\-]?(\\d([0-9_]*\\d)?))?[fFdD]?" + "|" +
            "((\\d([0-9_]*\\d)?))([eE][+\\-]?(\\d([0-9_]*\\d)?))[fFdD]?" + "|" +
            "(\\d[fFdD])" + "|" +
            "(0[xX][0-9a-fA-F]([0-9a-fA-F_]*[0-9a-fA-F])?[pP][+\\-]?\\d([0-9_]*\\d)?)" +
            ")" + ")";
    static String OPERATIONS = "[" + Arrays.stream(Operation.values())
            .map(Operation::getRegExpSymbol)
            .collect(Collectors.joining()) + "]";
}
