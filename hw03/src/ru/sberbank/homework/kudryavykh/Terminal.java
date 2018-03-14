package ru.sberbank.homework.kudryavykh;

public interface Terminal {

    void checkAccount() throws AccessAccoutException;

    void getMoney() throws AccessAccoutException;

    void setMoney() throws AccessAccoutException;

    boolean pin();
}
