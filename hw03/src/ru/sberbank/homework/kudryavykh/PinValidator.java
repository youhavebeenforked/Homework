package ru.sberbank.homework.kudryavykh;

import java.util.Timer;
import java.util.TimerTask;

public class PinValidator {
    private int attemp;
    private Account account;
    private int seconds;

    public PinValidator(Account account) {
        this.attemp = 0;
        this.account = account;
        this.seconds = 0;
    }

    public boolean checkPin(short pin) throws AccountIsLockedException {
        attemp++;
        if(attemp == 4) {
            Timer time = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    seconds++;
                    if(seconds == 5) {
                        attemp = 0;
                        seconds = 0;
                        time.cancel();
                    }
                }
            };
            time.schedule(tt, 0, 1000);
        }
        if(attemp < 4) {
            if (account.getPin() == pin) {
                attemp = 0;
                return true;
            } else {
                return false;
            }
        }
        else {
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
