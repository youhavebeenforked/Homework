package ru.sberbank.homework.kashin.main.model.numbers;

import ru.sberbank.homework.kashin.main.exception.WrongExpression;
import ru.sberbank.homework.kashin.main.model.Number;

import static ru.sberbank.homework.kashin.main.util.CalculateHelper.getOriginalLiterals;
import static ru.sberbank.homework.kashin.main.util.CalculateHelper.setPreResult;

public class BinaryNumber extends Number {
    @Override
    public String checkAndParse(String number, int item) {
        try {
            if (number.startsWith("-")) {
                return  "-" + BinaryToDecimal(number);
            } else {
                return BinaryToDecimal(number);
            }
        } catch (Exception e) {
            setPreResult(null);
            throw new WrongExpression(String.format("error > %s", getOriginalLiterals().get(item)));
        }
    }

    private static String BinaryToDecimal(String base2) {
        char[] chars = base2.toCharArray();
        Long result = 0L;
        int mult = 1;
        for (int i = base2.length() - 1; i >= 0; i--) {
            if (chars[i] == '1') {
                result += mult;
            }
            mult *= 2;
        }
        return result.toString().replaceAll("L", "");
    }
}
