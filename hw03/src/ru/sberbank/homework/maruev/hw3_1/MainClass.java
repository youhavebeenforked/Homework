package ru.sberbank.homework.maruev.hw3_1;

import ru.sberbank.homework.maruev.hw3_1.enums.EnterCommand;
import ru.sberbank.homework.maruev.hw3_1.exceptions.*;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by Иван on 20.02.2018.
 */
public class MainClass {
    private static final int LOCK_TIME = 5;
    public final static String[] COMMANDS_LIST = {"1", "2", "3", "4"};
    public static Scanner scanner = new Scanner(System.in);
    public static TerminalServer server = new TerminalServer();
    public static TerminalImpl client = new TerminalImpl();

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
                System.out.println(e.getMessage() + "\n" + EnterCommand.CONTINUE_MESSAGE.getCommand());
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
            if (TimeUnit.MILLISECONDS.toSeconds(date.getTime() - TerminalServer.startTime) > LOCK_TIME) {
                server.unLock();
            } else {
                throw new AccountIsLockedException(EnterCommand.UN_LOCK.getCommand() +
                        (LOCK_TIME - TimeUnit.MILLISECONDS.toSeconds(date.getTime() - TerminalServer.startTime))
                        + EnterCommand.SECONDS.getCommand());
            }
        }
    }

    private static void openMenu() {
        System.out.println(
                EnterCommand.CHOOSE_OPERATION.getCommand() +
                        EnterCommand.BALANCE.getCommand() +
                        EnterCommand.UP_BALANCE.getCommand() +
                        EnterCommand.DOWN_BALANCE.getCommand() +
                        EnterCommand.EXIT_CODE.getCommand());
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
            throw new IllegalArgumentException(EnterCommand.INCORRECT_COMMAND.getCommand());
        }
    }
}
