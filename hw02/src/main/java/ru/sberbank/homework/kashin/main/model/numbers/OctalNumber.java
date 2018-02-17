package ru.sberbank.homework.kashin.main.model.numbers;

import ru.sberbank.homework.kashin.main.exception.WrongExpression;
import ru.sberbank.homework.kashin.main.model.Number;

import static ru.sberbank.homework.kashin.main.util.CalculateHelper.getOriginalLiterals;
import static ru.sberbank.homework.kashin.main.util.CalculateHelper.setPreResult;

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
            setPreResult(null);
            throw new WrongExpression(String.format("error > %s", getOriginalLiterals().get(item)));
        }
    }
}
