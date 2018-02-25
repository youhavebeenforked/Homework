package ru.sberbank.homework.kudryavykh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        
        String previousResult = "";
        System.out.print("Введите выражение: ");
        for (;;) {
            if(previousResult.contains("Ошибка")) {
                previousResult = "";
            }
            Scanner in = new Scanner(System.in);
            String inputText = in.nextLine();

            Calculator calc = new Calculate(previousResult);
            previousResult = calc.calculate(inputText);
            System.out.print(" = " + previousResult + " ");
        }
    }
}

class Calculate implements Calculator {

    private boolean errorFlag = false;
    private String previousResult;

    Calculate(String previousResult) {
        if(previousResult != "") {
            this.previousResult = previousResult + " ";
        }
        else {
            this.previousResult = previousResult;
        }
    }

    public String calculate(String inputText) {

        inputText = previousResult + inputText;
        ArrayList<String> sArray;
        sArray = new ArrayList<>(Arrays.asList(inputText.split(" ")));
        for (int i = 0; i < sArray.size();) {
            sArray.set(i, Parse.validationEndParser(sArray.get(i)));
            if(sArray.get(i) == null) {
                errorFlag = true;
                sArray.clear();
                break;
            }
            else {
                i++;
            }
        }
        if(errorFlag == false) {
            //Обработка
            String result = computation(sArray, "*", "/");
            if(result.contains("Ошибка")) {
                return result;
            }
            result = computation(sArray, "+", "-");
            if(result.contains("Ошибка")) {
                return result;
            }
        }
        else {
            return "Ошибка ввода! ";
        }
        return  Parse.output(sArray.get(0));
    }
/**
    Входные данные - ArrayList, символы операторы + - или * /
    Метод проходит по всему массиву, ищет символ-оператор и при удачном поиске проверяет боковые
    элементы на наличие "=-/*" . Тем самым исключается сценарий + + 8 + 1. После получения значения
    enum происходит схлопывание трех ячеек в одну с результатом операции. Метод получился тяжелым
    но у меня, к сожалению, нет идей сделать его более читабельным, а сроки я и так затянул.

 */

    private String computation(ArrayList<String> sArray, String firstSymbol, String secondSymbol) {
        String result;
        for (int i = 1; i < sArray.size(); i++) {
            if (sArray.get(i).equals(firstSymbol) || sArray.get(i).equals(secondSymbol)) {
                if (!(sArray.get(i - 1).equals("+") || sArray.get(i - 1).equals("-") || sArray.get(i + 1).equals("+")
                        || sArray.get(i + 1).equals("-")) || (sArray.get(i - 1).equals("*") || sArray.get(i - 1).equals("/")
                        || sArray.get(i + 1).equals("*") || sArray.get(i + 1).equals("/"))) {
                    Operation operatEnum = Operation.setValue(sArray.get(i));
                    if (operatEnum != null) {
                        result = Operation.getResult(operatEnum, Double.parseDouble(sArray.get(i - 1)),
                                Double.parseDouble(sArray.get(i + 1)));
                        if(result != null) {
                            sArray.set(i, result);
                            sArray.remove(i - 1);
                            i--;
                            sArray.remove(i + 1);
                        }
                        else {
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
}