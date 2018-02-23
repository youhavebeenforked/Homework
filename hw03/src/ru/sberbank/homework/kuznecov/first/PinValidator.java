package ru.sberbank.homework.kuznecov.first;

import java.util.Date;

public class PinValidator {

    private boolean validated;
    private int counter;
    private Date date;

    public PinValidator() {
        validated = false;
        counter = 0;
        date = null;
    }

    public boolean validate(CardNumber cardNumber, Pin pin) {
        Date now = new Date();
        if (date != null && (now.getTime() - date.getTime() < 5000)) {
            throw new AccountIsLockedException(
                    "Account is locked! Please wait for " +
                            String.valueOf(now.getTime() - date.getTime())
            + " millisec.");
        }
        if (pin.equals(getPinFromDB(cardNumber))){
            validated = true;
        } else {
            validated = false;
            counter++;
            if (counter > 2) {
                date = now;
                counter = 0;
            }
        }
        return validated;
    }

    public boolean isValidated(){
        return validated;
    }

    //Запрос к бд
    private Pin getPinFromDB(CardNumber cardNumber){
        return new Pin(0000);
    }
}
