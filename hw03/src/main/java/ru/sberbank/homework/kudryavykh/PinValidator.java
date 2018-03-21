package ru.sberbank.homework.kudryavykh;

import java.util.Timer;
import java.util.TimerTask;

public class PinValidator {
    private int attemp;
    private final int MAX_COUNT_ATTEMPS = 3;
    private final int DELAY_BEFORE_UNLOCK = 5;
    private Account account;
    private int seconds;

    public PinValidator(Account account) {
        this.account = account;
    }

    public boolean checkPin(int pin) throws AccountIsLockedException {
        attemp++;
        if (attemp > MAX_COUNT_ATTEMPS) {
            Timer timer = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    seconds++;
                    if (seconds == DELAY_BEFORE_UNLOCK) {
                        attemp = 0;
                        seconds = 0;
                        timer.cancel();
                    }
                }
            };
            timer.schedule(tt, 0, 1000);
        }
        if (attemp <= MAX_COUNT_ATTEMPS) {
            if (account.getPin() == pin) {
                attemp = 0;
                return true;
            } else {
                return false;
            }
        } else {
            //Таймер на 5 секунд, после которого сброс attemp
            throw new AccountIsLockedException("Lock! Left " + (5 - seconds) + " seconds.");
        }
    }
}

class AccountIsLockedException extends Exception {

    public AccountIsLockedException(String message) {
        super(message);
    }
}
