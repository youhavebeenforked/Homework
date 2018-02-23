package ru.sberbank.homework.maruev.operations;

/**
 * Created by Иван on 15.02.2018.
 */

import ru.sberbank.homework.maruev.ConsoleParser;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static ru.sberbank.homework.maruev.HardCalculator.result;

public class SumOperator implements Operator {

    public String getResult() {
        ConsoleParser.stackOperator.pop();

        result = ConsoleParser.dequeSymbols.pop() + ConsoleParser.dequeSymbols.pop();
        ConsoleParser.dequeSymbols.addFirst(result);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String validResult = decimalFormat.format(result);
        validResult = validResult.replace(',', '.'); // Исправлено
        return validResult;
    }
}
