package ru.sberbank.homework.gavrilov.hw03_1;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    /**
     * Список счётов (аккаунтов) в банке.
     */
    private List<Account> listOfAccounts = new ArrayList<>();

    public void add(Account account) {
        listOfAccounts.add(account);
    }

    public Bank() {
        fillAccount();
    }

    /**
     * Дефолт аккаунт.
     */
    private void fillAccount() {
        listOfAccounts.add(new Account("1234 5678", 1000, "Sergey", 1234));
        listOfAccounts.add(new Account("5678 1234", 500, "Pert", 4321));
        listOfAccounts.add(new Account("8765 4321", 2000, "Mark", 5678));
        listOfAccounts.add(new Account("1111 1111", 5000, "Vova", 1111));
    }

    public List<Account> getListOfAccounts() {
        return listOfAccounts;
    }

    public Account getAccount(String cardNumber) {
        return listOfAccounts.stream()
                .filter(account -> account.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElse(null);
    }
}
