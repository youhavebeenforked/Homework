package ru.sberbank.homework.kudryavykh;

public class TerminalServer {
    private Account account;
    private PinValidator pinValid;

    public TerminalServer(long cardNumber) throws InvalidCardNumber {
        if(checkCardNumber(cardNumber)) {
            account = new Account("User", 1000, (short)1234, (short)123, 1111222233334444L);
            pinValid = new PinValidator(account);
        }
        else {
            throw new InvalidCardNumber("Карта повреждена или не обслуживается");
        }
    }

    //Условный метод по проверке карты
    private boolean checkCardNumber(long cardNumber) {
        return true;
    }

    public boolean pinCheck(short pin) throws AccountIsLockedException {
        return pinValid.checkPin(pin);
    }


    public double getBalance() {
        return account.getBalance();
    }

    public void setBalance(double balance) throws IncorrectBalanceException {
        account.setBalance(balance);
    }
}

class InvalidCardNumber extends Exception {

    public InvalidCardNumber(String message) {
        super(message);
    }
}
