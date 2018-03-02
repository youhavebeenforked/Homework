package ru.sberbank.homework.abzaltdinov.first.repository;

import ru.sberbank.homework.abzaltdinov.first.model.Account;

import java.util.HashSet;
import java.util.Set;

public class AccountStorage {
    private static Set<Account> accounts;

    static {
        accounts = new HashSet<>();
        accounts.add(new Account(1111, 1111, "Ivan", 1000));
        accounts.add(new Account(2222, 2222, "Ilya", 2000));
        accounts.add(new Account(3333, 3333, "Linar", 3000));
        accounts.add(new Account(4444, 4444, "Aleksey", 4000));
        accounts.add(new Account(5555, 5555, "Fedor", 500));
        accounts.add(new Account(6666, 6666, "Efim", 100));
    }

    public static Account getAccount(int accountId) {
        return accounts.stream()
                .filter(acc -> acc.getId() == accountId)
                .findFirst()
                .orElse(null);
    }

}
