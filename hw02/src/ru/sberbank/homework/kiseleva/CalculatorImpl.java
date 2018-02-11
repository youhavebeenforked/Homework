package ru.sberbank.homework.kiseleva;

import java.text.DecimalFormat;

/**
 * Created by Ekaterina Kiseleva on 08.02.2018.
 */
public class CalculatorImpl implements Calculator {

    private static CommonCalculator commonCalculator;
    private static DoubleCalculator doubleCalculator = new DoubleCalculator();
    private static LongCalculator longCalculator = new LongCalculator();
    DecimalFormat df = new DecimalFormat("#.##");
    private static Number result;

    @Override
    public String calculate(String userInput) {
        try {
            parse(userInput);
            return df.format(result);
        } catch (Exception e) {
            return "Error > wrong expression";
        }
    }

    public void parse(String userInput) {
        String[] parsedArr = userInput.split(" ");

        if (parsedArr.length == 3) { //первый ввод
            computation(parsedArr[0], parsedArr[2], parsedArr[1]);
        } else if (parsedArr.length == 2) { //последующий ввод
            computation(String.valueOf(result), parsedArr[1], parsedArr[0]);
        }
    }

    public void computation(String numOne, String numTwo, String operation) {

        try {
            if (numOne.contains(".") || numTwo.contains(".")) {
                commonCalculator = doubleCalculator;
            } else {
                commonCalculator = longCalculator;
            }
            switch (operation) {
                case ("+"):
                    result = commonCalculator.addition(numOne, numTwo);
                    break;
                case ("-"):
                    result = commonCalculator.subtraction(numOne, numTwo);
                    break;
                case ("*"):
                    result = commonCalculator.multiplication(numOne, numTwo);
                    break;
                case ("/"):
                    result = commonCalculator.division(numOne, numTwo);
                    break;
            }
        } catch (IllegalArgumentException e) {
            String[] arr = e.getMessage().split(" ");
            String wrongArgument = arr[arr.length - 1];
            System.out.println("error > " + wrongArgument.replace("\"", ""));
        }
    }
}

