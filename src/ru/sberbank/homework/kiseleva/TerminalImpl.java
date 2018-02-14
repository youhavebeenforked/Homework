package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.exceptions.NotEnoughMoneyException;
import ru.sberbank.homework.kiseleva.interfaces.Terminal;

import java.util.Scanner;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class TerminalImpl implements Terminal {

    @Override
    public int checkAccount(TerminalServer server) {
        return server.getAmountMoney();
    }

    @Override
    public void pullMoney(TerminalServer server, int i) throws NotEnoughMoneyException {
        if (i % 100 != 0) {
            throw new IllegalArgumentException("The amount must be a multiple of 100");
        }
        server.setAmountMoney(server.getAmountMoney() - i);
        System.out.println("now you have " + server.getAmountMoney());
    }

    @Override
    public void putMoney(TerminalServer server, int i) {
        if (i % 100 != 0) {
            throw new IllegalArgumentException("The amount must be a multiple of 100");
        }
        server.setAmountMoney(server.getAmountMoney() + i);
        System.out.println("now you have " + server.getAmountMoney());
    }
}
