package ru.sberbank.homework.dergun.hw1;

public class MoneyValidator {
    public boolean validWithdrawMoney(int money) {
        if (money % 100 != 0 || money <= 0) {
            throw new FailedValidateMoney("You can withdraw money only multiples of 100");
        }
        return true;
    }

    public boolean validPutMoney(int money) {
        if (money % 100 != 0 || money <= 0) {
            throw new FailedValidateMoney("You can put money only multiples of 100");
        }
        return true;
    }

}

class FailedValidateMoney extends RuntimeException {
    public FailedValidateMoney(String message) {
        super(message);
    }
}
