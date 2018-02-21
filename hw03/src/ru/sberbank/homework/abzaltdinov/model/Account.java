package ru.sberbank.homework.abzaltdinov.model;

public class Account {
    private int id; //unique number
    private int pinCode;
    private String owner;

    public Account(int id, int pinCode, String owner) {
        this.id = id;
        this.pinCode = pinCode;
        this.owner = owner;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Account))
            return false;
        Account account = (Account)o;
        return account.id == this.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
