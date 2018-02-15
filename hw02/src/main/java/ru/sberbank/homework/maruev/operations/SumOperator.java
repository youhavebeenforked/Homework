package ru.sberbank.homework.maruev.operations;

/**
 * Created by Иван on 15.02.2018.
 */

import ru.sberbank.homework.maruev.ConsoleParser;

import static ru.sberbank.homework.maruev.HardCalculator.result;

public class SumOperator implements Operator {
    @Override
    public double getResult() {
        ConsoleParser.stackOperator.pop();
        result = ConsoleParser.dequeSymbols.pop() + ConsoleParser.dequeSymbols.pop();
        ConsoleParser.dequeSymbols.addFirst(result);
        return result;
    }
}
