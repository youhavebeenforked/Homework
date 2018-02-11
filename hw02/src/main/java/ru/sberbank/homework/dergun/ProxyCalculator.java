package ru.sberbank.homework.dergun;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProxyCalculator implements Calculator {
    private static final double DBL_EPSILON = 1e-10;
    private static final String QUIT = "quit";
    private static final String RESTART = "restart";
    private final EngineCalculator calculator = new EngineCalculatorImpl();
    private boolean firstScan = true;

    @Override
    public String calculate(String line) {
        final String[] split = line.split(" ");
        if (split.length == 1) {
            if (split[0].equals(QUIT)) {
                System.exit(0);
            } else if (split[0].equals(RESTART)) {
                firstScan = true;
                return "";
            }
        }
        double doubleA;
        int i = 0;
        if (firstScan) {
            if (split.length != 3) {
                return "error > wrong expression";
            }
            try {
                doubleA = parseDouble(split[i]);
            } catch (NumberFormatException e) {
                return "error > " + split[i];
            }
            i++;
            firstScan = false;
        } else {
            if (split.length != 2) {
                return "error > wrong expression";
            }
            doubleA = calculator.getValue();
        }
        Operation operation;
        try {

            if(split[i].length() != 1) {
                return "error > " + split[i];
            }
            operation = Operation.parse(split[i].charAt(0));
        } catch (IllegalArgumentException e) {
            return "error > " + split[i];
        }
        i++;
        double doubleB;
        try {
            doubleB = parseDouble(split[i]);
        } catch (NumberFormatException e) {
            return "error > " + split[i];
        }
        calculator.calculate(doubleA, operation, doubleB);
        return getValue();
    }

    private String getValue() {
        final double result = calculator.getValue();
        if (Math.abs(result - (long) result) <= DBL_EPSILON) {
            return Long.toString((long) result);
        }
        String res = String.format(Locale.ROOT, "%.2f", result);
        if(res.charAt(res.length() - 1) == '0') {
            res = res.substring(0, res.length() - 1);
            if(res.charAt(res.length() - 1) == '0') {
                res = res.substring(0, res.length() - 2);
            }
        }
        return res;
    }

    private double parseDouble(String s) {
        try {
            Pattern pattern = Pattern.compile("^([+]|-)?(0x|0b|0|#)?([0-9]+[_]?[0-9]+)*[0-9]*");
            Matcher matcher = pattern.matcher(s);
            if(matcher.matches())
                s = s.replaceAll("[_]", "");
            pattern = Pattern.compile("([+]|-)?(0x|0b|0|#)?[0-9]*((l|L)?)$");
            matcher = pattern.matcher(s);
            if(matcher.matches())
                s = s.replaceAll("l|L", "");
            if (s.length() > 2 && s.charAt(0) == '0' && s.charAt(1) == 'b') {
                return Integer.parseInt(s.substring(2), 2);
            } else {
                return Integer.decode(s);
            }
        } catch (NumberFormatException e) {
            return Double.parseDouble(s);
        }
    }
}
