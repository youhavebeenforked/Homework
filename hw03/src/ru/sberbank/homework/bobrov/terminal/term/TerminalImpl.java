package ru.sberbank.homework.bobrov.terminal.term;


import ru.sberbank.homework.bobrov.terminal.exception.NotEnoughMoney;
import ru.sberbank.homework.bobrov.terminal.exception.WrongSumException;
import ru.sberbank.homework.bobrov.terminal.pin.PinValidator;
import ru.sberbank.homework.bobrov.terminal.srv.TerminalServer;
import ru.sberbank.homework.bobrov.terminal.srv.TerminalServerImpl;
import ru.sberbank.homework.bobrov.terminal.exception.CheckPinException;

import java.util.Scanner;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 16.02.2018
 */


public class TerminalImpl implements Terminal {
    private final TerminalServer server = new TerminalServerImpl();
    private final PinValidator pinValidator = new PinValidator();
    Scanner scanner = new Scanner(System.in);
    boolean isValidPin = false;

    @Override
    public void startTerminal(long cardNumber) throws CheckPinException, WrongSumException, NotEnoughMoney {
        Integer pinCode;
        int countWrongPin = 0;
        System.out.println("please enter your pin code");
        pinCode = scanner.nextInt();
        while (true) {
            if (pinValidator.checkCredentials(cardNumber, pinCode)) {
                isValidPin = true;
                startMenu();
                break;
            } else {
                System.out.println("Please try again");
                pinCode = scanner.nextInt();
                countWrongPin++;
            }
            if (countWrongPin == 2) {
                try {
                    countWrongPin = 0;
                    System.out.println("account locked for 5 seconds");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startMenu() throws CheckPinException, WrongSumException, NotEnoughMoney {
        System.out.println("start menu");
        System.out.println("1 - Get money");
        System.out.println("2 - Deposit money");
        System.out.println("3 - Get balance");
        System.out.println("4 - Quit");

        int select = scanner.nextInt();
        switch (select) {
            case 1:
                getMoney();
            case 2:
                depositMoney();
            case 3:
                getBalance();
            case 4:
                break;

        }
    }

    public void getMoney() throws CheckPinException, WrongSumException, NotEnoughMoney {
        if (isValidPin) {
            System.out.println("Enter amount");
        } else {
            throw new CheckPinException("Not entered pin");
        }
        int amount = scanner.nextInt();
        if (amount % 100 != 0) {
            throw new WrongSumException("Enter the sum multiple 100");
        } else {
            server.getMoney(amount);
        }

    }

    private void getBalance() {
    }

    private void depositMoney() {
    }


}
