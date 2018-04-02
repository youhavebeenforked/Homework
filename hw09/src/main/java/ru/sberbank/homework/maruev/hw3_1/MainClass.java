package ru.sberbank.homework.maruev.hw3_1;

import ru.sberbank.homework.maruev.hw3_1.constants.EnterCommands;
import ru.sberbank.homework.maruev.hw3_1.exceptions.*;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by Иван on 20.02.2018.
 */
public class MainClass {
    private static final int LOCK_TIME = 5;
    public static final String[] COMMANDS_LIST = {"1", "2", "3", "4"};
    public static Scanner scanner = new Scanner(System.in);
    public static TerminalServer server = new TerminalServer();
    public static Terminal client = new TerminalImpl();

    public static void main(String[] args) {
        MainClass.start();
    }

    private static void start() {
        while (true) {
            try {
                lockInform();
                openMenu();
                enterCommand();
            } catch (AccountIsLockedException e) {
                System.out.println(e.getMessage() + "\n" + EnterCommands.CONTINUE_MESSAGE);
                String enter = scanner.nextLine();

                if (enter.equals(COMMANDS_LIST[0])) {
                    continue;
                } else if (enter.equals(COMMANDS_LIST[3])) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (LockException e) {
                System.out.println(e.getMessage());
            } catch (ExitException e) {
                break;
            }
        }
    }

    private static void lockInform() {
        if (!server.isLock()) {
            Date date = new Date();
            if (TimeUnit.MILLISECONDS.toSeconds(date.getTime() - server.getStartTime()) > LOCK_TIME) {
                server.unLock();
            } else {
                throw new AccountIsLockedException(String.format(EnterCommands.UN_LOCK,
                        LOCK_TIME -
                                TimeUnit.MILLISECONDS.toSeconds(date.getTime() -
                                        server.getStartTime())));
            }
        }
    }

    private static void openMenu() {
        System.out.println(
                EnterCommands.CHOOSE_OPERATION +
                        EnterCommands.BALANCE +
                        EnterCommands.UP_BALANCE +
                        EnterCommands.DOWN_BALANCE +
                        EnterCommands.EXIT_CODE);
    }

    public static void enterCommand() {
        String input = scanner.nextLine();

        if (input.equals(COMMANDS_LIST[3])) {
            throw new ExitException();
        } else if (input.equals(COMMANDS_LIST[0])) {
            client.checkBalance(server, scanner);
        } else if (input.equals(COMMANDS_LIST[1])) {
            client.setMoney(server, scanner);
        } else if (input.equals(COMMANDS_LIST[2])) {
            client.getMoney(server, scanner);
        } else {
            throw new IllegalArgumentException(EnterCommands.INCORRECT_COMMAND);
        }
    }
}
