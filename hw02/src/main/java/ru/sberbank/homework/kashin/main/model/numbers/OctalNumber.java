package ru.sberbank.homework.kashin.main.model.numbers;

import ru.sberbank.homework.kashin.main.exception.WrongExpression;
import ru.sberbank.homework.kashin.main.model.Number;

import static ru.sberbank.homework.kashin.main.calculator.ExpressionCalculator.ORIGINAL_LITERALS;
import static ru.sberbank.homework.kashin.main.util.FactoryExpCalc.expressionCalculatorClear;

public class OctalNumber extends Number {
    @Override
    public String checkAndParse(String number, int item) {
        try {
            if (number.startsWith("-")) {
                return  "-" + parse(number,1, 8);
            } else {
                return parse(number,1, 8);
            }
        } catch (Exception e) {
            expressionCalculatorClear();
            throw new WrongExpression(String.format("error > %s", ORIGINAL_LITERALS.get(item)));
        }
    }
}
