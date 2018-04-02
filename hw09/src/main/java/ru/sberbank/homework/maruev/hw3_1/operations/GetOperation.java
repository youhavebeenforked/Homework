package ru.sberbank.homework.maruev.hw3_1.operations;

import ru.sberbank.homework.maruev.hw3_1.TerminalServer;
import ru.sberbank.homework.maruev.hw3_1.constants.BankCommands;
import ru.sberbank.homework.maruev.hw3_1.exceptions.*;

import java.util.Scanner;

/**
 * Created by Иван on 20.02.2018.
 */
public class GetOperation implements Operation {

    @Override
    public void move(TerminalServer server, Scanner scanner) {
        System.out.println(BankCommands.PIN_CODE);
        String code = scanner.nextLine();

        if (validator.isValidPin(code)) {
            server.setWrongPinCounter(0);
            System.out.println(BankCommands.MONEY_SUM);
            int sum = Integer.parseInt(scanner.nextLine());
            if (sum % 100 == 0) {
                try {
                    server.downBalance(sum);
                } catch (EmptyBillException e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            } else {
                throw new IllegalArgumentException(BankCommands.GET_MONEY);
            }
        } else {
            server.setWrongPinCounter(server.getWrongPinCounter() + 1);
            if (server.getWrongPinCounter() == LOCK_NUMBER) {
                server.lock();
            }
            throw new LockException(BankCommands.INCORRECT_PIN +
                    (LOCK_NUMBER - server.getWrongPinCounter()) + "\n");
        }
    }
}
