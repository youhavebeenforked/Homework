package ru.sberbank.homework.kiseleva;

import java.text.DecimalFormat;

/**
 * Created by Ekaterina Kiseleva on 08.02.2018.
 */
public class CalculatorImpl implements Calculator {

    static final CommonCalculator commonCalculator = new CommonCalculator();
    static final Parser parser = new Parser();
    DecimalFormat df = new DecimalFormat("#.##");
    private static Number result;

    @Override
    public String calculate(String userInput) {
        parser.parse(userInput);
        return df.format(result);
    }

    public void firstInput(String[] parsedArr) {
        if (parsedArr[0].contains(".")) {
            switch (parsedArr[1]) {
                case ("+"):
                    result = commonCalculator.addition(Double.parseDouble(parsedArr[0]), Double.parseDouble(parsedArr[2]));
                    break;
                case ("-"):
                    result = commonCalculator.subtraction(Double.parseDouble(parsedArr[0]), Double.parseDouble(parsedArr[2]));
                    break;
                case ("*"):
                    result = commonCalculator.multiplication(Double.parseDouble(parsedArr[0]), Double.parseDouble(parsedArr[2]));
                    break;
                case ("/"):
                    result = commonCalculator.division(Double.parseDouble(parsedArr[0]), Double.parseDouble(parsedArr[2]));
                    break;
            }
        } else {
            switch (parsedArr[1]) {
                case ("+"):
                    result = commonCalculator.addition(Long.parseLong(parsedArr[0]), Long.parseLong(parsedArr[2]));
                    break;
                case ("-"):
                    result = commonCalculator.subtraction(Long.parseLong(parsedArr[0]), Long.parseLong(parsedArr[2]));
                    break;
                case ("*"):
                    result = commonCalculator.multiplication(Long.parseLong(parsedArr[0]), Long.parseLong(parsedArr[2]));
                    break;
                case ("/"):
                    result = commonCalculator.division(Long.parseLong(parsedArr[0]), Long.parseLong(parsedArr[2]));
                    break;
            }
        }
    }

    public void continueInput(String[] parsedArr) {
        if (parsedArr[1].contains(".")) {
            switch (parsedArr[0]) {
                case ("+"):
                    result = commonCalculator.addition((double) result, Double.parseDouble(parsedArr[1]));
                    break;
                case ("-"):
                    result = commonCalculator.subtraction((double) result, Double.parseDouble(parsedArr[1]));
                    break;
                case ("*"):
                    result = commonCalculator.multiplication((double) result, Double.parseDouble(parsedArr[1]));
                    break;
                case ("/"):
                    result = commonCalculator.division((double) result, Double.parseDouble(parsedArr[1]));
                    break;
            }
        } else {
            switch (parsedArr[0]) {
                case ("+"):
                    result = commonCalculator.addition((long) result, Long.parseLong(parsedArr[1]));
                    break;
                case ("-"):
                    result = commonCalculator.subtraction((long) result, Long.parseLong(parsedArr[1]));
                    break;
                case ("*"):
                    result = commonCalculator.multiplication((long) result, Long.parseLong(parsedArr[1]));
                    break;
                case ("/"):
                    result = commonCalculator.division((long) result, Long.parseLong(parsedArr[1]));
                    break;
            }
        }
    }
}

