package ru.sberbank.homework.kuznecov.first;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PinValidator {

    private boolean validated;
    private int counter;
    private Date date;
    private static final int NUMBER_OF_SECONDS = 5;

    public PinValidator() {
    }

    public boolean validate(CardNumber cardNumber, Pin pin) {
        Date now = new Date();
        if (date != null && (now.getTime() - date.getTime() < TimeUnit.SECONDS.toMillis(NUMBER_OF_SECONDS))) {
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
            if (counter >= 3) {
                date = now;
                counter = 0;
            }
        }
        return validated;
    }

    public void checkValidation(){
        if (!validated){
            throw new NotValidatedException("At first you should type your pin. " +
                    "Please, type \"pin\" and then your pin.");
        }
    }

    //Запрос к бд
    private Pin getPinFromDB(CardNumber cardNumber){
        return new Pin(0000);
    }
}
