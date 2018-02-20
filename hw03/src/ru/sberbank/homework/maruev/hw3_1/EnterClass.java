package ru.sberbank.homework.maruev.hw3_1;

import ru.sberbank.homework.maruev.hw3_1.exceptions.AccountIsLockedException;
import ru.sberbank.homework.maruev.hw3_1.exceptions.LockException;

import java.util.Date;
import java.util.Scanner;

/**
 * Created by Иван on 20.02.2018.
 */
public abstract class EnterClass {
    Scanner scanner = new Scanner(System.in);
    TerminalServer server = new TerminalServer();
    TerminalImpl client = new TerminalImpl();
    private final static String OPERATION = "Выберите операцию:\n";
    private final static String BALANCE = "Доступный остаток -- 1\n";
    private final static String UP_BALANCE = "Пополнить счет -- 2\n";
    private final static String OUT_BALANCE = "Снять наличные -- 3\n";
    private final static String EXIT_CODE = "Завершить работу -- 4";
    private final static String INCORRECT_COMMAND = "Некорректный ввод\n";
    private final static String UN_LOCK = "До разблокировки: ";
    private final static String SECONDS = " секунд";
    private final static String[] COMMANDS_LIST = {"1", "2", "3", "4"};
    private final static String MESSAGE = String.format("Нажмите %s что бы продолжить, или %s для завершения",
            COMMANDS_LIST[0], COMMANDS_LIST[3]);
    private final int LOCK_TIME = 5;
    private final long M_SEC = 1000;

    public void useTerminal() {
        while (true) {
            try {
                if (!server.isLock()) {
                    Date date = new Date();
                    if ((date.getTime() - TerminalServer.startTime) > LOCK_TIME * M_SEC) {
                        server.unLock();
                    } else {
                        throw new AccountIsLockedException(UN_LOCK +
                                (LOCK_TIME - (date.getTime() - TerminalServer.startTime) / M_SEC) + SECONDS);
                    }
                }
            } catch (AccountIsLockedException e) {
                System.out.println(e.getMessage());
                System.out.println(MESSAGE);
                String a = scanner.nextLine();
                if (a.equals(COMMANDS_LIST[0])) {
                    continue;
                } else if (a.equals(COMMANDS_LIST[3])) {
                    break;
                }
            }
            System.out.println(OPERATION + BALANCE + UP_BALANCE + OUT_BALANCE + EXIT_CODE);
            String input = scanner.nextLine();

            try {
                if (input.equals(COMMANDS_LIST[3])) {
                    break;
                } else if (input.equals(COMMANDS_LIST[0])) {
                    client.checkBalance(server, scanner);
                } else if (input.equals(COMMANDS_LIST[1])) {
                    client.setMoney(server, scanner);
                } else if (input.equals(COMMANDS_LIST[2])) {
                    client.getMoney(server, scanner);
                } else {
                    throw new IllegalArgumentException(INCORRECT_COMMAND);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (LockException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
