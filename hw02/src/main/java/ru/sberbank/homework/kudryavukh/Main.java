package java.ru.sberbank.homework.kudryavukh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


//Доп задача - калькулятор работает с длинными выражениями вида a + b * c / d - e. Без скобок



//Результат помещается вычисления помещается обратно в список. Список типа String, т.к. хранит и числа и знаки

public class Main {

    public static void main(String[] args) {


        String previousResult = "";
        System.out.print("Введите выражение: ");
        Operation calc = new Calculate();;
        for (;;) {
            if(previousResult.contains("Ошибка")) {
                previousResult = "";
            }
            Scanner in = new Scanner(System.in);
            String inputText = in.nextLine();
            previousResult = calc.calculate(inputText);
            System.out.print(" = " + previousResult + " ");
        }
    }
}



