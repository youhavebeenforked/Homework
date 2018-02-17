package ru.sberbank.homework.dergun;

import ru.sberbank.homework.common.Calculator;

import java.math.BigInteger;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProxyCalculator implements Calculator {
    private static final double DBL_EPSILON = 1e-10;
    private static final String QUIT = "quit";
    private final EngineCalculator calculator = new EngineCalculatorImpl();
    private boolean firstScan = true;

    @Override
    public String calculate(String line) {
        final String[] split = line.split(" ");
        if (split.length == 1 && split[0].equals(QUIT)) {
            System.exit(0);
        }
        if (firstScan && split.length != 3) {
            return "error > wrong expression";
        }
        if (split.length == 2) {
            return oneArgument(split[0], split[1]);
        }
        if (split.length == 3) {
            firstScan = false;
            return twoArgument(split[0], split[1], split[2]);
        }
        return "error > wrong expression";
    }

    private String twoArgument(String A, String operator, String B) {
        double doubleA;
        try {
            doubleA = parseDouble(A);
        } catch (IllegalArgumentException e) {
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
                if (i == 0
                        || i + 1 == expression.length()
                        || expression.charAt(i - 1) == '_'
                        || expression.charAt(i + 1) == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    private double parseDouble(String p) {
        String s = p;
        try {
            boolean isLong = parseLong(s);
            if (isLong) {
                s = s.replaceAll("[l|L]$", "");
            }
            if (isValidUnderscore(s)) //matcher.matches())
                s = s.replaceAll("[_]", "");

            if (isLong) {
                if (parseBinary(s)) {
                    if (s.charAt(0) == '-') {
                        return - new BigInteger(s.substring(catBinary(s)), 2).longValue();
                    }
                    return new BigInteger(s.substring(catBinary(s)), 2).longValue();
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
            if (parseBinary(s)) {
                if (s.charAt(0) == '-') {
                    return - new BigInteger(s.substring(catBinary(s)), 2).intValue();
                }
                return new BigInteger(s.substring(catBinary(s)), 2).intValue();
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
            return Double.parseDouble(p);
        }
    }

    private int catBinary(String s) {
        if (parseBinary(s) && parseBinary("+" + s)) {
            return 2;
        }
        if (parseBinary(s)) {
            return 3;
        }
        return 0;
    }

    private boolean parseLong(String s) {
        Pattern pattern = Pattern.compile(".*[lL]$");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    private boolean parseBinary(String s) {
        if (s.length() > 2) {
            char one = s.charAt(0);
            char two = s.charAt(1);
            char three = s.charAt(2);
            return one == '0' && (two == 'b' || two == 'B') ||
                    (one == '+' || one == '-') && two == '0' && (three == 'b' || three == 'B');
        }
        return false;
    }
}

