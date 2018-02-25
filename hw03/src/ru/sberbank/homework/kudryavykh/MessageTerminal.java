package ru.sberbank.homework.kudryavykh;

import java.util.Scanner;

public class MessageTerminal implements MessageTermnalInterface {

    public void incorrectPinValue() {
        System.out.println("Некорректный Pin код. Попробуйте еще раз!");
    }

    public void incorrectBalanceValue() {
        System.out.println("Ваш баланс не может быть отрицательным!");
    }

    public void incorrectInputValue() {
        System.out.println("Сумма должна быть кратна 100.");
    }

    public void invalidCardNumber() {
        System.out.println("Карта повреждена или не обслуживается банком.");
    }

    public void accountLocked() {
        System.out.println("Слишком большое количество неверных попыток ввода Pin кода. " +
                "Аккаунт заблокирован");
    }

    public int inputSum() {
        System.out.println("Введите сумму");
        Scanner in = new Scanner(System.in);
        String userAnswers = in.nextLine();
        return Integer.parseInt(userAnswers);
    }

    public String inputPin() {
        System.out.println("Введите Pin: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void getBalanceCash(double balance) {
        System.out.println("Ваш баланс " + balance);
    }

    public static void menuMessage() {
        System.out.println("1 -> Посмотреть баланс");
        System.out.println("2 -> Снять со счета");
        System.out.println("3 -> Положить на счет");
        System.out.println("4 -> Выход");
    }
}
//Методы inputSum и inputPin очень схожи, но я решил их не обьеденять, т.к. при их вызове из...
//...терминала придется передавать начальную фразу (введите пин), что противоречит логике терминала