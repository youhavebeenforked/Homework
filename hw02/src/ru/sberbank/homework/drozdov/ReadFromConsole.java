package ru.sberbank.homework.drozdov;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ReadFromConsole {
    private Scanner scanner = new Scanner(System.in);
    private ExpressionParser parser = new ExpressionParser();

    public void getLine() {
        System.out.println("Введите выражение или 'quit' для выхода");
        String inputExpression = scanner.nextLine();
        switch (inputExpression) {
            case "":
                System.out.println("Вы ввели пустую строку");
                break;
            case "quit":
                System.exit(0);
            default:
                String answer = parser.calculate(inputExpression);
                if (answer != null) {
                    String pattern = "#.##";
                    DecimalFormat decimalFormat = new DecimalFormat(pattern);
                    decimalFormat.setRoundingMode(RoundingMode.CEILING);
                    answer = String.valueOf(decimalFormat.format(Double.parseDouble(answer)));
                    System.out.printf("Ответ равен %s\n", answer);
                }
                break;
        }
    }
}
