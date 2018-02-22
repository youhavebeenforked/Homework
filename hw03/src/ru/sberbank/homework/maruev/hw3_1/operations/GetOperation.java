package ru.sberbank.homework.maruev.hw3_1.operations;

import ru.sberbank.homework.maruev.hw3_1.TerminalServer;
import ru.sberbank.homework.maruev.hw3_1.enums.BankCommand;
import ru.sberbank.homework.maruev.hw3_1.exceptions.*;

import java.util.Scanner;

/**
 * Created by Иван on 20.02.2018.
 */
public class GetOperation implements Operation {

    @Override
    public void move(TerminalServer server, Scanner scanner) {
        System.out.println(BankCommand.PIN_CODE.getCommand());
        String code = scanner.nextLine();

        if (validator.isValidPin(code)) {

            System.out.println(BankCommand.MONEY_SUM.getCommand());
            int sum = Integer.parseInt(scanner.nextLine());
            if (sum % 100 == 0) {
                try {
                    server.downBalance(sum);
                } catch (EmptyBillException e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            } else {
                throw new IllegalArgumentException(BankCommand.GET_MONEY.getCommand());
            }
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
