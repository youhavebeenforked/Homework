package ru.sberbank.homework.dergun;

import java.util.Locale;

public class ProxyCalculator implements Calculator {
    private static final double DBL_EPSILON = 1e-10;
    private static final String QUIT = "quit";
    private static final String RESTART = "restart";
    private final EngineCalculator calculator;
    private boolean firstScan = true;

    public ProxyCalculator(EngineCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public String calculate(String userInput) {
        return firstScan ? firstScan(userInput) : secondScan(userInput);
    }

    private String getValue() {
        final double result = calculator.getValue();
        if (Math.abs(result - (long) result) <= DBL_EPSILON) {
            return Long.toString((long) result);
        }
        return String.format(Locale.ROOT, "%.2f", result);
    }

    private double parseDouble(String s) {
        try {
            if (s.length() > 2 && s.charAt(0) == '0' && s.charAt(1) == 'b') {
                return Integer.parseInt(s.substring(2), 2);
            } else {
                return Integer.decode(s);
            }
        } catch (NumberFormatException e) {
            return Double.parseDouble(s);
        }
    }

    private String firstScan(String line) {
        final String[] split = line.split(" ");

        if (split.length == 1) {
            if (split[0].equals(QUIT)) {
                System.exit(0);
            } else if (split[0].equals(RESTART)) {
                return "Restart done";
            }
        }

        if (split.length != 3) {
            throw new IllegalArgumentException("Wrong input format! Please write so: a_@_b");
        }

        final Operation operation = Operation.parse(split[1]);
        final double doubleA = parseDouble(split[0]);
        final double doubleB = parseDouble(split[2]);
        calculator.calculate(doubleA, operation, doubleB);

        firstScan = false;
        return getValue();
    }


    private String secondScan(String line) {
        final String[] split = line.split(" ");

        if (split.length == 1) {
            if (split[0].equals(QUIT)) {
                System.exit(0);
            } else if (split[0].equals(RESTART)) {
                firstScan = true;
                calculator.reset();
                return "Restart done";
            }
        }

        if (split.length != 2) {
            throw new IllegalArgumentException("Wrong input format! Please write so: @_b");
        }

        final Operation operation = Operation.parse(split[0]);
        final double doubleA = parseDouble(split[1]);
        calculator.calculate(operation, doubleA);

        return getValue();
    }
}
