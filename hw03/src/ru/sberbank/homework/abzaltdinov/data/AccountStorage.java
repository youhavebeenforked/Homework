package ru.sberbank.homework.abzaltdinov.data;

import ru.sberbank.homework.abzaltdinov.model.Account;

import java.util.HashSet;
import java.util.Set;

public class AccountStorage {
    private static Set<Account> accounts;

    static {
        accounts = new HashSet<>();
        accounts.add(new Account(1234, 1111, "Ivan"));
        accounts.add(new Account(3643, 8827, "Ilya"));
        accounts.add(new Account(9364, 4822, "Linar"));
        accounts.add(new Account(2534, 4982, "Aleksey"));
        accounts.add(new Account(0253, 9854, "Fedor"));
        accounts.add(new Account(4758, 1234, "Efim"));
    }

    public static Account getAccount(int accountId) {
        return accounts.stream()
                .filter(acc -> acc.getId() == accountId)
                .findFirst()
                .orElse(null);
    }

}
