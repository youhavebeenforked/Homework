package ru.sberbank.homework.maruev.operations;

import ru.sberbank.homework.maruev.ConsoleParser;

import java.text.DecimalFormat;

import static ru.sberbank.homework.maruev.HardCalculator.result;

/**
 * Created by Иван on 15.02.2018.
 */
public class DivisionOperator implements Operator {
    @Override
    public String getResult() {
        ConsoleParser.stackOperator.pop();
        if (ConsoleParser.dequeSymbols.peekLast() != 0) {
            result = ConsoleParser.dequeSymbols.pollFirst() / ConsoleParser.dequeSymbols.pollLast();
            ConsoleParser.dequeSymbols.addFirst(result);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String validResult = decimalFormat.format(result);
            validResult = validResult.replace(',', '.'); // Исправлено
            return validResult;
        } else {
            throw new NullPointerException();
        }

    }
}
