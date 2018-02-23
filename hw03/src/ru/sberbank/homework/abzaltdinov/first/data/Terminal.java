package ru.sberbank.homework.abzaltdinov.first.data;

public interface Terminal {
    boolean startSession(int accountNumber);

    boolean closeSession();

    boolean enterPin(int pinCode);

    /**
     * Запрос на снятие наличных со счета.
     *
     * @param amount Сумма наличных
     * @return true - если операция успешно выполнена, иначе - false
     */
    boolean withdrawCash(int amount);

    /**
     * Запрос на внесение наличных на счет.
     *
     * @param amount Сумма наличных
     * @return true - если операция успешно выполнена, иначе - false
     */
    boolean depositCash(int amount);

    void getBalance();
}
