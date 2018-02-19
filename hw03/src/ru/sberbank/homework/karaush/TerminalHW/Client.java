package ru.sberbank.homework.karaush.TerminalHW;

import java.util.Scanner;

public class Client {

    private Terminal terminal;
    private Scanner scanner;

    Client(Terminal terminal, Scanner scanner) {
        this.terminal = terminal;
        this.scanner = scanner;
    }

    public void interact() {

        //pin-code processing
        System.out.println("Enter pin-code");
        boolean access = false;
        do {
            try {
                String s = scanner.nextLine();
                access = terminal.validatePin(s);
                if (!access) {
                    System.out.println("incorrect pin, try again");
                }

            } catch (IncorrectPinFormatException e) {
                System.out.println("incorrect format, correct format - \"****\"");
            }catch (IllegalAccessException e){
                System.out.println("your account is blocked for " + Integer.parseInt(e.getMessage()) / 1000 + " seconds");
            }
        } while (!access);

        //interaction with authorised user
        boolean interaction = true;
        while (interaction) {
            System.out.println("");
            System.out.println("press 1 to check account");
            System.out.println("press 2 to put money");
            System.out.println("press 3 to withdraw money");
            System.out.println("press 4 to quit");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("You have " + terminal.checkBalance());
                    break;
                case "2":
                    System.out.println("enter your money");
                    try {
                        terminal.putMoney(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("your input should be integer");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("enter desired amount");
                    try {
                        terminal.withdrawMoney(Integer.parseInt(scanner.nextLine()));
                    }catch(NumberFormatException e) {
                        System.out.println("your input should be integer");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (NotEnoughMoneyException e) {
                        System.out.println("you don't have such amount of money");
                    }
                    break;
                case "4":
                    interaction = false;
                    break;
                default:
                    System.out.println("Your input isn't recognised");
            }
        }
    }
}




















