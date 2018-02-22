package ru.sberbank.homework.andreev.first;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TerminalViewer {

    private static Terminal term;
    private static String cardNumber;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        prepareTerminal();
        int userChoose;
        System.out.println("Welcome in terminal");
        while (true) {
            authorisation();
            System.out.println("your account is " + cardNumber);
            userChoose = 1;
            while (userChoose != 4) {
                userChoose = getMenuResult();
                switch (userChoose) {
                    case 1:
                        System.out.println("your balance is " + term.checkBalance());
                        break;
                    case 2:
                        depositMoney();
                        break;
                    case 3:
                        withdrawMoney();
                        break;
                    case 4:
                        term.closeSession();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Wrong menu choice");
                        break;
                }
            }
        }
    }

    private static void withdrawMoney() {
        System.out.println("Enter amount you want withdraw");
        BigDecimal amount = readBigDecimal();
        try {
            term.withdraw(amount);
        } catch (TerminalException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void depositMoney() {
        System.out.println("Enter amount you want deposit");
        BigDecimal amount = readBigDecimal();
        try {
            term.deposit(amount);
        } catch (TerminalException e) {
            System.out.println(e.getMessage());
        }
    }

    private static BigDecimal readBigDecimal() {
        boolean reading = true;
        BigDecimal result = null;
        while (reading) {
            try {
                result = new BigDecimal(scanner.nextLine());
                reading = false;
            } catch (NumberFormatException e) {
                System.out.println("wrong amount, try again");
            }
        }
        return result;
    }

    private static void authorisation() {
        String pin;
        boolean readingNumberAndPin = true;
        while (readingNumberAndPin) {
            System.out.println("Please input card number");
            cardNumber = scanner.nextLine();
            System.out.println("Please input pin");
            pin = scanner.nextLine();
            try {
                boolean result = term.openSession(cardNumber, pin);
                if (result) {
                    System.out.println("welcome");
                    readingNumberAndPin = false;
                } else {
                    System.out.println("wrong pin");
                }
            } catch (TerminalException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getMenuResult() {
        boolean isRead = true;
        Integer result = null;
        while (isRead) {
            System.out.println("Choose action: \n" +
                    "1 - show balance \n" +
                    "2 - deposit money \n" +
                    "3 - withdraw money \n" +
                    "4 - close session \n" +
                    "5 - exit\n");
            String read = scanner.nextLine();
            try {
                result = Integer.parseInt(read);
                isRead = false;
            } catch (NumberFormatException e) {
                System.out.println("Wrong action, try again");
            }
        }
        return result;
    }

    private static void prepareTerminal() {
        Map<String, String> pins = new HashMap<>();
        pins.put("753951456852", "0258");
        pins.put("123456789101112", "4562");
        Map<String, BigDecimal> accounts = new HashMap<>();
        accounts.put("753951456852", new BigDecimal("1000").setScale(2, BigDecimal.ROUND_HALF_UP));
        accounts.put("123456789101112", new BigDecimal("20").setScale(2, BigDecimal.ROUND_HALF_UP));
        term = new TerminalImpl(new TerminalServer(accounts), new PinValidator(pins));
    }
}
