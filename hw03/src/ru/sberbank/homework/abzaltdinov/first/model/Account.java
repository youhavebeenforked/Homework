package ru.sberbank.homework.abzaltdinov.first.model;

public class Account {
    private int id; //unique number
    private int pinCode;
    private String owner;
    private int balance;
    public long lockedUntil;

    public Account(int id, int pinCode, String owner, int balance) {
        this.id = id;
        this.pinCode = pinCode;
        this.owner = owner;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getPinCode() {
        return pinCode;
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    public void depositMoney(int amount) {
        this.balance += amount;
    }

    public void withdrawMoney(int amount) {
        this.balance -= amount;
    }

    public long getLockedUntil() {
        return lockedUntil;
    }

    public void setLockedUntil(long lockedUntil) {
        this.lockedUntil = lockedUntil;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return account.id == this.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
