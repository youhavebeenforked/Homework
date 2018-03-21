package ru.sberbank.homework.bobrov.terminal.term;


import ru.sberbank.homework.bobrov.terminal.exception.IdentificationException;
import ru.sberbank.homework.bobrov.terminal.exception.NotEnoughMoney;
import ru.sberbank.homework.bobrov.terminal.exception.WrongSumException;
import ru.sberbank.homework.bobrov.terminal.msg.ShowMessage;
import ru.sberbank.homework.bobrov.terminal.msg.ShowMessageImpl;
import ru.sberbank.homework.bobrov.terminal.pin.PinValidator;
import ru.sberbank.homework.bobrov.terminal.srv.TerminalServer;
import ru.sberbank.homework.bobrov.terminal.srv.TerminalServerImpl;
import ru.sberbank.homework.bobrov.terminal.exception.CheckPinException;

import java.util.Scanner;

/**
 * Task terminal.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */


public class TerminalImpl implements Terminal {
    private final TerminalServer server = new TerminalServerImpl();
    private final PinValidator pinValidator = new PinValidator();
    private final ShowMessage showMsg = new ShowMessageImpl();
    private boolean isValidPin = false;
    private long cardNumber;

    @Override
    public void startTerminal(long cardNumber) {
        this.cardNumber = cardNumber;
        Scanner scanner = new Scanner(System.in);
        int pinCode;
        int countWrongPin = 0;
        int exit = -1;

        System.out.println("Please enter your pin code");
        pinCode = scanner.nextInt();
        while (true) {
            if (pinCode == exit) {
                break;
            }
            try {
                pinCheck(cardNumber, pinCode);
                startMenu();
            } catch (CheckPinException e) {
                showMsg.showWrongPin();
                pinCode = scanner.nextInt();
                countWrongPin++;
            }
            if (countWrongPin == 2) {
                try {
                    countWrongPin = 0;
                    System.out.println("Account locked for 5 seconds");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startMenu() {
        System.out.println("Start menu");
        System.out.println("1 - Get money");
        System.out.println("2 - Deposit money");
        System.out.println("3 - Get balance");
        System.out.println("4 - Quit");

        Scanner scanner = new Scanner(System.in);
        int select = scanner.nextInt();
        switch (select) {
            case 1:
                getMoney();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                getBalance();
                break;
            case 4:
                System.exit(0);

        }
    }

    public void getMoney() {
        Scanner scanner = new Scanner(System.in);
        if (isValidPin) {
            System.out.println("Enter amount");
        } else {
            callIdentification();
        }
        try {
            while (true) {
                int amount = scanner.nextInt();
                server.getMoney(amount);
                break;
            }
        } catch (NotEnoughMoney e) {
            showMsg.showNotEnoughMoney();
        }
    }

    public void depositMoney() {
        Scanner scanner = new Scanner(System.in);
        if (isValidPin) {
            System.out.println("Enter amount");
        } else {
            callIdentification();
        }
        while (true) {
            int amount = scanner.nextInt();
            try {
                server.depositMoney(amount);
            } catch (WrongSumException e) {
                showMsg.showWrongSumException();
            }
            break;
        }
    }


    public int getBalance() {
        int balance = server.getBalance();
        System.out.println(balance);
        return balance;
    }

    void pinCheck(long card, int pin) {
        isValidPin = pinValidator.checkCredentials(card, pin);
    }


    private void callIdentification() {
        try {
            throw new IdentificationException("Identification failed");
        } catch (IdentificationException e) {
            showMsg.showIdentError();
            startTerminal(cardNumber);
        }
    }


}
