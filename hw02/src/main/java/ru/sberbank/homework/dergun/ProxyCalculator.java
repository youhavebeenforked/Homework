package ru.sberbank.homework.dergun;

import ru.sberbank.homework.common.Calculator;

import java.math.BigInteger;
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
        if (split.length == 1 && split[0].equals(QUIT)) {
            System.exit(0);
        }

        if (split.length == 2) {
            return oneArgument(split[0], split[1]);
        }
        if (split.length == 3) {
            return twoArgument(split[0], split[1], split[2]);
        }
        return "error > wrong expression";
    }

    private String twoArgument(String A, String operator, String B) {
        double doubleA;
        try {
            doubleA = parseDouble(A);
        } catch (NumberFormatException e) {
            return "error > " + A;
        }

        Operation operation;
        try {
            operation = Operation.parse(operator.charAt(0));
        } catch (IllegalArgumentException e) {
            return "error > " + operator;
        }

        double doubleB;
        try {
            doubleB = parseDouble(B);
        } catch (IllegalArgumentException e) {
            return "error > " + B;
        }
        calculator.calculate(doubleA, operation, doubleB);
        return getValue();
    }

    private String oneArgument(String operator, String B) {
        double doubleA = calculator.getValue();

        Operation operation;
        try {
            operation = Operation.parse(operator.charAt(0));
        } catch (IllegalArgumentException e) {
            return "error > " + operator;
        }

        double doubleB;
        try {
            doubleB = parseDouble(B);
        } catch (IllegalArgumentException e) {
            return "error > " + B;
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
        if (res.charAt(res.length() - 1) == '0') {
            res = res.substring(0, res.length() - 1);
            if (res.charAt(res.length() - 1) == '0') {
                res = res.substring(0, res.length() - 2);
            }
        }
        return res;
    }

    private boolean isValidUnderscore(final String expression) {
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '_') {
                if (i == 0 || i + 1 == expression.length() || !Character.isDigit(expression.charAt(i - 1)) || !Character.isDigit(expression.charAt(i + 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    private double parseDouble(String s) {
        try {
            if (isValidUnderscore(s)) //matcher.matches())
                s = s.replaceAll("[_]", "");
            Pattern pattern = Pattern.compile("[+-]?(0x|0b|0|#)?[0-9]*[lL]$");
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                s = s.replaceAll("l|L", "");
                if (s.length() > 2 && s.charAt(0) == '0' && s.charAt(1) == 'b') {
                    return new BigInteger(s.substring(2), 2).longValue();
                } else {
                    if (s.length() > 1 && s.charAt(0) == '0' && Character.isDigit(s.charAt(1))) {
                        String res = s.replaceAll("^0+", "");
                        Long.decode(res);
                        try {
                            return Long.decode(s);
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException();
                        }
                    }
                    return Long.decode(s);
                }
            }
            if (s.length() > 2 && s.charAt(0) == '0' && s.charAt(1) == 'b') {
                return new BigInteger(s.substring(2), 2).intValue();
            } else {
                if (s.length() > 1 && s.charAt(0) == '0' && Character.isDigit(s.charAt(1))) {
                    String res = s.replaceAll("^0+", "");
                    Integer.decode(res);
                    try {
                        return Integer.decode(s);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException();
                    }
                }
                return Integer.decode(s);
            }
        } catch (NumberFormatException e) {
            return Double.parseDouble(s);
        }
    }
}

