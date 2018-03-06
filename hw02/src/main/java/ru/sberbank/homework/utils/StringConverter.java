package ru.sberbank.homework.utils;

import ru.sberbank.homework.Polushin.Operation;

import java.util.*;
import java.util.regex.Pattern;

public class StringConverter {

    /*
    Метод проверяет строку на валидность и выбрасывает исключение, если строка не корректна.
    Затем конвертирует ее в постфиксную форму записи выражения и возращает результат в виде стэка.
     */
    public static LinkedList convertToPostfix(String inputExpression) throws UserInputException {
        String isCorrect = new StringAnalyzer().analyze(inputExpression.toUpperCase());
        if (isCorrect != "good") {
            throw new UserInputException(isCorrect);
        }

        String[] infix = inputExpression   //Парсим строку на операнды и операторы
                .toUpperCase()
                .replaceAll("[(]", "( ")
                .replaceAll("[)]", " )")
                .replaceAll("[_]", "")
                .split("[ ]+");

        Stack<Operation> temp = new Stack<>();
        LinkedList postfixExpression = new LinkedList();
        Operation operation;

        /*
        Преобразуем строковые значения в числа и операторы и помещаем их в постфиксный стек.
         */
        for (int i = 0; i < infix.length; i++) {
            if (infix[i].length() > 1) {

                Double value = valueOf(infix[i]);
                postfixExpression.push(value);

            } else {
                //преобразование операций и помещение их в стэк
                switch (infix[i].charAt(0)) {
                    case '+':
                        operation = Operation.SUM;
                        while (!temp.empty() && temp.peek() != Operation.PARENTHESIS_LEFT) {
                            postfixExpression.push(temp.pop());
                        }
                        temp.push(operation);
                        break;
                    case '-':
                        operation = Operation.SUBTRACT;
                        while (!temp.empty() && temp.peek() != Operation.PARENTHESIS_LEFT) {
                            postfixExpression.push(temp.pop());
                        }
                        temp.push(operation);
                        break;
                    case '/':
                        operation = Operation.DIVISION;
                        while (!temp.empty()
                                && (temp.peek() == Operation.MULTIPLY
                                || temp.peek() == Operation.DIVISION
                                || temp.peek() == Operation.POWER)) {
                            postfixExpression.push(temp.pop());
                        }
                        temp.push(operation);
                        break;
                    case '*':
                        operation = Operation.MULTIPLY;
                        while (!temp.empty()
                                && (temp.peek() == Operation.MULTIPLY
                                || temp.peek() == Operation.DIVISION
                                || temp.peek() == Operation.POWER)) {

                            postfixExpression.push(temp.pop());
                        }
                        temp.push(operation);
                        break;
                    case '^':
                        operation = Operation.POWER;
                        while (!temp.empty() && temp.peek() == Operation.POWER) {
                            postfixExpression.push(temp.pop());
                        }
                        temp.push(operation);
                        break;
                    case '(':
                        operation = Operation.PARENTHESIS_LEFT;
                        temp.push(operation);
                        break;
                    case ')':
                        operation = Operation.PARENTHESIS_RIGHT;
                        while (!temp.empty() && temp.peek() != Operation.PARENTHESIS_LEFT) {
                            postfixExpression.push(temp.pop());
                        }
                        //TODO Переделать правильно бросание ошибки.
                        if (temp.empty()) {
                            throw new UserInputException("Неправильно расставленны скобки )");
                        } else {
                            temp.pop();
                        }
                        break;
                    //Может понадобится, если литерал из одного символа, например "7". и он всегда будет инт.
                    default:
                        postfixExpression.push(Double.parseDouble(infix[i]));
                }
            }
        }
        while (!temp.empty()) {
            if (temp.peek() == Operation.PARENTHESIS_LEFT) {
                throw new UserInputException("Неправильно расставленны скобки (");
            }
            postfixExpression.push(temp.pop());
        }
        return postfixExpression;
    }

    /*
    Преобразует строку в число и возращает его как double
     */
    //TODO подумать и возможно переделать метод, чтобы возвращал значение + тип
    protected static Double valueOf(String input) throws UserInputException {
        String value = input;
        if (Pattern.compile("^" + RegularExpr.INTEGER_TYPE.getRegExp() + "$").matcher(input).find()) {
            int radix = 10;
            int sign = 0;

                    /*
                    Разбираем строковой литерал:
                    1) записываем его знак в переменную и отбрасываем знак
                    2) определяем систему счисления литерала, и отбрасываем символы ее обозначающую.
                    3) преобразуем литерал в число, в зависимости от типа лонг,инт,дабл.
                    4) записываем число в стек, учитывая знак.
                     */
            if (input.charAt(0) == '-') {
                sign = 1;
                input = input.substring(1);
            }
            if (input.charAt(0) == '0') {
                input = input.substring(1);
                if (Pattern.compile("^[0-7]+[L]?$").matcher(input).find()) {
                    radix = 8;
                }
            }
            if (input.charAt(0) == 'B') {
                radix = 2;
                input = input.substring(1);
            }
            if (input.charAt(0) == 'X') {
                radix = 16;
                input = input.substring(1);
            }
            if (input.charAt(input.length() - 1) == 'L') {
                input = input.substring(0, input.length() - 1);
                long var;
                try {
                    var = Long.parseUnsignedLong(input, radix);
                } catch (NumberFormatException e) {
                    throw new UserInputException("Вы ввели недопустимое значение для данного типа Long: " + value);
                }
                return Double.valueOf(sign == 1 ? 0 - var : var);
            } else {
                long var;
                try {
                    var = Integer.parseUnsignedInt(input, radix);
                } catch (NumberFormatException e) {
                    throw new UserInputException("Вы ввели недопустимое значение для данного типа Integer: " + value);
                }
                return Double.valueOf((sign == 1 ? 0 - var : var));
            }
        } else {
            double var;
            try {
                var = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                throw new UserInputException("Вы ввели недопустимое значение для данного типа Double: " + value);
            }
            return var;
        }

    }
}