package ru.sberbank.homework.maruev.operations;

/**
 * Created by Иван on 15.02.2018.
 */

import ru.sberbank.homework.maruev.ConsoleParser;

import static ru.sberbank.homework.maruev.HardCalculator.result;

public class SubstractOperator implements Operator {
    @Override
    public double getResult() {
        ConsoleParser.stackOperator.pop();
        result = ConsoleParser.dequeSymbols.pollLast() - ConsoleParser.dequeSymbols.pollFirst();
        ConsoleParser.dequeSymbols.addFirst(result);
        return result;
    }
}
