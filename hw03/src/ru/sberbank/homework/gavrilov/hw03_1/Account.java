package ru.sberbank.homework.gavrilov.hw03_1;

import java.util.Objects;

/**
 * Аккаунт пользователя в банке.
 * или
 * Банковский счёт.
 */
public class Account {

    /**
     * Unique number.
     */
    private final String cardNumber;

    /**
     * Пин код.
     */
    private int pinCode;

    /**
     * Баланс на счёте.
     */
    private int balance;

    /**
     * Клиент которому принадлежит аккаунт.
     */
    private final String client;

    public Account(String cardNumber, int balance, String client, int pinCode) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.client = client;
        this.pinCode = pinCode;
    }

    /**
     * Снять деньги.
     *
     * @param amount сумма
     * @return true если удалось снять деньги.
     */
    public boolean withDrawMoney(int amount) {
        boolean result = false;
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            result = true;
        }
        return result;
    }

    /**
     * Положить деньги.
     *
     * @param amount сумма
     * @return true если удалось положить.
     */
    public boolean depositMoney(int amount) {
        boolean result = false;
        if (amount > 0) {
            balance += amount;
            result = true;
        }
        return result;
    }

    /**
     * Проверить баланс.
     *
     * @return сумму на балансе.
     */
    public int checkBalance() {
        return balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getClient() {
        return client;
    }

    public int getPinCode() {
        return pinCode;
    }

    @Override
    public String toString() {
        return "Account{" +
                "cardNumber=" + cardNumber +
                ", balance=" + balance +
                ", client='" + client + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(cardNumber, account.cardNumber) &&
                Objects.equals(client, account.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, client);
    }
}
