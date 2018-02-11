package ru.sberbank.homework.solovyov;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleCalculator calculator = new SimpleCalculator();
        String calcResult = "";

        while (calcResult.compareTo("quit") != 0) {
            calcResult = calculator.calculate(scanner.nextLine());
            System.out.println(calcResult);
        }

//        switch (userInput.charAt(0)) {
//            case 'q':
//                return "quit";
////            case 'n':
////                isNextStep = false;
////                resultString = "";
////                return "new";
//            default:
//                if (isNextStep) {
//                    resultString += " " + userInput;
//                }
//        }
//
//        if (isNextStep) {
//            parseAndCalc(resultString);
//        } else {
//            parseAndCalc(userInput);
//        }
//
//        if (resultString != null) {
//            System.out.println("= " + resultString);
//            System.out.println("Type:\n- the next operation and the operand (format: @ c) to continue calculation\n" +
//                    "- n(ew) for new calculation example\n- q(uit) for exit.");
//            isNextStep = true;
//            return resultString;
//        } else {
//            return "error";
//        }
    }

}

