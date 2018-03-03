package ru.sberbank.homework.gavrilov.hw03_1;

public interface Terminal {

    /**
     * Запущен ли терминал.
     *
     * @param cardNumber номер карты (аккаунта).
     * @return true, если такой cardNumber существует и он верефицирован в системе (ввёл пароль).
     */
    boolean startTerminal(String cardNumber);

    /**
     * Проверить баланс.
     *
     * @return
     */
    int checkBalance();

    /**
     * Положить деньги.
     *
     * @param amount сумма.
     */
    void depositMoney(int amount);

    /**
     * Снять деньги.
     *
     * @param amount сумма.
     * @return
     */
    int withDrawMoney(int amount);

}
