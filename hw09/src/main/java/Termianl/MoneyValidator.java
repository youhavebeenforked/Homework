package Termianl;

import Termianl.exeptions.ValidateMoneyExeption;

public class MoneyValidator {
    public void validMoney(int money) {
        if (money % 100 != 0 || money <= 0) {
            throw new ValidateMoneyExeption("The amount entered must be a multiple of 100");
        }
    }
}

