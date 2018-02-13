package ru.sberbank.homework.kashin.task_1.model;

import ru.sberbank.homework.kashin.task_1.exception.AccountIsLockedException;

public class PinValidatorImpl implements PinValidator {
    private String pin = "0000";
    private boolean access = false;
    private int counter = 0;
    private long startTime;

    @Override
    public boolean giveAccess() {
        return access;
    }

    @Override
    public boolean checkPin(String pin) {
        if (pin.equals(this.pin)) {
            access = true;
            return access;
        } else {
            counter++;
            if  (counter == 3) {
                startTime = System.currentTimeMillis();
                resetCounter();
                throw new AccountIsLockedException("Вы ввели три раза неверный пин. Аккаунт заблокирован. Попробуй войти позже.");
            }
            return false;
        }
    }

    public void checkBlock() {
        if (startTime + 5000 > System.currentTimeMillis()){
            throw new AccountIsLockedException("Аккаунт заблокирован. Попробуй войти позже.");
        }
    }

    private void resetCounter() {
        counter = 0;
    }
}
