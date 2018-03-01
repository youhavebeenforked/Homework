package ru.sberbank.homework.kalugin;

/**
 * Проверка переданных в калькулятор данных.
 */
class InputValidator {
    private static final String OPERATION_REGEX = "^[*/+-]{1}$";
    private static final String DECIMAL_OR_INT_REGEX = "^-?\\d*\\.{0,1}\\d+$";

    static String validate(String input, String lastResult) {
        // если есть результат прошлой операции
        if (lastResult != null)
            // если первый элемент выражения это матем. оператор
            if (input.substring(0,1).matches(OPERATION_REGEX))
                // добавим прошлый результат в выражение
                input = lastResult + " " + input;

        StringBuilder formatedOutput = new StringBuilder();
        String[] tokens = input.split(" ");

        // кол-во элементов выражения должно быть >= 3 (вида: "цифра оператор цифра") и нечетным
        if ((tokens.length < 3) || (tokens.length % 2 == 0) ) {
            // если это не так, значит была ошибка в выражении или пробелах
            return "error > wrong expression";
        }
        // флаг показывает что следующий элемент должен быть матем. оператор
        Boolean nextIsOperatorFlag = false;
        // проверяем в цикле элементы переданного в функцию матем.выражения
        for (String s: tokens) {
            if (nextIsOperatorFlag) {
                if (!checkOperator(s, formatedOutput)) {
                    return "error > wrong expression";
                }
                nextIsOperatorFlag = false;
            }
            else {
                if (!checkNumber(s, formatedOutput)) {
                    return "error > " + s;
                }
                nextIsOperatorFlag = true;
            }
        }
        // если закончили цикл проверки оператором, а не числом, ошибка в выражении
        if (!nextIsOperatorFlag) {
            return "error > wrong expression";
        }

        return formatedOutput.toString();
    }

    private static boolean checkNumber(String s, StringBuilder formatedOutput) {
        s = s.replace("_","");
        if (s.endsWith("l") || s.endsWith("L")) {
            s = s.substring(0,s.length()-2);
        }
        try {
            // если число в hexadecimal binary или octa записи
            if (s.startsWith("0x") || s.startsWith("0b") || s.startsWith("0")) {
                formatedOutput.append(Long.decode(s));
            }
            // если число десятичное/целое
            else if (s.matches(DECIMAL_OR_INT_REGEX)) {
                formatedOutput.append(s);
            }
            else {
                return false;
            }
        }
        // ошибка при парсинге числа
        catch (NumberFormatException e) {
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private static boolean checkOperator(String s, StringBuilder formatedOutput) {
        if (s.matches(OPERATION_REGEX)) {
            formatedOutput.append(s);
            return true;
        }
        else {
            return false;
        }
    }
}