package ru.sberbank.homework.kudryavykh;

import java.util.Scanner;

public class MessageTerminal implements MessageTermnalInterface {

    @Override
    public void incorrectPinValue() {
        System.out.println("Некорректный Pin код. Попробуйте еще раз!");
    }

    @Override
    public void incorrectBalanceValue() {
        System.out.println("Ваш баланс не может быть отрицательным!");
    }

    public void incorrectInputValue() {
        System.out.println("Сумма должна быть кратна 100.");
    }

    @Override
    public void invalidCardNumber() {
        System.out.println("Карта повреждена или не обслуживается банком.");
    }

    @Override
    public void accountLocked() {
        System.out.println("Слишком большое количество неверных попыток ввода Pin кода. " +
                "Аккаунт заблокирован");
    }

    @Override
    public int inputSum() {
        System.out.println("Введите сумму");
        Scanner in = new Scanner(System.in);
        String userAnswers = in.nextLine();
        return Integer.parseInt(userAnswers);
    }

    @Override
    public String inputPin() {
        System.out.println("Введите Pin: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    @Override
    public void balanceCash(double balance) {
        System.out.println("Ваш баланс " + balance);
    }

    public static void menuMessage() {
        System.out.println("1 -> Посмотреть баланс");
        System.out.println("2 -> Снять со счета");
        System.out.println("3 -> Положить на счет");
        System.out.println("4 -> Выход");
    }

    private static void consoleOutput(String message) {
        System.out.println(message);
    }
}
//Методы inputSum и inputPin очень схожи, но я решил их не обьеденять, т.к. при их вызове из...
//...терминала придется передавать начальную фразу (введите пин), что противоречит логике терминала