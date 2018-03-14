package ru.sberbank.homework.kudryavykh;

public class Account {

    private final String name;
    private double balance;
    private final int pin;
    private final int cvv;
    private final long cardnumber;

    public Account(String name, double balance, int pin, int cvv, long cardnumber) {
        this.name = name;
        this.balance = balance;
        this.pin = pin;
        this.cvv = cvv;
        this.cardnumber = cardnumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) throws IncorrectBalanceException {
        if (balance < 0) {
            throw new IncorrectBalanceException("Отрицательный баланс. Баланс не может быть отрицательным");
        }
        this.balance = balance;
    }
}

class IncorrectBalanceException extends Exception {
    public IncorrectBalanceException(String message) {
        super(message);
    }
}
