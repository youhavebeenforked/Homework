package ru.sberbank.homework.kudryavykh;

public class Account {

    final private String name;
    private double balance;
    final private short pin;
    final private short cvv;
    final private long cardnumber;

    public Account(String name, double balance, short pin, short cvv, long cardnumber) {
        this.name = name;
        this.balance = balance;
        this.pin = pin;
        this.cvv = cvv;
        this.cardnumber = cardnumber;
    }

    public short getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) throws IncorrectBalanceException {
        if(balance < 0)
            throw new IncorrectBalanceException("Отрицательный баланс. Баланс не может быть отрицательным");
        this.balance = balance;
    }
}

class IncorrectBalanceException extends Exception {
    public IncorrectBalanceException(String message) {
        super(message);
    }
}
