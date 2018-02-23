package ru.sberbank.homework.kuznecov.first;

import java.util.Scanner;

public class Test {

    //Correct pin for testing - 0000.
    public static void main(String[] args) {
        Terminal terminal = new TerminalImpl(new TerminalServer(500), new PinValidator());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! This is terminal.");
        boolean quit = false;
        while (!quit){
            System.out.println("Type your command(help - get list of commands):");
            String input = scanner.nextLine();
            switch (input){
                case ("help"):
                    System.out.println("pin - check pin");
                    System.out.println("check - check account");
                    System.out.println("get - get money");
                    System.out.println("put - put money");
                    System.out.println("quit - quit from terminal");
                    break;
                case ("pin"):
                    System.out.println("Type your pin");
                    input = scanner.nextLine();
                    if (tryParseInt(input)) {
                        try {
                         boolean answer =
                                 terminal.validate(terminal.getCardNumber(), new Pin(Integer.parseInt(input)));
                         if (answer) {
                             System.out.println("Correct pin!");
                         } else {
                             System.out.println("Incorrect pin");
                         }
                        } catch (AccountIsLockedException e){
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid pin");
                    }
                    break;
                case ("check"):
                    try {
                        double answer = terminal.checkAccount(terminal.getCardNumber());
                        System.out.println("Now you have " + answer + " rubles.");
                    } catch (NotValidatedException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case ("get"):
                    System.out.println("Enter the sum(Must be a multiply of 100):");
                    input = scanner.nextLine();
                    if (tryParseDouble(input)){
                            try {
                                double answer =
                                        terminal.getCash(terminal.getCardNumber(), Double.parseDouble(input));
                                System.out.println("Now you have " + answer + " rubles left.");
                            } catch (IllegalArgumentException |
                                    NotValidatedException |
                                    NotEnoughMoneyException e){
                                System.out.println(e.getMessage());
                            }
                    } else {
                        System.out.println("Invalid sum");
                    }
                    break;
                case ("put"):
                    System.out.println("Please, input money(Must be a multiply of 100):");
                    input = scanner.nextLine();
                    if (tryParseDouble(input)){
                        try {
                            double answer =
                                    terminal.putCash(terminal.getCardNumber(), Double.parseDouble(input));
                            System.out.println("Now you have " + answer + " rubles.");
                        } catch (IllegalArgumentException |
                                NotValidatedException  e){
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid sum");
                    }
                    break;
                case ("quit"):
                    quit = true;
                    break;
                default:
                    System.out.println("Incorrect command. Type \"help\" to get list of them.");
                    break;
            }
        }

    }

    private static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
