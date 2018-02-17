package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.model.Number;
import ru.sberbank.homework.kashin.main.model.numbers.BinaryNumber;
import ru.sberbank.homework.kashin.main.model.numbers.DecNumber;
import ru.sberbank.homework.kashin.main.model.numbers.HexNumber;
import ru.sberbank.homework.kashin.main.model.numbers.OctalNumber;

import static ru.sberbank.homework.kashin.main.util.CalculateHelper.*;

public class FactoryNumber {

    public static Number getNumber(String number) {
        if (checkWithRegExp(number, BINARY_NUMBER)) {
            return new BinaryNumber();
        } else if (checkWithRegExp(number, OCTAL_NUMBER)) {
            return new OctalNumber();
        } else if (checkWithRegExp(number, HEX_NUMBER)) {
            return new HexNumber();
        } else {
            return new DecNumber();
        }
    }
}
