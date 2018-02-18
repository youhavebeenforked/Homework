package ru.sberbank.homework.kashin.main.model.numbers;

import ru.sberbank.homework.kashin.main.exception.WrongExpression;
import ru.sberbank.homework.kashin.main.model.Number;

import static ru.sberbank.homework.kashin.main.calculator.ExpressionCalculator.*;
import static ru.sberbank.homework.kashin.main.util.FactoryExpCalc.expressionCalculatorClear;

public class HexNumber extends Number {
    @Override
    public String checkAndParse(String number, int item) {
        try {
            if (number.startsWith("-")) {
                return  "-" + parse(number,3, 16);
            } else if (number.startsWith("+")) {
                return parse(number,3, 16);
            } else {
                return parse(number,2, 16);
            }
        } catch (Exception e) {
            expressionCalculatorClear();
            throw new WrongExpression(String.format("error > %s", ORIGINAL_LITERALS.get(item)));
        }
    }
}
