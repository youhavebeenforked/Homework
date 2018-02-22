package ru.sberbank.homework.maruev.hw3_1.operations;

import java.util.Scanner;

import ru.sberbank.homework.maruev.hw3_1.TerminalServer;
import ru.sberbank.homework.maruev.hw3_1.enums.BankCommand;
import ru.sberbank.homework.maruev.hw3_1.exceptions.*;

/**
 * Created by Иван on 20.02.2018.
 */
public class CheckOperation implements Operation {

    @Override
    public void move(TerminalServer server, Scanner scanner) {
        System.out.println(BankCommand.PIN_CODE.getCommand());
        String code = scanner.nextLine();

        if (validator.isValidPin(code)) {
            System.out.println(BankCommand.BALANCE_SUM.getCommand() + server.getBalance() + "\n");
        } else {
            server.setWrongPinCounter(server.getWrongPinCounter() + 1);
            if (server.getWrongPinCounter() == LOCK_NUMBER) {
                server.lock();
            }
            throw new LockException(BankCommand.INCORRECT_PIN.getCommand() +
                    (LOCK_NUMBER - server.getWrongPinCounter()) + "\n");
        }
    }
}
