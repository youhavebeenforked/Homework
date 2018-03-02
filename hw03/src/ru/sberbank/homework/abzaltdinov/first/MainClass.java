package ru.sberbank.homework.abzaltdinov.first;

import ru.sberbank.homework.abzaltdinov.first.data.Terminal;
import ru.sberbank.homework.abzaltdinov.first.data.TerminalImpl;
import ru.sberbank.homework.abzaltdinov.first.output.ConsoleUserOutput;
import ru.sberbank.homework.abzaltdinov.first.output.UserOutput;

import java.util.Scanner;

public class MainClass {
    private static final UserOutput out = new ConsoleUserOutput();
    private static final Terminal terminal = new TerminalImpl();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int accountNumber;
        do {
            out.println("Enter the account number: ");
            accountNumber = inputInt();
        } while (!terminal.startSession(accountNumber));

        int pinCode;
        do {
            out.println("Enter pin: ");
            pinCode = inputInt();
        } while (!terminal.enterPin(pinCode));

        boolean isSessionClosed = false;
        while (!isSessionClosed) {
            printMenu();
            switch (in.next()) {
                case "1":
                    terminal.getBalance();
                    break;
                case "2":
                    out.println("Введите сумму");
                    int amountToWithdraw = inputInt();
                    terminal.withdrawCash(amountToWithdraw);
                    break;
                case "3":
                    out.println("Введите сумму");
                    int amountToDeposit = inputInt();
                    terminal.depositCash(amountToDeposit);
                    break;
                case "4":
                    isSessionClosed = terminal.closeSession();
                    break;
                default:
                    out.println("Кажется, вы ошиблись. Введите число от 1 до 4.");
            }
        }

        in.close();
    }

    private static int inputInt() {
        while (!in.hasNextInt()) {
            in.next();
            out.println("Кажется, вы ошиблись. Введите число.");
        }
        return in.nextInt();
    }

    private static void printMenu() {
        System.out.println(
                "1 - Проверить баланс\n"
                + "2 - Снять наличные\n"
                + "3 - Внести наличные\n"
                + "4 - Закончить"
        );
    }
}
