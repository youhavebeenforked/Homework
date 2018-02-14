package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.exceptions.AccountIsLockedException;
import ru.sberbank.homework.kiseleva.exceptions.NotEnoughMoneyException;

import java.util.Date;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class TerminalServer {
    private int wrongPins = 0;
    private long lockTime = 0;
    private int amountMoney = 1000;
    private boolean lock = false;

    public int getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(int amountMoney) throws NotEnoughMoneyException {
        if (amountMoney >= 0) {
            this.amountMoney = amountMoney;
        } else {
            throw new NotEnoughMoneyException("You have less money then you tried to take off!");
        }
    }

    public boolean isLock() {
        return lock;
    }

    public int getWrongPins() {
        return wrongPins;
    }

    public void setWrongPins(int wrongPins) throws AccountIsLockedException {
        this.wrongPins = wrongPins;
        if (this.wrongPins == 3) {
            lock = true;
            lockTime = new Date().getTime();
            System.out.println("Account is locked");
        }
    }

    public void unlock() {
        lock = false;
    }

    public void locker(Date currentDate) {
        if ((currentDate.getTime() - lockTime) / 1000 < 5) {
            throw new AccountIsLockedException(5 - ((currentDate.getTime() - lockTime) / 1000 ));
        } else {
            unlock();
        }
    }
}
