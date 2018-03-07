package java.ru.sberbank.homework.kudryavukh;

import java.util.ArrayList;
import java.util.Arrays;
import java.ru.sberbank.homework.common.*;

public class Calculate implements ru.sberbank.homework.common.Calculator {

    private boolean errorFlag = false;
    private String previousResult = "";
    private Parse parse = new Parse();

    //public static

    public String calculate(String inputText) {

        errorFlag = false;
        String value = "";
        inputText = previousResult + inputText;
        //System.out.println("Выражение" + inputText);
        ArrayList<String> sArray;
        sArray = new ArrayList<>(Arrays.asList(inputText.split(" ")));
        //System.out.println("Количество ячеек в списке " + sArray.size());
        for (int i = 0; i < sArray.size(); ) {
            value = Parse.validationEndParser(sArray.get(i));
            if (value == null) {
                errorFlag = true;
                value = sArray.get(i);
                sArray.clear();
                break;
            } else {
                sArray.set(i, value);
                i++;
            }
        }
        if (errorFlag == false) {
            //Обработка
            String result = computation(sArray, "*", "/");
            if (result.contains("Ошибка")) {
                return result;
            }
            result = computation(sArray, "+", "-");
            if (result.contains("Ошибка")) {
                return result;
            }
        } else {
            return "error > " + value;
        }
        previousResult = parse.output(sArray.get(0)) + " ";
        return parse.output(sArray.get(0));
    }

    /**
     * Входные данные - ArrayList, символы операторы + - или * /
     * Метод проходит по всему массиву, ищет символ-оператор и при удачном поиске проверяет боковые
     * элементы на наличие "=-/*" . Тем самым исключается сценарий + + 8 + 1. После получения значения
     * enum происходит схлопывание трех ячеек в одну с результатом операции. Метод получился тяжелым
     * но у меня, к сожалению, нет идей сделать его более читабельным, а сроки я и так затянул.
     */

    private String computation(ArrayList<String> sArray, String firstSymbol, String secondSymbol) {
        String result;
        for (int i = 1; i < sArray.size(); i++) {
            if (sArray.get(i).equals(firstSymbol) || sArray.get(i).equals(secondSymbol)) {
                if (neighborElementNotContainOperator(sArray, i)) {
                    OperationEnum operatEnum = OperationEnum.setValue(sArray.get(i).charAt(0));
                    if (operatEnum != null) {
                        result = OperationEnum.getResult(operatEnum, Double.parseDouble(sArray.get(i - 1)),
                                Double.parseDouble(sArray.get(i + 1)));
                        if (result != null) {
                            sArray.set(i, result);
                            sArray.remove(i - 1);
                            i--;
                            sArray.remove(i + 1);
                        } else {
                            return "Ошибка ввода! Деление на ноль или я в коде накосячил";
                        }
                    } else {
                        throw new NullPointerException("Обьект Enum хранит null");
                    }
                } else {
                    return "Ошибка ввода! Два знака подрят.";
                }
            }
        }
        return "Общая ошибка";
    }

    private boolean neighborElementNotContainOperator(ArrayList<String> sArray, int index) {

        if (sArray.get(index - 1).equals("+")) {
            return false;
        } else if (sArray.get(index - 1).equals("-")) {
            return false;
        } else if (sArray.get(index - 1).equals("*")) {
            return false;
        } else if (sArray.get(index - 1).equals("/")) {
            return false;
        } else if (sArray.get(index + 1).equals("+")) {
            return false;
        } else if (sArray.get(index + 1).equals("-")) {
            return false;
        } else if (sArray.get(index + 1).equals("*")) {
            return false;
        } else if (sArray.get(index + 1).equals("/")) {
            return false;
        } else {
            return true;
        }
    }
}
