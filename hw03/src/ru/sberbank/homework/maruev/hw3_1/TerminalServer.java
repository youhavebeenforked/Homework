package ru.sberbank.homework.maruev.hw3_1;

import ru.sberbank.homework.maruev.hw3_1.exceptions.EmptyBillException;

import java.util.Date;

/**
 * Created by Иван on 20.02.2018.
 */
public class TerminalServer {
    private int balance = 2500;
    private int wrongPinCounter = 0;
    private boolean isLock = true;
    private long startTime;
    private String BILL_IS_EMPTY = "Недостаточно средств на счете\n";

    public int getBalance() {
        return balance;
    }

    public long getStartTime() {
        return startTime;
    }

    public void upBalance(int sum) {
        balance += sum;
    }

    public void downBalance(int sum) {
        if (balance == 0 || balance < 100) {
            throw new EmptyBillException(BILL_IS_EMPTY);
        } else {
            balance -= sum;
        }
    }

    public void setWrongPinCounter(int wrongPinCounter) {
        this.wrongPinCounter = wrongPinCounter;
    }

    public int getWrongPinCounter() {
        return wrongPinCounter;
    }

    public void lock() {
        isLock = false;
        Date date = new Date();
        startTime = date.getTime();
    }

    public void unLock() {
        setWrongPinCounter(0);
        isLock = true;
    }

    public boolean isLock() {
        return isLock;
    }
}
